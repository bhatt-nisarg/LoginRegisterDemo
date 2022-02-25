package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText username,email,password,repassword;
    Button register;
    TextView login;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        username = (EditText) findViewById(R.id.reguser);
        email = (EditText) findViewById(R.id.regemail);
        password = (EditText) findViewById(R.id.regpassword);
        repassword = (EditText)findViewById(R.id.retypepass);
        register = (Button) findViewById(R.id.Regbtn);
        login = (TextView) findViewById(R.id.Login);
        DB = new DbHelper(this);



        register.setOnClickListener(view -> {

            String user = username.getText().toString();
            String useremail = email.getText().toString();
            String userpassword = password.getText().toString();
            String userrepass = repassword.getText().toString();

            if(username.getText().length() < 8){
               username.setError("Enter atleast 8 Character password");
            }
            if (!(email.getText().toString().trim().matches(emailPattern))){
                email.setError("Enter Valid Email");
            }

            if (user.equals("") || useremail.equals("") || userpassword.equals("") || userrepass.equals("")){
                Toast.makeText(getApplicationContext(),"Please enter all the fields",Toast.LENGTH_LONG).show();
            }
            else {
                if (userpassword.equals(userrepass)){
                    Boolean checkuser = DB.checkusername(user);
                    if (checkuser == false){
                        Boolean insert = DB.insertdata(user,useremail,userpassword);
                        if (insert == true){
                            Toast.makeText(RegisterUser.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(RegisterUser.this,"Registration Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterUser.this,"User already exists! Please Sign in",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(RegisterUser.this,"Password not matching!",Toast.LENGTH_LONG).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterUser.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}