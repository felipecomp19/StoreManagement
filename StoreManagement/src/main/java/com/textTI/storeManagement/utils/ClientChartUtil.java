package com.textTI.storeManagement.utils;

import java.util.List;

import org.springframework.ui.Model;

import com.textTI.storeManagement.manager.ClientManager;
import com.textTI.storeManagement.model.Client;

public class ClientChartUtil {

	public void prepareClientChartData(Model model, List<Client> clients, ClientManager clientManager) {
		//TODO não ficou bom!
		//Esta retornando a lista ao contrario
		/*List<Client> clientsYear = clientManager.getClientsCreatedInAYear();
		String clientsCountChart="<!-- " + clientsYear.toString().replace("[","").replace("]", "");
		clientsCountChart =clientsCountChart.concat(" -->");
		model.addAttribute("clientsCountChart", clientsCountChart);*/
		
		int totalOfClients = clients.size();
		model.addAttribute("clientsSize", totalOfClients );
	}
}
