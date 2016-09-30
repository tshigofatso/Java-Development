package com.games.cyko.storelocator.views;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.games.cyko.storelocator.R;
import com.games.cyko.storelocator.controller.UserSessionManager;

public class LoginActivity extends AppCompatActivity {

    private EditText txt_email, txt_passsword;
    private Button btn_login;

    //create a session
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSessionManager(getApplicationContext());

        btn_login = (Button) findViewById(R.id.btn_login);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_passsword = (EditText) findViewById(R.id.txt_password);
    }

    /** Called when the user clicks on Login ****/
    public void loginClicked(View view){

        //get all the text imput and validate it
        String email  = txt_email.getText().toString();
        String password = txt_passsword.getText().toString();

            //validate teh username and passord
            if((email.trim().length() > 0 && password.trim().length() > 0 ) || !email.isEmpty() ||!password.isEmpty() ){
                //validate email address and password
                if(!TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()  && password.equals("magic")){
                    //creating a login session
                    session.createUserLoginSession(email);

                    //start store list activity
                    Intent storelistscreen = new Intent(getApplicationContext(), StoreListActivity.class);
                    storelistscreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    storelistscreen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(storelistscreen);
                    finish();


                }else{
                    Toast.makeText(getApplicationContext(),"Username/Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Username/Password is incorrect", Toast.LENGTH_SHORT).show();
            }



    }

    /** Called when the user clicks on Register ****/
    public void registerCliked(View view){
        Intent android_serach = new Intent(Intent.ACTION_SEARCH);
        android_serach.putExtra(SearchManager.QUERY,"http://android.com");
        if(android_serach.resolveActivity(getPackageManager()) != null){
            startActivity(android_serach);
        }else{
            Toast.makeText(this,"Could not initiate search, please reset application",Toast.LENGTH_LONG);
        }
    }
}
