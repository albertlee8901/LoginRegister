package com.example.loginregister;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText etName,etAge,etUsername;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(authenticate()) {
            displayUserDetails();
        }else{
            Toast.makeText(this, "Not login yet", Toast.LENGTH_LONG).show();
        }

    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    public void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();

        etName.setText(user.getName());
        etAge.setText(user.getAge() + "");
        etUsername.setText(user.getUsername());
    }

    public void logoutClicked(View view) {
        userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);

        startActivity(new Intent(this, Login.class));
    }

    public void loginClicked(View view) {
        startActivity(new Intent(MainActivity.this, Login.class));
    }

}
