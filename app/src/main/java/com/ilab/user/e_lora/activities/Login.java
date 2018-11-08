package com.ilab.user.e_lora.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ilab.user.e_lora.R;

public class Login extends AppCompatActivity {

    private static final String TAG = Login.class.getSimpleName();

    //views
    private Button login_btn_login;
    private EditText username_edittext, password_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn_login = findViewById(R.id.login_btn_login);

        username_edittext = findViewById(R.id.username_edit_txt);
        password_edittext = findViewById(R.id.password_edit_txt);

        login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login(){
        String username = username_edittext.getText().toString();
        String password = password_edittext.getText().toString();

        //authentication
        if(username.equals(getResources().getString(R.string.uname))){
            if (password.equals(getResources().getString(R.string.upass))){
                Intent i = new Intent(Login.this, MainActivity.class);
                i.putExtra("user", username);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(), "invalid credentials", Toast.LENGTH_LONG).show();
            }

        }else if (username.equals(getResources().getString(R.string.uname1))){
            if (password.equals(getResources().getString(R.string.upass))){
                Intent i = new Intent(Login.this, MainActivity.class);
                i.putExtra("user", username);
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Invalid login", Toast.LENGTH_LONG).show();
            }
        }

    }
}
