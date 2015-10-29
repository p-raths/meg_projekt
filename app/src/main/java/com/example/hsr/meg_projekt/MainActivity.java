package com.example.hsr.meg_projekt;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hsr.meg_projekt.service.Callback;
import com.example.hsr.meg_projekt.service.LibraryService;

public class MainActivity extends OverlayActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = this.getSharedPreferences(
                "server", Context.MODE_PRIVATE);

        String server = prefs.getString("server", "");
        LibraryService.setServerAddress(server);

        activityName = getResources().getString(R.string.title_activity_main);
    }

    public void login(View view){

        EditText usernameEdit = (EditText) findViewById(R.id.username_text);
        final String username = usernameEdit.getText().toString();

        EditText passwordEdit = (EditText) findViewById(R.id.password_text);
        String password = passwordEdit.getText().toString();


        SharedPreferences prefs = this.getSharedPreferences(
                "server", Context.MODE_PRIVATE);

        Log.d("Email", username);
        Log.d("password", password);

        try {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.d("Hide Keyboard","oke...");
        }

        LibraryService.login(username, password, new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {

                if (input) {
                    loginSuccessfull(findViewById(android.R.id.content));

                    TextView headertext = (TextView) findViewById(R.id.drawer_header_login_name);
                    headertext.setText("logged in as: " + username);

                    //Menu setting = (Menu) findViewById(R.id.menu_config);
                    //setting.getItem(1).setTitle(getResources().getString(R.string.drawer_submenu_login));
                } else {
                    loginFailed(findViewById(android.R.id.content));
                    Log.d("Server Response", "Login Failsed");
                }
            }

            @Override
            public void onError(String message) {
                Log.d("Login", message);
                loginFailed(findViewById(android.R.id.content));
            }
        });


    }

    public void loginFailed(View view){
        Snackbar.make(view, "Login Failed", Snackbar.LENGTH_LONG).show();
    }

    public void loginSuccessfull(View view){
        Intent intent = new Intent(this, Item_Overview.class);

        startActivity(intent);
        Log.d("Login", "Works");
    }
    public void register(View view){
        Intent intent = new Intent (this, Registration.class);
        startActivity (intent);
    }

}
