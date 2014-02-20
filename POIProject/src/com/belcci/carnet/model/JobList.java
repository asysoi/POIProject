package com.belcci.carnet.model;

public class JobList extends java.util.ArrayList<Job> {
	private static JobList list;

	private JobList() {
	}

	public static JobList getInstance() {
		if (list == null) {
			list = new JobList();
		}
		return list;
	}

	public Job findJobByName(String name) {
		Job job = null;
		if (name != null) {
			for (Job item : list) {
				if (name.equals(item.getName()) || name.equals(item.getEname())) {
					job = item;
					break;
				}
			}
		}
		return job;
	}

	public Job findjobById(int id) {
		Job job = null;
		for (Job item : list) {
			if (item.getId() == id) {
				job = item;
				break;
			}
		}
		return job;
	}
}
