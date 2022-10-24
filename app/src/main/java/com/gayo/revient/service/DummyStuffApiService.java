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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DummyStuffApiService implements StuffApiService {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Stuff> stuffList = new ArrayList<>();

    @Override
    public List<Stuff> getStuffs() {
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

    public void updateStuff(Stuff stuff){
        db.collection("stuffs").document();
    };


    public Date getReturnDate(Stuff stuff) {
        if (stuff.getBorrower() != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(stuff.getCurrentLoanDate());
            calendar.add(Calendar.DATE, stuff.getInitialLoanPeriodInDay());
            System.out.println(calendar.getTime());
            return calendar.getTime();
        } else {
            return null;
        }
    }

    public long getReturnLoanCountInDays(Stuff stuff) {
        long daysReturnCount = 0;
        Date now = new Date();
        if (stuff.getBorrower() != null) {
            long start = now.getTime();
            long end = getReturnDate(stuff).getTime();
            daysReturnCount = TimeUnit.MILLISECONDS.toDays(Math.abs(end - start));
        } else {
            System.out.println("Error this object is not lent");
        }
        return daysReturnCount;
    }

    public int getPercentLoan(Stuff stuff){
        if (stuff.getBorrower() != null) {
            int returnLoanDate = stuff.getInitialLoanPeriodInDay();
            int nowDate = (int) getReturnLoanCountInDays(stuff);
            return (nowDate * 100 / returnLoanDate);
        } else {
            return 0;
        }
    }

}
