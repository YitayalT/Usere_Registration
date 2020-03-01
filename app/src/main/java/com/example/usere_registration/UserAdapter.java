package com.example.usere_registration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

     private List<User> userList;
    private Context context;
    private  View.OnClickListener onClickListener;
    private  View.OnLongClickListener onLongClickListener;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.details,null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, final int position) {
   User user = userList.get(position);
   holder.uName.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder  {
        TextView  uName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
              uName = (TextView) itemView.findViewById(R.id.tvUName);
              itemView.setTag(this);
              itemView.setOnClickListener(onClickListener);
              itemView.setOnLongClickListener(onLongClickListener);
        }

    }

}
