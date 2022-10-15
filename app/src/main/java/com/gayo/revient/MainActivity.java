package com.gayo.revient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.gayo.revient.fragment.StuffListFragment;

public class MainActivity extends AppCompatActivity {

    private StuffListFragment mStuffListFragment;
//    private FrameLayout mMainActivityFrameLayout;
    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShowAndConfigureStuffListFragment();
    }

    public void ShowAndConfigureStuffListFragment() {
        mStuffListFragment = new StuffListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_MainAct, mStuffListFragment).addToBackStack(null).commit();
//        mFragmentManager.beginTransaction().replace(R.id.fl_MainAct, mStuffListFragment)
//                .addToBackStack(null)
//                .commit();
    }

}