package com.example.dell.wordsapp;

/**
 * Created by DELL on 2016/9/27.
 */
public interface HttpCallBack {
    void onSuccess(String result);
    void onFailure(String exception);
}