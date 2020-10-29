package com.example.registerlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText mail,password;
    Button login;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mail=(EditText)findViewById(R.id.mail_login);
        password=(EditText)findViewById(R.id.password_login);
        login=(Button)findViewById(R.id.login_button);


        dataBaseHelper=new DataBaseHelper(this);

 login.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {

         String maill= mail.getText().toString();
         String pass= password.getText().toString();

         Boolean result= dataBaseHelper.login(maill,pass);


         if(maill.equals("") || pass.equals(""))
         {
             Toast.makeText(getApplicationContext(),"Fields are Empty",Toast.LENGTH_SHORT).show();
         }

         else if (result==true)
         {
             Toast.makeText(getApplicationContext(),"Log in Successful",Toast.LENGTH_SHORT).show();
             Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
             intent.putExtra("data",maill);
             startActivity(intent);
         }
         else {

             Toast.makeText(getApplicationContext(),"Log in UnSuccessful",Toast.LENGTH_SHORT).show();
         }
     }
 });



    }
}
