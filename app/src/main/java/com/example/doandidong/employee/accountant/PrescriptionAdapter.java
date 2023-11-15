package com.example.doandidong.employee.accountant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.doctor_request.Prescription;

import java.util.List;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder> {
    private List<Prescription> prescriptionList;
    private String userId;

    public PrescriptionAdapter(List<Prescription> prescriptionList, String userId) {
        this.prescriptionList = prescriptionList;
    }

    @NonNull
    @Override
    public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_item, parent, false);
        return new PrescriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrescriptionViewHolder holder, int position) {
        Prescription prescription = prescriptionList.get(position);
        // Populate data into views in the layout
        holder.bind(prescription);
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    public static class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView userName, doctorName, appointmentType, currentTime, userid;

        public PrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            doctorName = itemView.findViewById(R.id.doctorName);
            appointmentType = itemView.findViewById(R.id.appointmentType);
            currentTime = itemView.findViewById(R.id.currentTime);
            userid = itemView.findViewById(R.id.userid);

        }
        public void bind(Prescription prescription) {
            // Bind prescription data to respective TextViews
            userName.setText(prescription.getUserName());
            doctorName.setText(prescription.getDoctorName());
            appointmentType.setText(prescription.getAppointmentType());
            currentTime.setText(prescription.getCurrentTime());
            userid.setText(prescription.getUserId());
        }
    }
}
