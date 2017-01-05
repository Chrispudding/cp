package com.feicuiedu.hunttreasure.user.Login;

import android.content.Intent;
import android.os.AsyncTask;

/**
 * Created by Administrator on 2017/1/3.
 */

public class LoginPresenter {
    private LoginInterface loginInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
    }

    public void Login() {
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loginInterface.showProgress();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                loginInterface.hideProgress();
                loginInterface.showMessage("登陆成功");
                loginInterface.navigationToHome();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

}
