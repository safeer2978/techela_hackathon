package com.techela.hackathon.db.pdf;

public class Colm {
	private String name;
	private String prn;
	private String branch;
	private String batch;
	
	public Colm(String name,String prn,String branch,String batch) {
		this.name=name;
		this.prn=prn;
		this.branch=branch;
		this.batch=batch;				
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPrn() {
		return this.prn;
	}
	
	public String getBranch() {
		return this.branch;
	}
	
	public String getBatch() {
		return this.batch;
	}
	
	
}
