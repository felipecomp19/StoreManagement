package com.textTI.storeManagement.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.textTI.storeManagement.manager.AuditManager;
import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.manager.StoreManager;
import com.textTI.storeManagement.model.Audit;
import com.textTI.storeManagement.model.Client;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.chart.TotalClientsByMonth;

@Controller
public class DashboardController extends BaseController{
	
	@Autowired
	private ClientManager clienteManager;
	
	@Autowired
	private StoreManager storeManager;
	
	@Autowired
	private AuditManager auditManager;
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Locale locale, Model model) {
		logger.info("Accessed the dashboard", locale);
		
		List<Client> clients = this.clienteManager.getAll();
		List<Store>	stores = this.storeManager.getAll();
		List<TotalClientsByMonth> totalClientsByMonth = this.storeManager.getTotalClientsByMonthInAYear(2014);
		
		//ClientChartUtil ccUtil = new ClientChartUtil();
		//ccUtil.prepareClientChartData(model, clients, this.clienteManager);
		
		List<Audit> top50audit = this.auditManager.getTOP50(locale);
		
		model.addAttribute("clientsSize", clients.size());
		model.addAttribute("stores", stores);
		model.addAttribute("audits", top50audit);
		model.addAttribute("totalClientsByMontList", totalClientsByMonth);
		model.addAttribute("filterYear", "2014");
		
		return "dashboard/dashboard";
	}

}
