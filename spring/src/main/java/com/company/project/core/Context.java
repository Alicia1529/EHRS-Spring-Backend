package com.company.project.core;

import com.alibaba.fastjson.JSONObject;
import com.company.project.status.Status;
import com.company.project.status.Status200;

/**
 * 响应结果生成工具
 */
public class Context {
    private Status status;
    
    public JSONObject handleRequest200(boolean success, String message, String data) {
    	status = new Status200();
    	return status.genResult(success, message, data);
    }
    
    public JSONObject handleRequest(int statusCode) {
    	try {
			Class statusClass = Class.forName("com.company.project.status.Status"+Integer.toString(statusCode));
			status = (Status) statusClass.newInstance();
			return status.genResult();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}	
    	return null;
    }
}
