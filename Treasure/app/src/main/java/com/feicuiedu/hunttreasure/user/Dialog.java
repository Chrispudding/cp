package com.feicuiedu.hunttreasure.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.feicuiedu.hunttreasure.R;

/**
 * Created by Administrator on 2017/1/3.
 * 防止横竖屏切换对对话框的影响
 */
public class Dialog extends DialogFragment {
    public static Dialog getInstanse(String title,String message){
        Dialog dialog =  new Dialog();
        Bundle bundle = new Bundle();
        bundle.putString("key_title",title);
        bundle.putString("key_message",message);
        dialog.setArguments(bundle);
        return dialog;
    }
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("key_title");
        String message = getArguments().getString("key_message");
        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
    }
}
