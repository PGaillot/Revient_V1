package com.gayo.revient.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gayo.revient.R;
import com.gayo.revient.model.Stuff;

import java.util.List;

public class SimpleStuffListAdapter extends RecyclerView.Adapter<SimpleStuffListAdapter.SimpleStuffListViewHolder> {

    Context mContext;
    List<Stuff> mStuffList;


    public SimpleStuffListAdapter(Context context, List<Stuff> stuff) {
        mContext = context;
        mStuffList = stuff;
    }

    public class SimpleStuffListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Stuff_Name, tv_Stuff_Category;

        public SimpleStuffListViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Stuff_Name = itemView.findViewById(R.id.tv_simpleStufffRow_name);
            tv_Stuff_Category = itemView.findViewById(R.id.tv_simpleStufffRow_category);
        }
    }

    @NonNull
    @Override
    public SimpleStuffListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.stuff_simple_row, parent, false);
        return new SimpleStuffListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleStuffListViewHolder holder, int position) {
        holder.tv_Stuff_Name.setText(mStuffList.get(position).getName());
        holder.tv_Stuff_Category.setText(mStuffList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return mStuffList.size();
    }
}
