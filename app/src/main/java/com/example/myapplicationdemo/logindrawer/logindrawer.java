package com.example.myapplicationdemo.logindrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplicationdemo.R;

public class logindrawer extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    SharedPreferences p;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String tname="name";
    public static final String mail="mail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindrawer);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.mail);
        b1=(Button) findViewById(R.id.submit);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=p.edit();
                String user=e1.getText().toString();
                String pass=e2.getText().toString();
                if(user.length()==0)
                {
                    e1.setError("Invalid name");
                }
                else if (pass.length()==0)
                {
                    e2.setError("invalid mail");
                }
                else {
                    ed.putString(tname,user);
                    ed.putString(mail,pass);
                    ed.apply();
                    Toast.makeText(logindrawer.this, "LOGIN SUCCESSFULL", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(logindrawer.this,navi.class);
                    startActivity(i);
                }
            }
        });

    }
}
