package com.webservice.app.wservice.ui.response;

public enum RequestOperationStatus {
	
	SUCCESS("Successful request"), FEAILED("Failed request");

	private String operationStatus;

	RequestOperationStatus(String operationStatus){
		this.operationStatus = operationStatus;
	}

	public String getStatusModel() {
		return operationStatus;
	}
}
