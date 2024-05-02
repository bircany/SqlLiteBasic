package com.bircanyilmaz.sqlliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,surname,TC,telNo,dob;
    Button insert,update,delete,view;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        TC = findViewById(R.id.txtTC);
        telNo = findViewById(R.id.telNo);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDell);
        view = findViewById(R.id.btnView);
        DB = new DbHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String surnameTXT = surname.getText().toString();
                String tcTXT = TC.getText().toString();
                String telNoTXT = telNo.getText().toString();
                String dobTXT = dob.getText().toString();
                Boolean checkInsertData = DB.insertUserData(nameTXT,surnameTXT,tcTXT,telNoTXT,dobTXT);
                if(checkInsertData == true){
                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show(); // show() eklenmiş
                }
                else{
                    Toast.makeText(MainActivity.this,"New Entry not Inserted",Toast.LENGTH_SHORT).show(); // show() eklenmiş
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String surnameTXT = surname.getText().toString();
                String tcTXT = TC.getText().toString();
                String telNoTXT = telNo.getText().toString();
                String dobTXT = dob.getText().toString();
                Boolean checkUpdateData = DB.updateUserData(nameTXT,surnameTXT,tcTXT,telNoTXT,dobTXT);
                if(checkUpdateData == true){
                    Toast.makeText(MainActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show(); // show() eklenmiş
                }
                else{
                    Toast.makeText(MainActivity.this,"New Entry not Updated",Toast.LENGTH_SHORT).show(); // show() eklenmiş
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Boolean checkDeleteData = DB.deleteUserData(nameTXT);
                if(checkDeleteData == true){
                    Toast.makeText(MainActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show(); // show() eklenmiş
                }
                else{
                    Toast.makeText(MainActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show(); // show() eklenmiş
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData();

                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this,"No Entry Exist",Toast.LENGTH_SHORT);
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : " + res.getString(0) + "\n" );
                    buffer.append("Surname : " + res.getString(1) + "\n" );
                    buffer.append("TC : " + res.getString(2) + "\n" );
                    buffer.append("telNo : " + res.getString(3) + "\n" );
                    buffer.append("Date Of Birth : " + res.getString(4) + "\n\n" );
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}