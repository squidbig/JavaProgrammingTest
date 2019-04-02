package com.zym.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Instrument {

	private String code = "";
	private String exchangeCode = "";
	private String publisher = "";
	private LocalDate lastTradingDate = LocalDate.now();
	private LocalDate deliveryDate = LocalDate.now();
	private String market = "";
	private String label = "";
	private boolean tradable = true;

	private DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getLastTradingDate() {
		return lastTradingDate;
	}

	public void setLastTradingDate(LocalDate lastTradingDate) {
		this.lastTradingDate = lastTradingDate;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getMarket() {
		return market;
	}
	
	public String getCutedMarket() {
		if (this.market.contains("_")) {
			return market.substring(market.lastIndexOf("_") + 1, market.length());
		}
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getExchangeCode() {
		return exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public boolean isTradable() {
		return tradable;
	}

	public void setTradable(boolean tradable) {
		this.tradable = tradable;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		// sb.append("|\tLAST_TRADING_DATE\t|\tDELIVERY_DATE\t|\tMARKET\t|\tLABEL\t|\tEXCHANGE_CODE\t|\tTRADABLE\t|\r\n");
		sb.append("|\t" + publisher + "\t|\t" + dTF.format(lastTradingDate)).append("\t|\t" + dTF.format(deliveryDate))
				.append("\t|\t" + getCutedMarket()).append("\t|\t" + label).append("\t|\t" + exchangeCode)
				.append("\t|\t" + tradable + "\t|");

		return sb.toString();
	}

}
