package com.pangu.gitizen;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;

public class LoginActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_activity_layout);
        Button bt_login = (Button)findViewById(R.id.btnLogin);
        bt_login.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, MainApplication.class);
				startActivity(intent);
			}
		});
    }
    
}
