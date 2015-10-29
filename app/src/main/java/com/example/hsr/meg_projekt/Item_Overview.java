package com.example.hsr.meg_projekt;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hsr.meg_projekt.service.Callback;
import com.example.hsr.meg_projekt.service.LibraryService;

public class Item_Overview extends OverlayActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item__overview);

        activityName = getResources().getString(R.string.title_activity_item_overview);
    }

    public void onBackPressed(){
        LibraryService.logout(new Callback<Boolean>() {
            @Override
            public void onCompletion(Boolean input) {
                if (input) {
                    logoutSuccessfull(findViewById(android.R.id.content));
                } else {
                    logoutFailed(findViewById(android.R.id.content));
                }
            }

            @Override
            public void onError(String message) {
                logoutFailed(findViewById(android.R.id.content));
            }
        });
    }

    public void logoutSuccessfull(View view){
        Snackbar.make(view, "Logout Successfull", Snackbar.LENGTH_LONG).show();
        this.finish();

    }

    public void logoutFailed(View view){
        Snackbar.make(view, "Logout Successfull", Snackbar.LENGTH_LONG).show();

    }

}
