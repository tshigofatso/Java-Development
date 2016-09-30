package com.games.cyko.storelocator.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.games.cyko.storelocator.views.LoginActivity;

import java.util.HashMap;

/**
 * Created by Cyko on 9/29/2016.
 */

public class UserSessionManager {

    SharedPreferences preferences;

    SharedPreferences.Editor editor;

    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREFER_NAME = "StoreLocatorPref";
    //pref keys
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    //Email Adress entered by the user, password is predifined according tot he specification
    public static final String KEY_Email = "email";

    public UserSessionManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor = preferences.edit();
    }

    /***create login session
     * param email - user email entered***/
    public void createUserLoginSession(String email){
        //storing the user email
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_Email, email);
        editor.commit();
    }

    /***checklogin status
     * @return true if user is currently loggied in and false return the inverse***/
    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){
            Intent loginScreen = new Intent(context, LoginActivity.class);
            //CLOSE ALL OPEN ACTIVITIES
            loginScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            //start a new activity
            loginScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(loginScreen);

            return true;
        }else{
            return false;
        }


    }

    /** Get user details
     * @return user detail
     * ***/
    public HashMap<String, String > getUserDetails(){

        HashMap<String, String > user = new HashMap<>();
        user.put(KEY_Email, preferences.getString(KEY_Email, null));

        return user;

    }

    /***** Clease user current session for the store locator application****/
    public void logoutUser(){

        //clear all currently stored shared preferances fro the application
        editor.clear();
        editor.commit();


        Intent loginScreen = new Intent(context, LoginActivity.class);
        //CLOSE ALL OPEN ACTIVITIES
        loginScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //start a new activity
        loginScreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(loginScreen);


    }

    /** Check for login **/
    public boolean isUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }

}
