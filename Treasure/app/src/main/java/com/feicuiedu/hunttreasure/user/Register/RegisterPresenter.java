package com.feicuiedu.hunttreasure.user.Register;

import android.os.AsyncTask;

/**
 * Created by Administrator on 2017/1/3.
 */

public class RegisterPresenter {
    private  RegisterInterface registerInterface;

    public RegisterPresenter(RegisterInterface registerInterface) {
        this.registerInterface = registerInterface;
    }

    public void register(){
        new AsyncTask<Void,Integer,Void>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                registerInterface.showProgress();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                registerInterface.hideProgress();
                registerInterface.showMessage("注册成功");
                registerInterface.navigationToHome();
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
