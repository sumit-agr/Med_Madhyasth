package com.cvrce.chatapp.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cvrce.chatapp.MainActivity;
import com.cvrce.chatapp.R;
import com.cvrce.chatapp.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button lBtn;
    private Button RBtn;
    private EditText lemail;
    private EditText lpass;
    private FirebaseAuth mAuth;
    private Login activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        lemail = (EditText) findViewById(R.id.login_email);
        lpass = (EditText) findViewById(R.id.login_pass);
        lBtn = (Button) findViewById(R.id.login_btn);
        lBtn.setOnClickListener(this);
        RBtn = (Button) findViewById(R.id.login_regbtn);
        RBtn.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }


//        lBtn.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.login_btn:
                {
                    String loginemail = lemail.getText().toString();
                String loginpass = lpass.getText().toString();

                if(!TextUtils.isEmpty(loginemail)||!TextUtils.isEmpty(loginpass))
                {
                    login_user(loginemail,loginpass);
                }

                }
                break;
                case R.id.login_regbtn:
                {
                    Intent i = new Intent(Login.this,RegisterActivity.class);
                    startActivity(i);


                }
                break;
                default:
                    break;
            }

          }


//
//                String loginemail = lemail.getText().toString();
//                String loginpass = lpass.getText().toString();
//
//                if(!TextUtils.isEmpty(loginemail)||!TextUtils.isEmpty(loginpass))
//                {
//                    login_user(loginemail,loginpass);
//                }
//
//            }
//        });
//    }

    private void login_user(String loginemail, String loginpass) {

    mAuth.signInWithEmailAndPassword(loginemail,loginpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if(task.isSuccessful())
            {

                Intent rIntent = new Intent(Login.this, MainActivity.class);
                startActivity(rIntent);
                finish();
            }

            else
            {
                Toast.makeText(Login.this,"You got some Error..",Toast.LENGTH_LONG).show();
            }

        }
    });
    }


//
//    public void Click(View view) {
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(activity,RegisterActivity.class);
//                startActivity(i);
//            }
//        });
//    }
}
