package com.xiaoliapp.app.utils;

import com.xiaoliapp.app.constants.Constants;

/**
 * Created by VennUser on 2015/8/14.
 */
public final class VerifyUtils {
	private VerifyUtils() {

	}

	//登录验证
	//type表示验证格式,STYLE_NUMBER表示电话号码格式验证,STYLE_PASSWORD表示密码格式验证
	public static boolean verify(String content, int type) {
		boolean isVerified = true;
		boolean isNull = content == null ? true : false;
		switch (type) {
			case Constants.STYLE_NUMBER:
				if (!isNull && content.length() == 11) {
					isVerified = content.matches("[0-9]+");
				} else
					isVerified = false;
				break;
			case Constants.STYLE_PASSWORD:
				if (!isNull && content.length() >= 6 && content.length() <= 12) {
					isVerified = true;
				} else
					isVerified = false;
				break;
			case Constants.STYLE_PIN_CODE:
				if (!isNull && content.length() == 4) {
					isVerified = true;
				} else
					isVerified = false;
				break;
			default:
				//TODO 不合法的格式处理
				break;
		}
		return isVerified;
	}
}
