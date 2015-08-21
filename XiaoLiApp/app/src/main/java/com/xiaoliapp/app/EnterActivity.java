package com.xiaoliapp.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import com.xiaoliapp.app.activity.LoginActivity;

public class EnterActivity extends FragmentActivity {
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Intent intent = new Intent(EnterActivity.this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter);
		new Thread() {
			public void run() {
				try {
					Thread.sleep(3000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendMessage(new Message());
			}
		}.start();
	}
}
