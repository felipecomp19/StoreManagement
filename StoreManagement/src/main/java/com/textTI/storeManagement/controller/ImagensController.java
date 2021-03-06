package com.textTI.storeManagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.textTI.storeManagement.manager.ImagensManager;
import com.textTI.storeManagement.model.FileUpload;
import com.textTI.storeManagement.model.Imagen;

@Controller
@RequestMapping(value="/imagens")
public class ImagensController extends BaseController {

	@Autowired
	private ImagensManager imgManager;
	
	//private final String filePath = "/home/felipe/app/jboss-6.1.0.Final/server/default/deploy/ROOT.war/storeManager/morana/images/"; //desenv
	private final String filePath = "/usr/share/jboss-6.1.0.Final/server/default/deploy/ROOT.war/storeManager/morana/images/"; //production
	//private final String filePath = "/usr/share/jboss-6.1.0.Final/server/default/deploy/ROOT.war/storeManager/tutti/images/"; //production
	
	private final String relativePath = "/storeManager/morana/images/";
	//private final String relativePath = "/storeManager/tutti/images/";
	
	@RequestMapping(value="/list",  method = RequestMethod.GET)
	public String imagens(Model model)
	{
		List<Imagen> imagens = this.imgManager.getAllImagens();
        model.addAttribute("imagens", imagens);
        model.addAttribute("relativePath",this.relativePath);
		
		return "/imagens/list";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload()
	{
		return "/imagens/upload";
	}
	
	@RequestMapping(value = "/uploadImagens", method = RequestMethod.POST)
    public String uploadImagens(@ModelAttribute("uploadForm") FileUpload uploadForm, Model model) {
        List<MultipartFile> files = uploadForm.getFiles();
        if(files != null && files.size() > 0){
        	Imagen img = new Imagen();
        	img.setName(uploadForm.getName());
			try {
				this.imgManager.save(img,files, this.filePath, this.relativePath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", "Erro ao importar imagens. Code 1!");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", "Erro ao importar imagens. Code 2!");
				e.printStackTrace();
			}
        }
        
        List<Imagen> imagens = this.imgManager.getAllImagens();
        model.addAttribute("imagens", imagens);
        model.addAttribute("relativePath",this.relativePath);
        
        return "redirect:/imagens/list";
    }
	
	@RequestMapping(value = "/uploadImagensAjax", method = RequestMethod.POST)
    public @ResponseBody String uploadImagensAjax(@ModelAttribute("uploadForm") FileUpload uploadForm, Model model,  HttpServletRequest request) {
        List<MultipartFile> files = uploadForm.getFiles();
        Imagen img = new Imagen();
        if(files != null && files.size() > 0){
        	img.setName(uploadForm.getName());
			try {
				this.imgManager.save(img,files, this.filePath, this.relativePath);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", "Erro ao importar imagens. Code 1!");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				model.addAttribute("message", "Erro ao importar imagens. Code 2!");
				e.printStackTrace();
			}
        }
        
        System.out.println("http://".concat(request.getServerName()));
        System.out.println(this.relativePath);
        System.out.println(img.getFileName());
        
        String imgUrl = "http://".concat(request.getServerName()).concat(this.relativePath).concat(img.getFileName());
        
        //List<Imagen> imagens = this.imgManager.getAllImagens();
        //model.addAttribute("imagens", imagens);
        model.addAttribute("relativePath",this.relativePath);
        
        //return this.relativePath.concat(img.getFileName());
        return imgUrl;
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id)
	{
		Imagen img = this.imgManager.getById(id);
		this.imgManager.delete(img, this.filePath);
		
		return "redirect:/imagens/list";
	}
}
