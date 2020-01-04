package com.company.project.status;

import com.alibaba.fastjson.JSONObject;

public interface Status {
	
	public JSONObject genResult(
			boolean success, String message, String data
	);
	public JSONObject genResult();

}




