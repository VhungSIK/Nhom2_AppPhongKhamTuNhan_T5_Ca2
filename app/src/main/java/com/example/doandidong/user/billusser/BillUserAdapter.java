package com.example.doandidong.user.billusser;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.doctor_request.Prescription;
import com.example.doandidong.employee.accountant.PrescriptionAdapter;

import java.util.List;

public class BillUserAdapter extends RecyclerView.Adapter<BillUserAdapter.BillUserViewHolder> {
    private List<Prescription> prescriptionList;
    private String UserId;
    private String billId;
    private BillUserAdapter.OnItemClickListener listener;
    public BillUserAdapter(List<Prescription> prescriptionList, String UserId, String billId) {
        this.prescriptionList = prescriptionList;
        this.UserId = UserId;
        this.billId = billId;
    }

    @NonNull
    @Override
    public BillUserAdapter.BillUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prescription_item, parent, false);
        return new BillUserAdapter.BillUserViewHolder(view);
    }
    public interface OnItemClickListener {
        void onItemClick(String prescriptionId);
    }
    public void setOnItemClickListener(BillUserAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return prescriptionList.size();
    }

    public class BillUserViewHolder extends RecyclerView.ViewHolder {
        TextView userName, doctorName, appointmentType, currentTime, userid,doctorid, billId;

        public BillUserViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.userName);
            doctorName = itemView.findViewById(R.id.doctorName);
            appointmentType = itemView.findViewById(R.id.appointmentType);
            currentTime = itemView.findViewById(R.id.currentTime);
            userid = itemView.findViewById(R.id.userid);
            doctorid = itemView.findViewById(R.id.doctorid);
            billId = itemView.findViewById(R.id.billId);
        }

        public void bind(Prescription prescription) {
            // Bind prescription data to respective TextViews
            userName.setText(prescription.getUserName());
            doctorName.setText(prescription.getDoctorName());
            appointmentType.setText(prescription.getAppointmentType());
            currentTime.setText(prescription.getCurrentTime());
            userid.setText(prescription.getUserId());
            doctorid.setText(prescription.getDoctorId());
            billId.setText(prescription.getBillId());
        }
    }
    @Override
    public void onBindViewHolder(@NonNull BillUserAdapter.BillUserViewHolder holder, int position) {
        Prescription prescription = prescriptionList.get(position);
        holder.bind(prescription);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(prescription.getBillId()); // Pass billId instead of prescriptionId
                }
            }
        });
    }

}