package com.higradius;

import java.util.Date;

public class InvoiceData {               
	
	private int key;
	private String name_customer;
	private String cust_number;
	private Date clear_date;
	private Date due_in_date;
	private float total_open_amount;
	private float invoice_id;
	private String note;
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public InvoiceData() {
		
	}
	
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
	public String getCust_number() {
		return cust_number;
	}
	public void setCust_number(String cust_number) {
		this.cust_number = cust_number;
	}
	public Date getClear_date() {
		return clear_date;
	}
	public void setClear_date(Date clear_date) {
		this.clear_date = clear_date;
	}
	public Date getDue_in_date() {
		return due_in_date;
	}
	public void setDue_in_date(Date due_in_date) {
		this.due_in_date = due_in_date;
	}
	public float getTotal_open_amount() {
		return total_open_amount;
	}
	public void setTotal_open_amount(float total_open_amount) {
		this.total_open_amount = total_open_amount;
	}
	public float getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(float invoice_id) {
		this.invoice_id = invoice_id;
	}
	
}





















