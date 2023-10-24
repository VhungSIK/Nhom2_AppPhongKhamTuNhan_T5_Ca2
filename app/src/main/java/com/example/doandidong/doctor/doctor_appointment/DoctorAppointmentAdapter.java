package com.example.doandidong.doctor.doctor_appointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.doandidong.R;
import com.example.doandidong.medical_appointment.Appointment;
import com.example.doandidong.medical_appointment.AppointmentAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorAppointmentAdapter extends RecyclerView.Adapter<DoctorAppointmentAdapter.AppointmentDT>{

    ArrayList<DoctorAppointment> doctorAppointments;
    Listener listener;
    public DoctorAppointmentAdapter(DoctorAppointmentAdapter.Listener listener, ArrayList<DoctorAppointment> doctorAppointments){
        this.listener=listener;
        this.doctorAppointments=doctorAppointments;
    }
    @NonNull
    @Override
    public DoctorAppointmentAdapter.AppointmentDT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appointment,parent,false);
        return new AppointmentDT(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorAppointmentAdapter.AppointmentDT holder, int position) {
        DoctorAppointment doctorAppointment=doctorAppointments.get(position);
        holder.txIdA1.setText("Lịch khám: ");
        holder.txDoctorName1.setText("Bác sĩ: ".concat(doctorAppointment.getDoctorName()));
        holder.txDate1.setText("Ngày khám: ".concat(doctorAppointment.getDate()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickItemListener(doctorAppointment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorAppointments.size();
    }

    class AppointmentDT extends RecyclerView.ViewHolder{
        CircleImageView imgApp1;
        TextView txIdA1,txDoctorName1,txDate1;

        public AppointmentDT(@NonNull View itemView) {
            super(itemView);
            imgApp1=itemView.findViewById(R.id.imgApp1);
            txIdA1=itemView.findViewById(R.id.txIdA1);
            txDoctorName1=itemView.findViewById(R.id.txDoctorName1);
            txDate1=itemView.findViewById(R.id.txDate1);
        }
    }
    public interface Listener{
        void onClickItemListener(DoctorAppointment doctorAppointment);
    }
}