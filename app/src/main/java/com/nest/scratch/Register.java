package com.nest.scratch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText username,email,password;
    Button register_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        username = (EditText) findViewById(R.id.name_register);
        email = (EditText) findViewById(R.id.email_register);
        password =(EditText) findViewById(R.id.password_register);
        register_button = (Button) findViewById(R.id.register_button);

        register_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        register();
                    }
                }
        );
    }

    public void register(){
        String username = this.username.getText().toString();
        String email =this.email.getText().toString();
        String password = this.password.getText().toString();
        //dbHandler.register(username,email,password);
        Intent i  = new Intent(this,FacebookLoginActivity.class);
        startActivityForResult(i,75);
    }
}
