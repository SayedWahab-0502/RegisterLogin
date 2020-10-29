package com.example.registerlogin;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Currency;

public class MainActivity extends AppCompatActivity {

    EditText username,mail,password,confirm_password;
    Button register,Login,retrieve;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username=(EditText)findViewById(R.id.username_register);
        mail=(EditText)findViewById(R.id.mail_register);
        password=(EditText)findViewById(R.id.password_register);
        confirm_password=(EditText)findViewById(R.id.confirm_password);

        register=(Button)findViewById(R.id.register);
        Login=(Button)findViewById(R.id.login_now_if_already);
        retrieve=(Button)findViewById(R.id.retrieve);

        dataBaseHelper=new DataBaseHelper(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1= username.getText().toString();
                String s2=mail.getText().toString();
                String s3=password.getText().toString();
                String s4=confirm_password.getText().toString();


                if (s1.equals("")||s2.equals("")||s3.equals("") || s4.equals(""))   //1st all fields will be checked
                {
                    Toast.makeText(getApplicationContext(),"Fields are Empty",Toast.LENGTH_LONG).show();
                }

                else if (s3.equals(s4))                                 //2nd password and confirm password will be checked
                {
                    Boolean checkmail=dataBaseHelper.checkmail(s2);

                    if (checkmail==true)                                  //3rd mail will be checked
                    {
                        Boolean insert=dataBaseHelper.insert(s1,s2,s3);

                        if (insert==true)                                   //4th data will get inserted
                        {
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();

                        } //2nd if
                    } //1st if

                    else {
                        Toast.makeText(getApplicationContext(),"Email Already Exist",Toast.LENGTH_SHORT).show();
                    }
                }

                else{
                    Toast.makeText(getApplicationContext(),"Password do not Match",Toast.LENGTH_SHORT).show();
                }

            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor=dataBaseHelper.getdata();

                if (cursor.getCount()>0)
                {
                    StringBuffer stringBuffer = new StringBuffer();

                    while (cursor.moveToNext()) {
                        stringBuffer.append("user :" + cursor.getString(0) + "\n");
                        stringBuffer.append("mail :" + cursor.getString(1) + "\n");
                        stringBuffer.append("password :" + cursor.getString(2) + "\n");
                        }
                    showMessage("Data's entered by the user", stringBuffer.toString());
                }

            }
        });
    }

    public void showMessage(String title,String mssge)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setMessage(mssge);
        builder.show();
    }
}
