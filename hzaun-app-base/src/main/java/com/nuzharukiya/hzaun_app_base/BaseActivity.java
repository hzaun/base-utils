package com.nuzharukiya.hzaun_app_base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nuzha Rukiya on 18/09/11.
 */
public abstract class BaseActivity extends AppCompatActivity implements
        UIBase {

    protected Context context;

    protected UIComponents uiComponents;
    protected BaseUtils baseUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initApp();
        initViews();
        initData();
        runFactory();
    }

    protected void startNextActivity(Class<?> nextClass) {
        startActivity(new Intent(context, nextClass));
    }

    @Override
    public void initApp() {
        context = this;

        baseUtils = new BaseUtils(context);
    }

    @Override
    public void initViews() {
//        ButterKnife.bind((Activity) context);
    }

    @Override
    public void initData() {
    }

    @Override
    public void runFactory() {
    }

    public BaseUtils getBaseUtils() {
        return baseUtils;
    }
}
