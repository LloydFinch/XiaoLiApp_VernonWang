package com.xiaoliapp.app.entity;

import com.xiaoliapp.app.utils.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by VennUser on 2015/8/15.
 */
public class ResponseMessage implements JSONParser {
	private String status;
	private String data;

	public void parseJson(String json) throws JSONException {
		JSONObject jsonObject = new JSONObject(json);
		status = jsonObject.getString("status");
		data = jsonObject.getString("data");
	}

	public String getStatus() {
		return status;
	}

	public String getData() {
		return data;
	}

	public String toString() {
		return "ResponseMessage{" +
				"status='" + status + '\'' +
				", data='" + data + '\'' +
				'}';
	}
}
