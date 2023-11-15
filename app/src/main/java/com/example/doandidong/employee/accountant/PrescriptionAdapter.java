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
import com.example.doandidong.doctor.doctor_appointment.DoctorAppointment;
import com.example.doandidong.doctor.doctor_appointment.DoctorAppointmentAdapter;
import com.example.doandidong.doctor.doctor_appointment.doctor_request.Prescription;

import java.util.List;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.PrescriptionViewHolder> {
    private List<Prescription> prescriptionList;
    private String UserId;
    private String doctorId;
    private OnItemClickListener listener;
    public PrescriptionAdapter(List<Prescription> prescriptionList, String UserId, String doctorId) {
        this.prescriptionList = prescriptionList;
        this.UserId = UserId;
        this.doctorId = doctorId;
    }

    @NonNull
    @Override
    public PrescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_item, parent, false);
        return new PrescriptionViewHolder(view);
    }
    public interface OnItemClickListener {
        void onItemClick(String prescriptionId);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    public class PrescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView userName, doctorName, appointmentType, currentTime, userid,doctorid;

        public PrescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            doctorName = itemView.findViewById(R.id.doctorName);
            appointmentType = itemView.findViewById(R.id.appointmentType);
            currentTime = itemView.findViewById(R.id.currentTime);
            userid = itemView.findViewById(R.id.userid);
            doctorid = itemView.findViewById(R.id.doctorid);
        }

        public void bind(Prescription prescription) {
            // Bind prescription data to respective TextViews
            userName.setText(prescription.getUserName());
            doctorName.setText(prescription.getDoctorName());
            appointmentType.setText(prescription.getAppointmentType());
            currentTime.setText(prescription.getCurrentTime());
            userid.setText(prescription.getUserId());
            doctorid.setText(prescription.getDoctorId());
        }
    }
    @Override
    public void onBindViewHolder(@NonNull PrescriptionViewHolder holder, int position) {
        Prescription prescription = prescriptionList.get(position);
        holder.bind(prescription);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(prescription.getPrescriptionId());
                }
            }
        });
    }
}