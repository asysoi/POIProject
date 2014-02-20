package com.belcci.carnet.model;

public class Job {
	private int id;
	private String name  = "";
	private String ename  = "";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public Job clone() {
		Job job = new Job();
		job.setName(name);
		job.setEname(ename);
		return job;
	}
}
