package com.nuzharukiya.hzaun_app_base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Nuzha Rukiya on 18/09/11.
 */
public class BaseUtils {

    private Context context;

    private ProgressDialog progressDialog;

    public BaseUtils(Context context) {
        this.context = context;
    }

    /**
     * Creates a short toast given a string resource
     *
     * @param message
     */
    public void makeToast(int message) {
        makeToast(context.getString(message));
    }

    /**
     * Creates a short toast given a string
     *
     * @param message
     */
    public void makeToast(final String message) {
        ((Activity) context).runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getLoader(Context context) {
        progressDialog = new ProgressDialog(context, R.style.AppDialogTheme);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setGravity(Gravity.CENTER);
        progressDialog.show();
    }

    public void dismissLoader() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
