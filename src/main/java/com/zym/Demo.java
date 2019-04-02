package com.zym;

import java.time.LocalDate;
import java.util.Arrays;

import com.zym.bean.Instrument;
import com.zym.controller.LogicControl;

public class Demo {

	public static void main(String[] args) {
		
		//initialize some informations
		Instrument i1=new Instrument();
		i1.setPublisher("LME");
		i1.setCode("PB_03_2018");
		i1.setLastTradingDate(LocalDate.of(2018,03, 15));	
		i1.setDeliveryDate(LocalDate.of(2018,03, 17));
		i1.setMarket("LME_PB");
		i1.setLabel("Lead 13 March 2018");
		
		Instrument i3=new Instrument();
		i3.setPublisher("LME");
		i3.setCode("PB_03_2018");
		i3.setLastTradingDate(LocalDate.of(2018,03, 11));	
		i3.setDeliveryDate(LocalDate.of(2018,03, 19));
		i3.setMarket("LME_PB");
		i3.setLabel("Lead 13 March 2018");
		
		
		Instrument i4=new Instrument();
		i4.setPublisher("LME");
		i4.setCode("PB_03_2018");
		i4.setLastTradingDate(LocalDate.of(2018,03, 9));	
		i4.setDeliveryDate(LocalDate.of(2018,03, 21));
		i4.setMarket("LME_PB");
		i4.setLabel("Lead 13 March 2018");
		
		
		Instrument i5=new Instrument();
		i5.setPublisher("LME");
		i5.setCode("PB_03_2018");
		i5.setLastTradingDate(LocalDate.of(2018,03, 16));	
		i5.setDeliveryDate(LocalDate.of(2018,03, 11));
		i5.setMarket("LME_PB");
		i5.setLabel("Lead 13 March 2018");
		
		
		Instrument i2=new Instrument();
		i2.setPublisher("PRIME");
		i2.setCode("PB_03_2018");
		i2.setExchangeCode("PB_03_2018");
		i2.setLastTradingDate(LocalDate.of(2018,03, 14));	
		i2.setDeliveryDate(LocalDate.of(2010,03, 18));
		i2.setMarket("LME_PB");
		i2.setLabel("Lead 13 March 2018");
		i2.setTradable(false);
		
		//call the program to publish
		LogicControl lc=LogicControl.getInstance();
		lc.Control(Arrays.asList(i1,i2,i3,i4,i5));
				
	}

}
