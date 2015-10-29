package com.example.hsr.meg_projekt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hsr.meg_projekt.domain.Loan;
import com.example.hsr.meg_projekt.service.Callback;
import com.example.hsr.meg_projekt.service.LibraryService;

import java.util.List;

public class Loaned_Items extends OverlayActivity {


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loaned_items);
        try {
            LibraryService.getLoansForCustomer(new Callback<List<Loan>>() {
                @Override
                public void onCompletion(List<Loan> input) {

                }

                @Override
                public void onError(String message) {
                    Toast.makeText(Loaned_Items.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IllegalStateException e) {
            Intent intent = new Intent(Loaned_Items.this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.d("Exception", "getLoansForCustomer");
        }

        activityName = getResources().getString(R.string.title_activity_loaned_items);
        setHeadertext();
    }



}
