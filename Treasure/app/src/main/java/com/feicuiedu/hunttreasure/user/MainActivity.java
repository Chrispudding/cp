package com.feicuiedu.hunttreasure.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.feicuiedu.hunttreasure.R;
import com.feicuiedu.hunttreasure.commons.ActivityUtils;
import com.feicuiedu.hunttreasure.user.Login.LoginActivity;
import com.feicuiedu.hunttreasure.user.Register.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    public static final String MAIN_ACTION = "navigationToHome";
    private ActivityUtils activityUtils;
    private Button btn_login;
    private Button btn_register;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityUtils = new ActivityUtils(this);
        btn_login = (Button) findViewById(R.id.btn_Login);
        btn_register = (Button) findViewById(R.id.btn_Register);
        btn_login.setOnClickListener(l);
        btn_register.setOnClickListener(l);
        IntentFilter intentFilter = new IntentFilter(MAIN_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,intentFilter);

    }


    private View.OnClickListener l = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_Login:
                    activityUtils.startActivity(LoginActivity.class);
                    break;
                case R.id.btn_Register:
                    activityUtils.startActivity(RegisterActivity.class);
                    break;
            }
        }
    };
}
