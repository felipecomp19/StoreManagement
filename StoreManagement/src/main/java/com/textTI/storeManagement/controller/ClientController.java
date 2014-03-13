package com.textTI.storeManagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.textTI.storeManagement.exception.ClientException;
import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.manager.ClientTypeManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.ClientType;
import com.textTI.storeManagement.model.FileUpload;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.utils.ClientChartUtil;

@Controller
@RequestMapping(value="/client")
public class ClientController extends BaseController{
	
	@Autowired
	private ClientManager clientManager;
	
	@Autowired
	private ClientTypeManager clientTypeManager;
	
	@Autowired
	private StoreManager storeManager;
	
	private Map<String, Store> storeCache;
	
	@ModelAttribute("clientTypes")
	public List<ClientType> populateClientTypes()
	{
		return this.clientTypeManager.getAll();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Locale locale, Model model) {
		logger.info("Accessed the clients list view", locale);
		
		List<Client> clients = this.clientManager.getAllByUser(this.getLoggedUser(request));
		
		this.prepareChartData(model, clients);
		
		model.addAttribute("clients", clients);
		
		return "client/list";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Locale locale, Model model) {
		logger.info("Accessed the create client view", locale);
		
		List<Client> clients = this.clientManager.getAllByUser(this.getLoggedUser(request));

		this.prepareChartData(model, clients);
		this.populateDayAndMonthSelectList(model);
		this.populateStores(request, model);
		
		model.addAttribute("client", new Client());
		
		return "client/create";
	}

	private void prepareChartData(Model model, List<Client> clients) {
		ClientChartUtil ccUtil = new ClientChartUtil();
		ccUtil.prepareClientChartData(model, clients, this.clientManager);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("client") Client client, HttpServletRequest request)
	{
		logger.info("Creating a client");
		
		//TODO validate Client 
		try{
			if(client.getId() != null)
				this.clientManager.update(client);
			else
				this.clientManager.insert(client);
		}catch(ClientException ce)
		{
			//TODO create a validator
			logger.error(ce.getMessage());
		}
		
		return "redirect:/client/list";
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") long id, HttpServletRequest request, Model model)
	{
		Client client = this.clientManager.getById(id);
		List<Client> clients = this.clientManager.getAll();

		this.prepareChartData(model, clients);
		this.populateDayAndMonthSelectList(model);
		this.populateStores(request, model);
		
		model.addAttribute("client", client);
		
		return "client/edit";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		Client client = this.clientManager.getById(id);
		this.clientManager.delete(client);
		
		return "redirect:/client/list";
	}
	
	@RequestMapping(value="/getClientByCPF/{cpf}")
	public @ResponseBody Client getClientByCPF(@PathVariable("cpf") String cpf) {
		Client cli = this.clientManager.getClientByCPF(cpf);
		
		if(cli == null)
			cli = new Client();
		
		return cli;
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        
		return "/client/upload";
    }
	
	
	@RequestMapping(value = "/uploadClients", method = RequestMethod.POST)
    public String uploadClients(@ModelAttribute("uploadForm") FileUpload uploadForm, Model model) {
        List<MultipartFile> files = uploadForm.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
         
        if(null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {
 
                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                
                //Handle file content - multipartFile.getInputStream()
            }
        }
         
        model.addAttribute("files", fileNames);
        return "redirect:/client/list";
    }
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "stores", new CustomCollectionEditor(Set.class) {
			protected Object convertElement(Object element) {
				if (element instanceof Store) {
					logger.debug("Converting from store to store: " + element);
					return element;
				}
				if (element instanceof String) {
					Store store = storeCache.get(element);
					logger.debug("Looking up store for id " + element + ": " + store);
					return store;
				}
				logger.debug("Don't know what to do with: " + element);
				return null;
			}
		});
	}
	
	private void populateStores(HttpServletRequest request, Model model) {
		List<Store> stores = this.storeManager.getAllByUser(this.getLoggedUser(request));
		this.storeCache = new HashMap<String,Store>();
		for (Store store : stores) {
			this.storeCache.put(store.getIdAsString(), store);
		}
		model.addAttribute("stores", stores);
	}
	
	
	private void populateDayAndMonthSelectList(Model model)
	{
		this.populateWithSequencialNumber(model, "daysSL", 1, 31);
		this.populateWithSequencialNumber(model, "monthSL", 1, 12);
	}
	
	/***
	 * Create a list starting in {initNumber}, ending in {finalNumber} and add 
	 * this list as attribute of the {model} named {var}
	 * 
	 * @param model
	 * @param var
	 * @param initNumber
	 * @param finalNumber
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void populateWithSequencialNumber(Model model, String var, int initNumber, int finalNumber)
	{
		List numbers = new ArrayList();
		for(int i = initNumber; i <= finalNumber; i++)
		{
			numbers.add(i);
		}
		
		model.addAttribute(var, numbers);
	}
}
