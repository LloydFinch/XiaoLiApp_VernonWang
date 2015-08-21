package com.xiaoliapp.app.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
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
import com.xiaoliapp.app.utils.HttpPostUtils;
import com.xiaoliapp.app.utils.MyLog;
import com.xiaoliapp.app.utils.VerifyUtils;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

public class LoginActivity extends BaseActivity implements View.OnClickListener, Runnable {

	private EditText txtPhoneNumber;
	private EditText txtPassword;
	private TextView textForgetPsw;

	//处理登录请求返回结果,1:成功 0:失败
	private Handler handlerLogin = new Handler() {
		public void handleMessage(Message msg) {
			if (msg != null) {
				int flag = msg.what;
				switch (flag) {
					case 0:
						Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
						break;
					case 1:
						//登录成功,跳转到下一个Activity
						startActivity(new Intent(LoginActivity.this, RelationActivity.class));
						break;
					default:
						break;
				}
				MyLog.d("Login", "status:" + msg.what + "  data:" + (String) msg.obj);
			}
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//更换内容区布局
		init();
	}

	//状态码,用于确定使用哪个功能
	private static int code = -1;

	public void onClick(View v) {
		code = -1;
		switch (v.getId()) {
			case R.id.base_top_back:
				//顶部回退键
				finish();
				break;
			case R.id.base_top_cancel:
				//顶部取消键
				txtPhoneNumber.setText(null);
				txtPassword.setText(null);
				txtPhoneNumber.requestFocus();
				break;
			case R.id.login_button_login:
				//登录
				code = Constants.CHOICE_LOGIN;
				break;
			case R.id.login_button_register:
				//注册
				register();
				break;
			case R.id.login_for_psw:
				//TODO 忘记密码
				code = Constants.CHOICE_FORGET;
				Toast.makeText(this, "未实现", Toast.LENGTH_SHORT).show();
				break;
			case R.id.login_go_look:
				//TODO 去看看
				Toast.makeText(this, "未实现", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}
		if (code > 0) {
			//new Thread(this).start();
			startActivity(new Intent(this, RelationActivity.class));
		}
	}

	//登录
	private void login() {
		String number = txtPhoneNumber.getText().toString().trim();
		String password = txtPassword.getText().toString().trim();
		if (VerifyUtils.verify(number, Constants.STYLE_NUMBER) &&
				VerifyUtils.verify(password, Constants.STYLE_PASSWORD)) {

			//验证通过,发起登录请求
			String path = HttpInterface.LOGIN;
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("mobile", number);
			params.put("password", password);
			//params.put("pincode", "123456");
			String responseContent = null;
			try {
				responseContent = HttpPostUtils.sendByPost(params, path, "utf-8");
				if (responseContent != null) {

					//解析返回的数据
					ResponseMessage responseMessage = new ResponseMessage();
					responseMessage.parseJson(responseContent);
					MyLog.d("Login", "responseMessage:" + responseMessage.toString());
					//发送请求的结果
					Message message = new Message();
					message.what = Integer.parseInt(responseMessage.getStatus());
					message.obj = responseMessage.getData();
					handlerLogin.sendMessage(message);
				} else {
					Toast.makeText(this, "用户信息获取失败!", Toast.LENGTH_SHORT).show();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Toast.makeText(this, "电话号码或密码格式不正确!", Toast.LENGTH_SHORT).show();
		}
	}

	//去注册
	private void register() {
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivityForResult(intent, Constants.CODE_ACTIVITY_LOGIN);
	}

	//处理注册后传回来的数据
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Constants.CODE_ACTIVITY_LOGIN && resultCode == Constants.CODE_ACTIVITY_REGISTER) {
			//TODO 处理data中的数据
		}
	}

	public void run() {
		Looper.prepare();
		switch (code) {
			case Constants.CHOICE_LOGIN:
				login();
				break;
			case Constants.CHOICE_FORGET:
				//TODO 忘记密码的处理
				break;
			default:
				break;
		}
		Looper.loop();
	}

	protected void init() {

		super.setBottomBar(false);
		RelativeLayout content = (RelativeLayout) this.findViewById(R.id.activity_base_content);
		content = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_login_content, content);

		txtPhoneNumber = (EditText) content.findViewById(R.id.login_phone_number);
		txtPassword = (EditText) content.findViewById(R.id.login_password);
		textForgetPsw = (TextView) content.findViewById(R.id.login_for_psw);
		textForgetPsw.setText(Html.fromHtml("<u>" + "忘记密码?" + "</u>"));
	}
}
