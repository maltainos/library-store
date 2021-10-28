package com.webservice.app.wservice.ui.response;

public enum RequestOperationName {
	
	PUT("Update"),POST("Create"),GET("Retriave"),DELETE("Delete");
	
	private String operatioName;
	
	RequestOperationName(String operationName) {
		this.operatioName = operationName;
	}	
	
	public String getOperationName() {
		return operatioName;
	}
}
