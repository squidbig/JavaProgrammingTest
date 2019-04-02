package com.zym.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.zym.bean.Instrument;
import com.zym.imp.DemoPublishImp;
import com.zym.interf.IPublish;

public class LogicControl {
	// Set LogicControl as singleton to make sure it has only one instance
	private static LogicControl instance = new LogicControl();
	private LogicControl() {}
	public static LogicControl getInstance() {
		return instance;
	}

	public void Control(List<Instrument> li) {
		IPublish ip=new DemoPublishImp();
		ip.ShowAllData(li);
		ip.PublishData(li);
	}
}
