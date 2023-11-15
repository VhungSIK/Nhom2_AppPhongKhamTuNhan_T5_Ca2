package com.example.doandidong.employee.technicians;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.doandidong.R;
import com.example.doandidong.employee.technicians.RequestAdapter.RequestViewHolder;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestViewHolder> {
    private List<RequestModel> requests;
    private String UserId;
    private String doctorId;
    public RequestAdapter(String UserId, String doctorId) {
        this.UserId = UserId;
        this.doctorId = doctorId;
    }

    public void setRequests(List<RequestModel> requests) {
        this.requests = requests;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_item, parent, false);
        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        if (requests != null) {
            RequestModel request = requests.get(position);
            holder.bind(request, UserId, doctorId);

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), SendRequireActivity.class);
                intent.putExtra("doctorName", request.getDoctorName());
                intent.putExtra("appointmentType", request.getAppointmentType());
                intent.putExtra("appointmentDate", request.getAppointmentDate());
                intent.putExtra("userName", request.getUserName());
                intent.putExtra("userPhone", request.getUserPhone());
                intent.putExtra("note", request.getNote());
                intent.putExtra("UserId", request.getUserId());
                intent.putExtra("doctorId", request.getDoctorId());
                Log.d("SendRequireActivity", "User ID: " + request.getUserId());
                Log.d("SendRequireActivity", "Doctor ID: " + request.getDoctorId());

                view.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return (requests != null) ? requests.size() : 0;
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        private TextView doctorNameTextView;
        private TextView appointmentTypeTextView;
        private TextView appointmentDateTextView;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorNameTextView = itemView.findViewById(R.id.doctorNameTextView);
            appointmentTypeTextView = itemView.findViewById(R.id.appointmentTypeTextView);
            appointmentDateTextView = itemView.findViewById(R.id.appointmentDateTextView);
        }

        public void bind(RequestModel request, String UserId, String doctorId) {
            doctorNameTextView.setText(request.getDoctorName());
            appointmentTypeTextView.setText(request.getAppointmentType());
            appointmentDateTextView.setText(request.getAppointmentDate());
            Log.d("RequestViewHolder", "Userssss ID5: " + doctorId);

            Log.d("RequestViewHolder", "User ID5: " + UserId);
        }
    }
}
