package com.textTI.storeManagement.poc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Tests {

	public static void main(String[] args) {
		Calendar dtFrom = Calendar.getInstance();
		dtFrom.set(2013, 11,1);
		
		Calendar dtTo = Calendar.getInstance();
		dtTo.set(2014, 11,1);
		
		List<String> headers = new ArrayList<String>();
		while (dtFrom.before(dtTo)) {
			System.out.println(dtFrom.get(Calendar.MONTH) + "/" + dtFrom.get(Calendar.YEAR));
			dtFrom.add(Calendar.MONTH, 1);
			
			headers.add(dtFrom.get(Calendar.MONTH) + "/" + dtFrom.get(Calendar.YEAR));
		}

	}

}
