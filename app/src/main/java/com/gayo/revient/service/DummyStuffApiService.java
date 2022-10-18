package com.gayo.revient.service;

import androidx.annotation.NonNull;

import com.gayo.revient.model.Stuff;
import com.gayo.revient.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DummyStuffApiService implements StuffApiService {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public List<Stuff> getStuffs() {
        List<Stuff> stuffList = new ArrayList<>();
        db.collection("stuffs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println("Stuffs loaded. " + document.getData().get("name"));
                                Stuff cStuff = new Stuff(
                                        String.format(document.getData().get("name").toString()),
                                        String.format(document.getData().get("owner").toString()),
                                        String.format(document.getData().get("borrower").toString()),
                                        String.format(document.getData().get("type").toString()),
                                        (Date) document.getData().get("creationDate"),
                                        (Date) document.getData().get("currentLoanDate"),
                                        (int) document.getData().get("initialLoanPeriodInDay")
                                );
                                stuffList.add(cStuff);
                            }
                        } else {
                            System.out.println("ERROR : Stuffs is not loaded.");
                        }
                    }
                });

        return stuffList;
    }
}
