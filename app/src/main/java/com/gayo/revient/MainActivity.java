package com.gayo.revient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gayo.revient.di.DI;
import com.gayo.revient.fragment.DetailFragment;
import com.gayo.revient.fragment.StuffListFragment;
import com.gayo.revient.model.Stuff;
import com.gayo.revient.model.StuffType;
import com.gayo.revient.model.User;
import com.gayo.revient.recyclerView.SimpleStuffListAdapter;
import com.gayo.revient.service.DummyStuffApiService;
import com.gayo.revient.service.DummyUserGenerator;
import com.gayo.revient.service.StuffApiService;
import com.gayo.revient.service.StuffTypeGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity implements StuffListFragment.OnButtonClickedListener, Serializable {

    // Api
    StuffApiService mStuffApiService = DI.getStuffApiService();

    FloatingActionButton mAddButton;
    StuffListFragment mStuffListFragment;

    //Firebase Variables
    FirebaseFirestore mFirestore;
    Stuff mStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Date now = new Date();
//        mStuff = new Stuff("name", "owner", "borrower","0", now, now,50);

        mAddButton = findViewById(R.id.fab_MainAct);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenAddNewStuffActivity();
            }
        });

        // Show Stuff List Fragment -------------------------------------------
        ShowAndConfigureStuffListFragment();
    }

    private void OpenAddNewStuffActivity() {
        Intent intent = new Intent(this, AddNewStuffActivity.class);
        startActivity(intent);
    }


    public void ShowAndConfigureStuffListFragment() {
        mStuffListFragment = new StuffListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_MainAct, mStuffListFragment).commit();
    }

    @Override
    public void onButtonClicked(View view, Stuff stuff) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle stuffBundle = new Bundle();
        stuffBundle.putSerializable("currentStuff", stuff);
        detailFragment.setArguments(stuffBundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_MainAct, detailFragment).addToBackStack(null).commit();
    }
}