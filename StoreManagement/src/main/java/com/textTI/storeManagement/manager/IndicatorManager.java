package com.textTI.storeManagement.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.textTI.storeManagement.dao.IndicatorDAO;
import com.textTI.storeManagement.model.Indicator;
import com.textTI.storeManagement.model.User;

@Component
public class IndicatorManager {
	
	@Autowired
	private IndicatorDAO indicatorDAO;

	public List<Indicator> getAll() {
		return this.indicatorDAO.getAll();
	}

	public Indicator getById(long id) {

		return (Indicator) this.indicatorDAO.getById(id, Indicator.class);
	}

	public void update(Indicator indicator) {
		this.indicatorDAO.update(indicator);
	}

	public void insert(Indicator indicator) {
		indicator.setEnabled(true);
		this.indicatorDAO.insert(indicator);
	}

	public void delete(Indicator emp) {
		this.indicatorDAO.delete(emp);
	}

	public List<Indicator> getAllByUser(User loggedUser) {
		return this.indicatorDAO.getAllByUser(loggedUser.getStoresId());
	}

	public List<Indicator> getAllByUserAndMonth(User loggedUser,
			String selectedMonth, String selectedYear) {
		return this.indicatorDAO.getAllByUserAndMonth(loggedUser.getStoresId(),selectedMonth, selectedYear);
	}
}
