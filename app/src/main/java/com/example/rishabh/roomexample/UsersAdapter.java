package com.example.rishabh.roomexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rishabh on 5/26/18.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {


    Context context;
    List<User> userList;
    UserViewListener mListener;

    public UsersAdapter(Context context,UserViewListener mListener){
        this.context = context;
        this.mListener = mListener;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).
                inflate(R.layout.user_view ,parent, false);
        return new UserViewHolder(itemView,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(userList.get(position));
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView firstNameTextView , lastNameTextView;
        private UserViewListener mListener;
        private User user;

        public UserViewHolder(@NonNull View itemView,UserViewListener mListener) {
            super(itemView);
            firstNameTextView = itemView.findViewById(R.id.textview_firstname);
            lastNameTextView = itemView.findViewById(R.id.textview_lastname);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
        }

        void bind(User user){
            this.user=user;
            firstNameTextView.setText(user.getFirstName());
            lastNameTextView.setText(user.getLastName());
        }

        @Override
        public void onClick(View view) {
            mListener.onClicked(user);
        }
    }

    public interface UserViewListener{
        void onClicked(User user);
    }
}
