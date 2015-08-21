package com.xiaoliapp.app.entity;

import com.xiaoliapp.app.utils.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by VennUser on 2015/8/15.
 */
public class ResponsePinCode implements JSONParser {
	private String opTime;
	private String pinCode;

	public void parseJson(String json) throws JSONException {
		JSONObject jsonObject = new JSONObject(json);
		opTime = jsonObject.getString("optime");
		pinCode = jsonObject.getString("pincode");
	}

	public String getOpTime() {
		return opTime;
	}

	public String getPinCode() {
		return pinCode;
	}

	public String toString() {
		return "ResponsePinCode{" +
				"opTime='" + opTime + '\'' +
				", pinCode='" + pinCode + '\'' +
				'}';
	}
}
