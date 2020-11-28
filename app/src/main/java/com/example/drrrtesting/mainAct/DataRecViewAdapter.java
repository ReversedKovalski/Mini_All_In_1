package com.example.drrrtesting.mainAct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrrtesting.R;
import com.example.drrrtesting.room.objects.User;

import java.util.ArrayList;
import java.util.List;

public class DataRecViewAdapter extends RecyclerView.Adapter<DataRecViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<User> mUsers = new ArrayList<>();

    public DataRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateUsers(List<User> users){
        mUsers.clear();
        mUsers.addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.data_rec_view_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tName.setText(mUsers.get(position).getName());
        holder.tEmail.setText(mUsers.get(position).getEmail());
        holder.tPassword.setText(mUsers.get(position).getPassword());
        holder.tId.setText(String.valueOf(mUsers.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tName, tEmail, tPassword, tId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tName = itemView.findViewById(R.id.txtName);
            tEmail = itemView.findViewById(R.id.txtEmail);
            tPassword = itemView.findViewById(R.id.txtPassword);
            tId = itemView.findViewById(R.id.txtId);
        }
    }
}
