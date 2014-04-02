package com.textTI.storeManagement.model.report;

import java.util.List;

import com.textTI.storeManagement.model.Indicator;

public class IndicatorsSummary {
	
	private List<Indicator> indicators;
	
	private Indicator userData;

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public Indicator getUserData() {
		return userData;
	}

	public void setUserData(Indicator userData) {
		this.userData = userData;
	}
}
