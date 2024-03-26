package com.touheed.ecommerceserver.entities;

import java.util.List;

public class BulkDeleteResponse {
	
	private List<Integer> successDeleted;

	public BulkDeleteResponse() {
		super();
	}

	public BulkDeleteResponse(List<Integer> successDeleted) {
		super();
		this.successDeleted = successDeleted;
	}

	public List<Integer> getSuccessDeleted() {
		return successDeleted;
	}

	public void setSuccessDeleted(List<Integer> successDeleted) {
		this.successDeleted = successDeleted;
	}


}
