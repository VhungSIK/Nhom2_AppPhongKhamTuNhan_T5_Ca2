package com.example.doandidong.doctor.doctor_appointment.doctor_result;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
    private String UserId;
    private String doctorId;
    private List<ResultModel> results;

    public ResultAdapter(Context context, String UserId, String doctorId) {
        this.UserId = UserId;
        this.doctorId = doctorId;
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (results != null) {
                        ResultModel result = results.get(getAdapterPosition());
                        // Chuyển sang trang DoctorDetailResultActivity và truyền thông tin chi tiết
                        Intent intent = new Intent(context, DoctorDetailResultActivity.class);
                        intent.putExtra("doctorName", result.getDoctorName());
                        intent.putExtra("userName", result.getUserName());
                        intent.putExtra("appointmentDate", result.getAppointmentDate());
                        intent.putExtra("appointmentType", result.getAppointmentType());
                        intent.putExtra("currentTime", result.getCurrentTime());
                        intent.putExtra("bloodGroup", result.getBloodGroup());
                        intent.putExtra("quantification", result.getQuantification());
                        intent.putExtra("index", result.getIndex());
                        intent.putExtra("totalAnalysis", result.getTotalAnalysis());
                        intent.putExtra("userId", result.getUserId());
                        intent.putExtra("doctorId", result.getDoctorId());

                        context.startActivity(intent);
                    }
                }
            });
        }

        public void bind(ResultModel result) {
            doctorNameTextView.setText(result.getDoctorName());
            userNameTextView.setText(result.getUserName());
            appointmentDateTextView.setText(result.getAppointmentDate());
            appointmentTypeTextView.setText(result.getAppointmentType());
            currentTimeTextView.setText(result.getCurrentTime());
            bloodGroupTextView.setText(result.getBloodGroup());
            quantificationTextView.setText(result.getQuantification());
            indexTextView.setText(result.getIndex());
            totalAnalysisTextView.setText(result.getTotalAnalysis());
            Log.d("ResultAdapter", "userId: " + result.getUserId()); // Log userId here

        }
    }
}
