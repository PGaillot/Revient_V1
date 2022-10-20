package com.gayo.revient.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gayo.revient.R;
import com.gayo.revient.model.Stuff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DetailFragment extends Fragment {


    ImageView iv_Type;
    TextView tv_name, tv_percent, tv_loanEndDate, tv_owner, tv_borrower, tv_initialLoanDate, tv_loanDuration, tv_StuffType, tv_creationDate;
    EditText ed_desc;
    LinearLayout ll_borrower, ll_initialLoanDate, ll_loanDuration;
    FloatingActionButton addFab;

    private Stuff mStuff;

    public DetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        iv_Type = view.findViewById(R.id.iv_detailFrag_headBar_type);
        tv_name = view.findViewById(R.id.tv_detailFrag_headBar_name);
        tv_percent = view.findViewById(R.id.tv_detailFrag_headBar_percent);
        tv_loanEndDate = view.findViewById(R.id.tv_detailFrag_loanEndDate);
        tv_owner = view.findViewById(R.id.tv_detailFrag_owner);
        tv_borrower = view.findViewById(R.id.tv_detailFrag_borrower);
        tv_initialLoanDate = view.findViewById(R.id.tv_detailFrag_initialLoanDate);
        tv_loanDuration = view.findViewById(R.id.tv_detailFrag_loanDuration);
        tv_StuffType = view.findViewById(R.id.tv_detailFrag_stuffType);
        tv_creationDate = view.findViewById(R.id.tv_detailFrag_creationDate);
        ed_desc = view.findViewById(R.id.et_detailFrag_desc);
        ll_borrower = view.findViewById(R.id.ll_detailFrag_borrower);
        ll_initialLoanDate = view.findViewById(R.id.ll_detailFrag_initialLoanDate);
        ll_loanDuration = view.findViewById(R.id.ll_detailFrag_loanDuration);



        DateFormat dateFormat_debug = new SimpleDateFormat("dd/MM/yyyy");

        Bundle stuffData = getArguments();
        if (stuffData != null) {
            mStuff = (Stuff) getArguments().getSerializable("currentStuff");
            tv_name.setText(mStuff.getName());
            tv_owner.setText(mStuff.getOwner());
            if (mStuff.getBorrower() != null) {
                tv_borrower.setText(mStuff.getBorrower());
            } else {
                ll_borrower.setVisibility(view.GONE);
            }

            if (mStuff.getCurrentLoanDate() != null) {
                tv_initialLoanDate.setText(dateFormat_debug.format(mStuff.getCurrentLoanDate()) + "");
            } else  {
                ll_initialLoanDate.setVisibility(view.GONE);
            }

            if (mStuff.getInitialLoanPeriodInDay() > 0) {
                tv_loanDuration.setText(mStuff.getInitialLoanPeriodInDay() + "");
            } else  {
                ll_loanDuration.setVisibility(view.GONE);
            }
            tv_StuffType.setText(mStuff.getType());


        } else {
            System.out.println("Error, bundle is empty");
        }

        return view;
    }
}