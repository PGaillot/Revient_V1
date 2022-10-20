package com.gayo.revient.fragment;

import static android.graphics.Color.CYAN;
import static android.graphics.Color.RED;
import static android.graphics.Color.TRANSPARENT;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.gayo.revient.MainActivity;
import com.gayo.revient.R;
import com.gayo.revient.di.DI;
import com.gayo.revient.model.Stuff;
import com.gayo.revient.model.StuffType;
import com.gayo.revient.recyclerView.SimpleStuffListAdapter;
import com.gayo.revient.service.StuffApiService;
import com.gayo.revient.service.StuffTypeGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StuffListFragment extends Fragment implements View.OnClickListener {

    private FirestoreRecyclerAdapter fstRecyclerAdapter;
    View mStuffListFragmentView;
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    String drawableUri;
    ImageButton ib_cat1,ib_cat2,ib_cat3,ib_cat4,ib_cat5;
    Stuff mStuff;
    int mButtonTypeSelected; // 0 = nothing selected;
    private OnButtonClickedListener mCallback;

    @Override
    public void onClick(View view) {

    }


    //-----------------------------------------------
    //----------------- callBack --------------------
    //-----------------------------------------------
    public interface OnButtonClickedListener{
        public void onButtonClicked(View view, Stuff stuff);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.createCallbackToParentActivity();
    }

//    @Override
//    public void onClick(View view) {
//        mCallback.onButtonClicked(view);
//    }

    private void createCallbackToParentActivity(){
        try {
            mCallback = (OnButtonClickedListener) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(e.toString() + "must implement OnButtonClickListener");
        }
    }



    //-----------------------------------------------
    //----------------- Constructor -------------------
    //-----------------------------------------------
    public StuffListFragment() {
    }

    public StuffListFragment newInstance() {
        StuffListFragment fragment = new StuffListFragment();
        return fragment;
    }


    //-----------------------------------------------
    //----------------- On Create -------------------
    //-----------------------------------------------
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawableUri = "drawable";
        mButtonTypeSelected = 0;

    }




    //-----------------------------------------------
    //--------------- RecyclerView ------------------
    //-----------------------------------------------
    private class FbViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Stuff_Name, tv_Stuff_Category;
        ImageView iv_Stuff_category;

        public FbViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Stuff_Name = itemView.findViewById(R.id.tv_simpleStufffRow_name);
            tv_Stuff_Category = itemView.findViewById(R.id.tv_simpleStufffRow_category);
            iv_Stuff_category = itemView.findViewById(R.id.iv_simpleStufffRow_category);
        }
    }


    //-----------------------------------------------
    //--------------- onCreateView ------------------
    //-----------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        Resources res = getResources();

        // 1 - Inflate the view
        mStuffListFragmentView = inflater.inflate(R.layout.fragment_stuff_list, container, false);


        //----------------------------------------------
        // --- ImagesButtons
        // ----------------------------------------------
        ib_cat1 = mStuffListFragmentView.findViewById(R.id.ib_SuffList_cat1);
        ib_cat2 = mStuffListFragmentView.findViewById(R.id.ib_SuffList_cat2);
        ib_cat3 = mStuffListFragmentView.findViewById(R.id.ib_SuffList_cat3);
        ib_cat4 = mStuffListFragmentView.findViewById(R.id.ib_SuffList_cat4);
        ib_cat5 = mStuffListFragmentView.findViewById(R.id.ib_SuffList_cat5);


        ib_cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catNumber = 1;

                if (mButtonTypeSelected != catNumber){
                mButtonTypeSelected = catNumber;
                ib_cat1.setBackgroundColor(RED);
                ib_cat2.setBackgroundColor(TRANSPARENT);
                ib_cat3.setBackgroundColor(TRANSPARENT);
                ib_cat4.setBackgroundColor(TRANSPARENT);
                ib_cat5.setBackgroundColor(TRANSPARENT);

//                InitializeStuffList(mButtonTypeSelected);
                } else {
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(TRANSPARENT);
                    mButtonTypeSelected = 0;
                }
                System.out.println("CATEGORY SELECTED : " + mButtonTypeSelected);
            }
        });

        ib_cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catNumber = 2;

                if (mButtonTypeSelected != catNumber){
                    mButtonTypeSelected = catNumber;
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(RED);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(TRANSPARENT);//
                } else {
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(TRANSPARENT);
                    mButtonTypeSelected = 0;
                }
                System.out.println("CATEGORY SELECTED : " + mButtonTypeSelected);
            }
        });

        ib_cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catNumber = 3;

                if (mButtonTypeSelected != catNumber){
                    mButtonTypeSelected = catNumber;
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(RED);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(TRANSPARENT);//
                     InitializeStuffList(mButtonTypeSelected);
                } else {
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(TRANSPARENT);
                    mButtonTypeSelected = 0;
                }
                System.out.println("CATEGORY SELECTED : " + mButtonTypeSelected);
            }
        });

        ib_cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catNumber = 4;

                if (mButtonTypeSelected != catNumber){
                    mButtonTypeSelected = catNumber;
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(RED);
                    ib_cat5.setBackgroundColor(TRANSPARENT);//                    InitializeStuffList(mButtonTypeSelected);
                } else {
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(TRANSPARENT);
                    mButtonTypeSelected = 0;
                }
                System.out.println("CATEGORY SELECTED : " + mButtonTypeSelected);
            }
        });

        ib_cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catNumber = 5;

                if (mButtonTypeSelected != catNumber){
                    mButtonTypeSelected = catNumber;
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(RED);//                    InitializeStuffList(mButtonTypeSelected);
                } else {
                    ib_cat1.setBackgroundColor(TRANSPARENT);
                    ib_cat2.setBackgroundColor(TRANSPARENT);
                    ib_cat3.setBackgroundColor(TRANSPARENT);
                    ib_cat4.setBackgroundColor(TRANSPARENT);
                    ib_cat5.setBackgroundColor(TRANSPARENT);
                    mButtonTypeSelected = 0;
                }
                    System.out.println("CATEGORY SELECTED : " + mButtonTypeSelected);
            }
        });

        InitializeStuffList(mButtonTypeSelected);

        return mStuffListFragmentView;
    }


    private void InitializeStuffList(int typeSelected) {
        Query query = null;

        if(typeSelected == 0){
            // Query
            query = db.collection("stuffs");
        } else {
            // Query
            query = db.collection("stuffs").whereEqualTo("type", "" + typeSelected);
        }

        // RecyclerOptions
        FirestoreRecyclerOptions<Stuff> options = new FirestoreRecyclerOptions.Builder<Stuff>()
                .setQuery(query, Stuff.class)
                .build();

        fstRecyclerAdapter = new FirestoreRecyclerAdapter<Stuff, FbViewHolder>(options) {
            @NonNull
            @Override
            public FbViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stuff_simple_row, parent, false);
                return new FbViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(@NonNull FbViewHolder holder, int position, @NonNull Stuff model) {
                StuffType cStuffType = getStuffTypeById(model.getType());
                holder.tv_Stuff_Name.setText(model.getName());
                holder.tv_Stuff_Category.setText(cStuffType.getName());
                int iconResource = getResources().getIdentifier(cStuffType.getIcon(), "drawable" ,getContext().getPackageName());
                holder.iv_Stuff_category.setImageResource(iconResource);
                mStuff = new Stuff(model.getName(), model.getOwner(), model.getBorrower(), model.getCreationDate(), model.getCurrentLoanDate(), model.getInitialLoanPeriodInDay());
                // --- Click at Item
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCallback.onButtonClicked(view, model);
                    }
                });
            }
        };

        mRecyclerView = mStuffListFragmentView.findViewById(R.id.rv_StuffList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getParent())); // getContext()
        mRecyclerView.setAdapter(fstRecyclerAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        fstRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        fstRecyclerAdapter.stopListening();
    }


    /**
     * Return a StuffType with a StuffType id
     * @param id String
     * @return StuffType
     */
    public StuffType getStuffTypeById(String id){
        List<StuffType> stuffTypeList = StuffTypeGenerator.GenerateStuffTypes();
        StuffType cStuffType = null;
        for (StuffType st :
                stuffTypeList) {
            if (st.getuId().equals(id)) {
                cStuffType = st;
            }
        }
        return cStuffType;
    };
}
