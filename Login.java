package com.example.project_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class Login extends AppCompatActivity {
    Button loginBtn1;
    EditText userNameField;
    EditText passwordField;
    String userNameValue;
    String paswordValue;
    Realm realmObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());
        realmObj = Realm.getDefaultInstance();
        setContentView(R.layout.activity_login);
        userNameField = findViewById(R.id.userNameID);
        passwordField = findViewById(R.id.passwordID);
        loginBtn1 = findViewById(R.id.login1);
        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameValue  = userNameField.getText().toString();
                paswordValue  = passwordField.getText().toString();


                if(userNameValue.isEmpty() ){

                    validate("User Name", "Please Enter Valid User Name...");



                }else if ( paswordValue.isEmpty()){

                    validate("Password", "Please Enter Valid Password...");

                }
                else {
                    /*

                    realmObj.beginTransaction();

                    UserModel aUser  = realmObj.createObject(UserModel.class);
                    aUser.setUserName(userNameValue);
                    aUser.setPassword(paswordValue);

                    realmObj.commitTransaction();



                    //query the User Model Table
                    RealmQuery<UserModel> recordlist = realmObj.where(UserModel.class);

                    System.out.println("Number of records "+recordlist.count());

                    RealmResults<UserModel> results = recordlist.findAll();

                    for (UserModel obj : results) {

                        System.out.println("User Name "+obj.getUserName());
                        System.out.println("Password "+obj.getPassword());
                    }

                    */


                    System.out.println("User Entered valid string");
                    validate("DONE", "You have entered correct info...");
                    Intent TabScreen = new Intent(getApplicationContext(),TabActivity.class);
                    TabScreen.putExtra("username",userNameField.getText().toString());
                    startActivity(TabScreen);
                }
            }
        });
    }

    public void validate(String title, String message){

        AlertDialog.Builder dialog = new AlertDialog.Builder(Login.this);
        dialog.setTitle(title);
        dialog.setMessage(message);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
            }
        });
        final AlertDialog alert = dialog.create();
        alert.show();
    }

}
