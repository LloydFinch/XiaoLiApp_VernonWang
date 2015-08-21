package com.xiaoliapp.app.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.SmsManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaoliapp.app.R;
import com.xiaoliapp.app.constants.Constants;
import com.xiaoliapp.app.constants.HttpInterface;
import com.xiaoliapp.app.entity.ResponseMessage;
import com.xiaoliapp.app.entity.ResponsePinCode;
import com.xiaoliapp.app.utils.HttpPostUtils;
import com.xiaoliapp.app.utils.VerifyUtils;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, Runnable {

	private TextView textGetPinCode;
	private EditText txtPhoneNumber;
	private EditText txtPassword;
	private EditText txtConfirmPsw;
	private EditText txtPinCode;
	private Handler registerHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case 0:
					//TODO 注册失败
					Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
					break;
				case 1:
					//TODO 注册成功
					Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
					Intent intent = getIntent();
					startActivity(intent);
					break;
				default:
					break;
			}
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private int code = -1;

	public void onClick(View v) {
		code = -1;
		switch (v.getId()) {
			case R.id.base_top_back:
				//顶部回退键
				super.onBackPressed();
				break;
			case R.id.login_get_pincode:
				//获取验证码
				code = Constants.CHOICE_PIN_CODE;
				break;
			case R.id.login_button_register:
				//注册
				code = Constants.CHOICE_REGISTER;
				break;
		}
		if (code > -1) {
			new Thread(this).start();
		}
	}

	//注册
	private void register() {
		//TODO 注册的实现
		String phoneNumber = txtPhoneNumber.getText().toString().trim();
		String password = txtPassword.getText().toString().trim();
		String pswConfirm = txtConfirmPsw.getText().toString().trim();
		String pinCode = txtPinCode.getText().toString().trim();
		if (VerifyUtils.verify(phoneNumber, Constants.STYLE_NUMBER) &&
				VerifyUtils.verify(password, Constants.STYLE_PASSWORD) &&
				VerifyUtils.verify(pswConfirm, Constants.STYLE_PASSWORD) &&
				VerifyUtils.verify(pinCode, Constants.STYLE_PIN_CODE)
				) {
			if (password.equals(pswConfirm)) {

				String path = HttpInterface.REGISTER;
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("mobile", phoneNumber);
				params.put("password", password);
				params.put("pincode", pinCode);
				params.put("username", "daydayup");
				//TODO 验证码的验证处理
				try {
					String responseContent = HttpPostUtils.sendByPost(params, path, "utf-8");
					if (responseContent != null) {
						ResponseMessage responseMessage = new ResponseMessage();
						responseMessage.parseJson(responseContent);

						Message message = new Message();
						message.what = Integer.parseInt(responseMessage.getStatus());
						message.obj = responseMessage.getStatus();
						registerHandler.sendMessage(message);
					} else {
						Toast.makeText(this, "连接服务器失败,请重新注册", Toast.LENGTH_SHORT).show();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Toast.makeText(this, "密码确认有误,请重新确认密码!", Toast.LENGTH_SHORT).show();
				txtConfirmPsw.requestFocus();
			}
		} else {
			Toast.makeText(this, "数据输入不合法", Toast.LENGTH_SHORT).show();
		}
	}


	//获取验证码
	private void getPinCode() {
		String phoneNumber = txtPhoneNumber.getText().toString().trim();
		if (VerifyUtils.verify(phoneNumber, Constants.STYLE_NUMBER)) {
			String path = HttpInterface.VERIFY;
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("mobile", phoneNumber);
			//params.put("optime", String.valueOf(System.currentTimeMillis()));
			//params.put("pincode", "123456");
			try {

				//发送请求并解析数据
				String responseContent = HttpPostUtils.sendByPost(params, path, "utf-8");
				ResponsePinCode responsePinCode = new ResponsePinCode();
				responsePinCode.parseJson(responseContent);

				//发送验证码
				//SmsManager smsManager = SmsManager.getDefault();
				//PendingIntent intent = PendingIntent.getBroadcast(this, 0, new Intent(), 0);
				//smsManager.sendTextMessage(phoneNumber, null, "验证码是:" + responsePinCode.getPinCode(), intent, null);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "电话号码不对", Toast.LENGTH_SHORT).show();
		}
	}

	public void run() {
		Looper.prepare();
		switch (code) {
			case Constants.CHOICE_PIN_CODE:
				//获取验证码
				getPinCode();
				break;
			case Constants.CHOICE_REGISTER:
				//注册
				register();
				break;
			default:
				break;
		}
		Looper.loop();
	}

	//布局的初始化
	protected void init() {
		super.setBottomBar(false);
		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_register_content, content);
		textGetPinCode = (TextView) content.findViewById(R.id.login_get_pincode);
		textGetPinCode.setText(Html.fromHtml("<u>" + "获取验证码" + "</u>"));

		this.findViewById(R.id.base_top_cancel).setVisibility(View.INVISIBLE);

		TextView textTitle = (TextView) this.findViewById(R.id.base_top_title);
		textTitle.setText("账号注册");

		txtPhoneNumber = (EditText) this.findViewById(R.id.login_phone_number);
		txtPassword = (EditText) this.findViewById(R.id.login_password);
		txtConfirmPsw = (EditText) this.findViewById(R.id.login_confirm_password);
		txtPinCode = (EditText) this.findViewById(R.id.login_pincode);
	}
}
