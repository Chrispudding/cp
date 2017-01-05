package com.feicuiedu.hunttreasure.user.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.feicuiedu.hunttreasure.user.Dialog;
import com.feicuiedu.hunttreasure.treasure.HomeActivity;
import com.feicuiedu.hunttreasure.R;
import com.feicuiedu.hunttreasure.commons.ActivityUtils;
import com.feicuiedu.hunttreasure.commons.RegexUtils;
import com.feicuiedu.hunttreasure.user.MainActivity;

/**
 * Created by Administrator on 2017/1/3.
 */

public class LoginActivity extends AppCompatActivity implements LoginInterface{
    private EditText et_username;
    private EditText et_password;
    private Toolbar toolbar;
    private Button btn_login;
    private String username;
    private String password;
    private ProgressDialog dialog;
    private ActivityUtils activityUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activityUtils = new ActivityUtils(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        et_username = (EditText) findViewById(R.id.et_Username);
        et_password = (EditText) findViewById(R.id.et_Password);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.login));
        }
        et_username.addTextChangedListener(l);
        et_password.addTextChangedListener(l);
        btn_login = (Button) findViewById(R.id.btn_Login);
        btn_login.setOnClickListener(click);
    }
    public TextWatcher l = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            username = et_username.getText().toString();
            password = et_password.getText().toString();
            boolean isOk = !(TextUtils.isEmpty(username)||TextUtils.isEmpty(password));
            btn_login.setEnabled(isOk);

        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_Login:
                    if(RegexUtils.verifyUsername(username)!=RegexUtils.VERIFY_SUCCESS){
                        Dialog.getInstanse(getString(R.string.username_error),getString(R.string.username_rules))
                                .show(getSupportFragmentManager(),"username");
                        return;
                    }
                    if(RegexUtils.verifyPassword(password)!=RegexUtils.VERIFY_SUCCESS){
                        Dialog.getInstanse(getString(R.string.password_error),getString(R.string.password_rules))
                        .show(getSupportFragmentManager(),"password");
                        return;
                    }
                    new LoginPresenter(LoginActivity.this).Login();
            }
        }
    };

    @Override
    public void showProgress() {
        dialog = ProgressDialog.show(this,"登录","正在登录,请稍后~");
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast("登录成功");
    }

    @Override
    public void navigationToHome() {
        activityUtils.startActivity(HomeActivity.class);
        finish();
        //本地广播
        Intent intent = new Intent(MainActivity.MAIN_ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
