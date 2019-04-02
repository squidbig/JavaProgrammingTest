package com.zym.interf;

import java.util.List;

import com.zym.bean.Instrument;

public interface IPublish {
	String PublishData(List<Instrument> li);
	
	void ShowAllData(List<Instrument> li);
}
