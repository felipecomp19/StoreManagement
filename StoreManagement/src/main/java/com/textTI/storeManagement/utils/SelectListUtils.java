package com.textTI.storeManagement.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

public class SelectListUtils {
	
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
	public static void populateWithSequencialNumber(Model model, String var, int initNumber, int finalNumber)
	{
		List numbers = new ArrayList();
		for(int i = initNumber; i <= finalNumber; i++)
		{
			numbers.add(i);
		}
		
		model.addAttribute(var, numbers);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Long> populateWithSequencialNumber(int initNumber, int finalNumber) {
		
		List numbers = new ArrayList();
		for(int i = initNumber; i <= finalNumber; i++)
		{
			numbers.add(i);
		}
		return numbers;
	}

}
