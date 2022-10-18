package com.gayo.revient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.gayo.revient.model.Stuff;
import com.gayo.revient.model.StuffType;
import com.gayo.revient.model.User;
import com.gayo.revient.service.DummyUserGenerator;
import com.gayo.revient.service.StuffTypeGenerator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNewStuffActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // Firestore
    FirebaseFirestore db;

    Spinner mTypeSpinner;
    SeekBar mDurationSeekBar;
    EditText mNameEt;
    AutoCompleteTextView mBorrowerACTV;
    TextView mDurationTV;
    Button mValidateButton;
    String mName, mOwner, mBorrower, typeId;
    List<StuffType> mStuffTypeList;
    List<String> mTypesNames;
    String mTypeId;
    Date mCreationDate, mCurrentLoanDate;
    int mInitialLoanPeriodInDay;
    boolean mNeedDuration;


    //-------------------------------------------------
    // ----------------- ON CREATE --------------------
    //-------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_stuff);
        db = FirebaseFirestore.getInstance();
        mTypeSpinner = findViewById(R.id.sp_addNewStuff_stuffType);
        mDurationSeekBar = findViewById(R.id.sb_addNewStuff_label_shareDuration);
        mDurationTV = findViewById(R.id.tv_addNewStuff_duration_shareDuration);
        mNameEt = findViewById(R.id.et_addNewStuff_name);
        mValidateButton = findViewById(R.id.bt_addNewStuff_valid);
        mBorrowerACTV = findViewById(R.id.actv_addNewStuff_label_shareTo);
        mStuffTypeList = new ArrayList<>();
        mTypesNames = new ArrayList<>();
        Date now = new Date();
        Intent intent = new Intent(this, MainActivity.class);
        typeId = null;
        mDurationTV.setText("aucune");


        //-------------------------------------------------
        // -- AutoCompleteTextView -
        //-------------------------------------------------
        List<User> friends = DummyUserGenerator.getUsers();
        List<String> friendsMails = new ArrayList<>();
        for (User user :
                friends) {
            friendsMails.add(user.getFirstName());
        }
        ArrayAdapter<String> userMailAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, friendsMails);
        mBorrowerACTV.setAdapter(userMailAdapter);
        mBorrowerACTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    mDurationSeekBar.setEnabled(true);
                } else {
                    mDurationSeekBar.setProgress(0);
                    mDurationSeekBar.setEnabled(false);
                    mInitialLoanPeriodInDay = 0;
                    mDurationTV.setText("aucune");


                }
            }
        });


        //-------------------------------------------------
        // -- TypeSpinner - OnItemSelectedListener
        //-------------------------------------------------
        mStuffTypeList = StuffTypeGenerator.GenerateStuffTypes();
        for (StuffType st :
                mStuffTypeList) {
            mTypesNames.add(st.getName());
        }
        ;
        mTypeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mTypesNames);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeSpinner.setAdapter(arrayAdapter);


        //-------------------------------------------------
        // -- Seekbar -
        //-------------------------------------------------
        mDurationSeekBar.setEnabled(false);
        mDurationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mInitialLoanPeriodInDay = i;
                // ---- Duration TextView ------------------------
                mDurationTV.setText(mInitialLoanPeriodInDay + "j.");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mInitialLoanPeriodInDay == 0) {
                    mDurationTV.setText("aucune");
                }

            }
        });


        //-------------------------------------------------
        // -- NameEditText - TextWatcher
        //-------------------------------------------------
        mNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().isEmpty()) {
                    mName = editable.toString();
                    mValidateButton.setEnabled(true);
                } else {
                    mName = "No Topic";
                    mValidateButton.setEnabled(false);
                }
            }
        });


        //-------------------------------------------------
        // -- Validate Button - OnClickListener
        //-------------------------------------------------
        mValidateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //-------------------
                // -- Get Creation Date
                //-------------------
                mCurrentLoanDate = now;


                //-------------------
                // -- Get Creation Date
                //-------------------
                mCreationDate = now;

                //-------------------
                // -- Get Borrower
                //-------------------
                mBorrower = String.valueOf(mBorrowerACTV.getText());

                //-------------------
                // -- Get User
                //-------------------
                mOwner = "0";


                //-------------------
                // -- Create New Stuff
                //-------------------
                Stuff mNewStuff = new Stuff(mName, mOwner, mBorrower, mTypeId, mCreationDate, mCurrentLoanDate, mInitialLoanPeriodInDay);
                db.collection("stuffs")
                        .add(mNewStuff)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                System.out.println(documentReference.getId() + " : was created !");
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                System.out.println("##### => " + e + " : ERROR ! ");
                            }
                        });
                startActivity(intent);
            }
        });
    }


    //-------------------
    // -- Spinner methods
    //-------------------
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mTypeId = mStuffTypeList.get(i).getuId();
        System.out.println(mStuffTypeList.get(i).getName() + " is selected, this uId == " + mStuffTypeList.get(i).getuId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}