package com.ilab.user.e_lora.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.ilab.user.e_lora.R;

public class Login extends AppCompatActivity {

    //views
    private Button login_btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn_login = findViewById(R.id.login_btn_login);

        login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Home.class));
            }
        });
    }
}
