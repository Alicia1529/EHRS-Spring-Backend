package com.company.project.status;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 统一API响应结果封装
 */
public class Status404 implements Status {

	@Override
	public JSONObject genResult(boolean success, String message, String data) {
		// TODO Auto-generated method stub
		JSONObject object = new JSONObject();
		object.put("code", 404);
		object.put("success", success);
		object.put("message", message);
		object.put("data", data);
		return object;
	}

	@Override
	public JSONObject genResult() {
		// TODO Auto-generated method stub
		return this.genResult(false, "Page not found.", null);
	}
  

}
