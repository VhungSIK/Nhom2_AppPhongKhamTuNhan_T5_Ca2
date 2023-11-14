package com.example.doandidong.employee.reception.details;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;

import java.util.ArrayList;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.UserDetailViewHolder> {

    private ArrayList<String> userDetailList;

    public UserDetailsAdapter(ArrayList<String> userDetailList) {
        this.userDetailList = userDetailList;
    }

    @NonNull
    @Override
    public UserDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_details, parent, false);
        return new UserDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailViewHolder holder, int position) {
        String userDetail = userDetailList.get(position);
        String[] userData = userDetail.split(" - ");

        holder.textViewUserName.setText(userData[0]);
        holder.textViewUserPhone.setText(userData[1]);
        holder.textViewDate.setText(userData[2]);
        holder.textViewDoctorName.setText(userData[3]);
        holder.textViewTime.setText(userData[4]);
        holder.textViewType.setText(userData[5]);
        holder.textViewDoctorId.setText(userData[6]);
        holder.textViewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = userData[1];
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + phoneNumber));
                v.getContext().startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userDetailList.size();
    }

    public static class UserDetailViewHolder extends RecyclerView.ViewHolder {
        TextView textViewUserName, textViewUserPhone, textViewDate, textViewDoctorName, textViewTime, textViewType, textViewDoctorId,textViewCall;

        public UserDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserName = itemView.findViewById(R.id.textViewUserName);
            textViewUserPhone = itemView.findViewById(R.id.textViewUserPhone);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewDoctorName = itemView.findViewById(R.id.textViewDoctorName);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewDoctorId = itemView.findViewById(R.id.textViewDoctorId);
            textViewCall = itemView.findViewById(R.id.textViewCall);
        }
    }
}
