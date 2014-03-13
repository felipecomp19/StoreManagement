package com.textTI.storeManagement.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.StoreDAO;
import com.textTI.storeManagement.model.Store;
import com.textTI.storeManagement.model.User;
import com.textTI.storeManagement.model.chart.TotalClientsByMonth;

@Component
public class StoreManager {

	@Autowired
	private StoreDAO storeDAO;
	
	public void insert(Store store) {
		this.storeDAO.insert(store);
	}

	public Store getById(Long id) {
		
		return this.storeDAO.getById(id);
	}

	public void update(Store store) {
		this.storeDAO.update(store);
		
	}

	public void delete(Store store) {
		//TODO validate if the store has clients
		this.storeDAO.delete(store);
	}

	public List<Store> getAll() {
		List<Store> stores = this.storeDAO.getAll();
		
		if(stores == null)//para evitar nullPointers =)
			stores = new ArrayList<Store>();
		
		return stores;
	}
	
	public List<TotalClientsByMonth> getTotalClientsByMonthInAYear(int year)
	{
		List<TotalClientsByMonth> result = this.storeDAO.getTotalClientsByMonthInAYear(year);
		if(result == null)
			result = new ArrayList<TotalClientsByMonth>(); //avoiding nullPointers =)
		
		return result;
	}

	public List<Store> getAllByUser(User loggedUser) {
		return this.storeDAO.getAllByUser(loggedUser.getStoresId());
	}
}
