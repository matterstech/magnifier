package com.inovia.magnifier.reports;

import com.inovia.magnifier.database.objects.Entity;

public class ReportEntry {
	private Entity entity;
	private Boolean isSuccess;
	
	public ReportEntry(Entity entity, Boolean isSuccess) {
		this.entity = entity;
		this.isSuccess = isSuccess;
	}
	
	public Boolean isSuccess() {
		return isSuccess;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public String toString() {
		return "{ entity: {" + entity.toString() + "}, success: " + isSuccess + "}";
	}
}
