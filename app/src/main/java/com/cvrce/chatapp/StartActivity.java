package com.cvrce.chatapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.cvrce.chatapp.ui.login.Login;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mRegBtn;
    private Button mLoginBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mLoginBtn = (Button) findViewById(R.id.start_login);
        mLoginBtn.setOnClickListener(this);

        mRegBtn = (Button) findViewById(R.id.start_reg_btn);
        mRegBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start_reg_btn:
            {
                Intent start = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(start);
                finish();

            }
                break;

            case R.id.start_login:
            {
                Intent Loginstart = new Intent(StartActivity.this, Login.class);
                startActivity(Loginstart);
                finish();

            }
                break;



            default:
                break;
        }

    }
}
