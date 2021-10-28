package com.webservice.app.wservice.ui.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class RequestStatusModel {
	
	private String requestOperationName;
	private String requestOperationStatus;
}
