package com.example.doandidong.employee.reception;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;

import java.util.ArrayList;
public class ReceptionAdapter extends RecyclerView.Adapter<ReceptionAdapter.UserInfoViewHolder> {

    private ArrayList<String> userInfoList;
    private OnItemClickListener listener;

    public ReceptionAdapter(ArrayList<String> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public interface OnItemClickListener {
        void onItemClick(String userId, String userName, String userEmail);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_infor, parent, false);
        return new UserInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoViewHolder holder, int position) {
        String userInfo = userInfoList.get(position);
        String[] userData = userInfo.split(" - ");

        final String userName = userData[0];
        final String userEmail = userData[1];
        final String userId = userData[2];

        holder.textViewUserName.setText(userName);
        holder.textViewUserEmail.setText(userEmail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(userId, userName, userEmail);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userInfoList.size();
    }

    public static class UserInfoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName, textViewUserEmail;

        public UserInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewUserEmail = itemView.findViewById(R.id.textViewUserEmail);
        }
    }
}