package com.zym.imp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import com.zym.bean.Instrument;
import com.zym.interf.IPublish;

public class DemoPublishImp implements IPublish {

	private Set<String> fields = new HashSet<String>();
	// Use a hashmap to replace database in this demo.
	private Map<String, List<Instrument>> cache = new HashMap<String, List<Instrument>>();

	@SuppressWarnings("unused")
	private Class<?> userClass;
	private final String publisher1 = "LME";
	private final String publisher2 = "PRIME";

	public DemoPublishImp() {
		fields.add("code");
		fields.add("exchangecode");
		fields.add("market");
		fields.add("label");

		cache.put(publisher1, new ArrayList<Instrument>());
		cache.put(publisher2, new ArrayList<Instrument>());

		try {
			userClass = Class.forName("com.zym.bean.Instrument");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main function to get Instrument
	 */
	@Override
	public String PublishData(List<Instrument> li) {

		// Categorize Instrument
		li.forEach((Instrument i) -> {
			if (publisher1.equalsIgnoreCase(i.getPublisher())) {
				cache.get(publisher1).add(i);
			} else {
				cache.get(publisher2).add(i);
			}
		});

		// Get mapping relationship for 2 publisher
		Scanner sc = new Scanner(System.in);
		String LMEField = getFieldForMapping(publisher1, sc);
		String PRIMEField = getFieldForMapping(publisher2, sc);

		// Get instrument list by publisher and code
		String publisher = SelectPublisher(sc).toUpperCase();
		List<Instrument> instruments = SelectCode(publisher, sc);

		// According to different publisher, get instrument internally
		if(instruments.size()>0)
		{
			if (publisher1.equalsIgnoreCase(publisher)) {
				System.out.println(GetLMEInstrument(instruments).toString());
			} else if (publisher2.equalsIgnoreCase(publisher)) {
				System.out.println(GetPRIMEInstrument(instruments, LMEField, PRIMEField).toString());
			}
		}
		sc.close();
		return "success";
	}

	@Override
	public void ShowAllData(List<Instrument> li) {
		System.out.println("Here are some initialized informations for test");
		li.forEach((Instrument i) -> {
			System.out.println(i.toString());
		});
		System.out.println("");
	}

	private String getFieldForMapping(String exchange, Scanner sc) {
		String fieldName;
		while (true) {
			System.out.println("e.g In example, we can type 'code' for LEM and type 'exchangeCode' for PRIME : ");
			System.out.println("Please type allowed field for [" + exchange + "] mapping: [code,exchangeCode,market,label],");
			try {
				String name = sc.nextLine().toLowerCase().trim();
				if (fields.contains(name)) {
					if ("exchangecode".equalsIgnoreCase(name)) {
						name = "exchangeCode";
					}
					fieldName = (new StringBuilder()).append("get").append(Character.toUpperCase(name.charAt(0)))
							.append(name.substring(1)).toString();
					break;
				} else {
					throw new Exception("Your typed field [" + name + "] does not allowed.");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return fieldName;
	}

	private String SelectPublisher(Scanner sc) {
		String name = null;
		while (true) {
			System.out.println("Please select a publisher [LME/PRIME] : ");
			try {
				name = sc.nextLine().toLowerCase().trim();
				if (!"lme".equalsIgnoreCase(name) && !"prime".equalsIgnoreCase(name)) {
					throw new Exception("Your typed publisher [" + name + "] does not allowed.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return name;
	}

	private List<Instrument> SelectCode(String publisher, Scanner sc) {
		List<Instrument> l = new ArrayList<Instrument>();
		while (true) {
			System.out.println(
					"Please intput an instrument code [e.g PB_03_2018] or 'exit' for cancel: ");
			try {
				String temp = sc.nextLine().toLowerCase().trim();
				l = cache.get(publisher).stream().filter(i -> i.getCode().equalsIgnoreCase(temp))
						.collect(Collectors.toList());
				if (l.size() == 0 && !"exit".equalsIgnoreCase(temp)) {
					throw new Exception("Your typed code [" + temp + "] does not exist.");
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return l;
	}

	/**
	 * Get instrument internally for LME
	 */
	private Instrument GetLMEInstrument(List<Instrument> li) {
		Instrument i = new Instrument();
		if (li.size() > 0) {
			Collections.sort(li, (a, b) -> b.getLastTradingDate().compareTo(a.getLastTradingDate()));
			i = li.get(0);
		}
		return i;
	}

	/**
	 * Get instrument internally for PRIME
	 */
	private Instrument GetPRIMEInstrument(List<Instrument> li, String LMEField, String PRIMEField) {
		// Get the most up-to-date instrument
		Instrument ist = new Instrument();
		if (li.size() > 0) {
			Collections.sort(li, (a, b) -> b.getLastTradingDate().compareTo(a.getLastTradingDate()));
			ist = li.get(0);
		}

		Method getValue;
		try {
			getValue = userClass.getMethod(PRIMEField);
			String value = (String) getValue.invoke(ist);

			getValue = userClass.getMethod(LMEField);

			List<Instrument> templist = new ArrayList<Instrument>();
			for (Instrument i : cache.get(publisher1)) {
				if (value.equalsIgnoreCase((String) getValue.invoke(i))) {
					templist.add(i);
				}
			}
			
			if (templist.size() > 0) {
				Collections.sort(templist, (a, b) -> b.getLastTradingDate().compareTo(a.getLastTradingDate()));
				ist.setLastTradingDate(templist.get(0).getLastTradingDate());
			}
			if (templist.size() > 0) {
				Collections.sort(templist, (a, b) -> b.getDeliveryDate().compareTo(a.getDeliveryDate()));
				ist.setDeliveryDate(templist.get(0).getDeliveryDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ist;
	}
}
