package com.example.doandidong.doctor.doctor_appointment.doctor_result;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;
import com.example.doandidong.employee.technicians.ResultModel;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private final Context context;
    private List<ResultModel> results;

    public ResultAdapter(Context context) {
        this.context = context;
    }

    public void setResults(List<ResultModel> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.result_item, parent, false);
        return new ResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        if (results != null) {
            ResultModel result = results.get(position);
            holder.bind(result);
        }
    }

    @Override
    public int getItemCount() {
        return results != null ? results.size() : 0;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        private final TextView doctorNameTextView;
        private final TextView userNameTextView;
        private final TextView appointmentDateTextView;
        private final TextView appointmentTypeTextView;
        private final TextView currentTimeTextView;
        private final TextView bloodGroupTextView;
        private final TextView quantificationTextView;
        private final TextView indexTextView;
        private final TextView totalAnalysisTextView;

        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorNameTextView = itemView.findViewById(R.id.doctorNameTextView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            appointmentDateTextView = itemView.findViewById(R.id.appointmentDateTextView);
            appointmentTypeTextView = itemView.findViewById(R.id.appointmentTypeTextView);
            currentTimeTextView = itemView.findViewById(R.id.currentTimeTextView);
            bloodGroupTextView = itemView.findViewById(R.id.bloodGroupTextView);
            quantificationTextView = itemView.findViewById(R.id.quantificationTextView);
            indexTextView = itemView.findViewById(R.id.indexTextView);
            totalAnalysisTextView = itemView.findViewById(R.id.totalAnalysisTextView);
        }

        public void bind(ResultModel result) {
            doctorNameTextView.setText("Doctor Name: " + result.getDoctorName());
            userNameTextView.setText("User Name: " + result.getUserName());
            appointmentDateTextView.setText("Appointment Date: " + result.getAppointmentDate());
            appointmentTypeTextView.setText("Appointment Type: " + result.getAppointmentType());
            currentTimeTextView.setText("Current Time: " + result.getCurrentTime());
            bloodGroupTextView.setText("Blood Group: " + result.getBloodGroup());
            quantificationTextView.setText("Quantification: " + result.getQuantification());
            indexTextView.setText("Index: " + result.getIndex());
            totalAnalysisTextView.setText("Total Analysis: " + result.getTotalAnalysis());
        }
    }
}

