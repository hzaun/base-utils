package com.nuzharukiya.hzaun_app_base;

/**
 * Created by Nuzha Rukiya on 18/01/10.
 */

/**
 * Base UI used in all activities
 */

public interface UIBase {

    /**
     * Set the context and init UIComponents
     */
    void initApp();

    /**
     * Find views and set their values
     */
    void initViews();

    /**
     * Initialize the data
     */
    void initData();

    /**
     * Start doing the work
     */
    void runFactory();
}
