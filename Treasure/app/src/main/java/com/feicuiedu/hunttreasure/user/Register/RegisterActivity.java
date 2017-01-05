package com.feicuiedu.hunttreasure.user.Register;

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

public class RegisterActivity extends AppCompatActivity implements RegisterInterface {
    private Toolbar toolbar;
    private EditText et_username, et_password, et_confirm;
    private Button btn_register;
    private String confirm;
    private String password;
    private String username;
    private ActivityUtils activityUtils;
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        activityUtils = new ActivityUtils(this);
        et_username = (EditText) findViewById(R.id.et_Username);
        et_password = (EditText) findViewById(R.id.et_Password);
        et_confirm = (EditText) findViewById(R.id.et_Confirm);
        et_username.addTextChangedListener(l);
        et_password.addTextChangedListener(l);
        et_confirm.addTextChangedListener(l);
        btn_register = (Button) findViewById(R.id.btn_Register);
        btn_register.setOnClickListener(click);
    }

    private TextWatcher l = new TextWatcher() {


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
            confirm = et_confirm.getText().toString();
            boolean isOK = !(TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) && TextUtils.equals(password, confirm);
            btn_register.setEnabled(isOK);
        }
    };

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.register);
        }
//        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                RegisterActivity.this.finish();
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_Register:
                    if (RegexUtils.verifyUsername(username) != RegexUtils.VERIFY_SUCCESS) {
                        Dialog.getInstanse(getString(R.string.username_error), getString(R.string.username_rules))
                                .show(getSupportFragmentManager(), "usernameError");
                        return;
                    }
                    if (RegexUtils.verifyPassword(password) != RegexUtils.VERIFY_SUCCESS) {
                        Dialog.getInstanse(getString(R.string.password_error), getString(R.string.password_rules))
                                .show(getSupportFragmentManager(), "passwordError");
                        return;
                    }
                    new RegisterPresenter(RegisterActivity.this).register();
            }
        }
    };

    @Override
    public void navigationToHome() {
        activityUtils.startActivity(HomeActivity.class);
        finish();
        //本地广播
        Intent intent = new Intent(MainActivity.MAIN_ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void showProgress() {
        dialog = ProgressDialog.show(this, "注册", "正在注册,请稍后~");

    }
}
