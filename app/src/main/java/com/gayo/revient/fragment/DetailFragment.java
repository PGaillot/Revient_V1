package com.gayo.revient.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gayo.revient.R;
import com.gayo.revient.di.DI;
import com.gayo.revient.model.Stuff;
import com.gayo.revient.model.StuffType;
import com.gayo.revient.model.User;
import com.gayo.revient.service.StuffApiService;
import com.gayo.revient.service.StuffTypeApiService;
import com.gayo.revient.service.UserApiService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DetailFragment extends Fragment {


    ImageView iv_Type;
    TextView tv_name, tv_percent, tv_loanEndDate, tv_owner, tv_borrower, tv_initialLoanDate, tv_loanDuration, tv_StuffType, tv_creationDate;
    EditText et_desc;
    LinearLayout ll_borrower, ll_initialLoanDate, ll_loanDuration;

    //APIServices
    UserApiService mUserApiService;
    StuffTypeApiService mStuffTypeApiService;
    StuffApiService mStuffApiService;

    private Stuff mStuff;
    DateFormat dateFormat_debug = new SimpleDateFormat("dd/MM/yyyy");

    public DetailFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserApiService = DI.getNewInstanceUserApiService();
        mStuffTypeApiService = DI.getNewInstanceStuffTypeApiService();
        mStuffApiService = DI.getStuffApiService();

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
        et_desc = view.findViewById(R.id.et_detailFrag_desc);
        ll_borrower = view.findViewById(R.id.ll_detailFrag_borrower);
        ll_initialLoanDate = view.findViewById(R.id.ll_detailFrag_initialLoanDate);
        ll_loanDuration = view.findViewById(R.id.ll_detailFrag_loanDuration);

        et_desc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mStuff.setDesc(editable.toString());
            }
        });

        Bundle stuffData = getArguments();
        if (stuffData != null) {
            mStuff = (Stuff) getArguments().getSerializable("currentStuff");

            boolean isInLoanPeriod = (mStuff.getBorrower() != null);

            User owner = mUserApiService.getUserById(mStuff.getOwner());
            StuffType stuffType = mStuffTypeApiService.getStuffTypeById(mStuff.getType());
            int iconResource = getResources().getIdentifier(stuffType.getIcon(), "drawable", getContext().getPackageName());

            tv_name.setText(mStuff.getName());
            tv_owner.setText(owner.GetShortName(owner));
            tv_creationDate.setText(dateFormat_debug.format(mStuff.getCreationDate()) + "");
            iv_Type.setImageResource(iconResource);
            tv_StuffType.setText(stuffType.getName());

            if (isInLoanPeriod) {
                tv_loanEndDate.setText(mStuffApiService.getReturnDate(mStuff) + "jours");
                tv_percent.setText(mStuffApiService.getPercentLoan(mStuff) + "%");
                tv_borrower.setText(mStuff.getBorrower());
                tv_initialLoanDate.setText(dateFormat_debug.format(mStuff.getCurrentLoanDate()) + "");
                tv_loanDuration.setText(mStuff.getInitialLoanPeriodInDay() + "j");
            } else {
                tv_percent.setVisibility(view.INVISIBLE);
                tv_loanEndDate.setVisibility(view.INVISIBLE);
                ll_borrower.setVisibility(view.GONE);
                ll_initialLoanDate.setVisibility(view.GONE);
                ll_loanDuration.setVisibility(view.GONE);
            }

        } else {
            System.out.println("Error, bundle is empty");
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // TODO Update current Stuff
        mStuffApiService.updateStuff(mStuff);
        System.out.println(mStuff.getName() + " detail Fragment is destroy.");
    }
}