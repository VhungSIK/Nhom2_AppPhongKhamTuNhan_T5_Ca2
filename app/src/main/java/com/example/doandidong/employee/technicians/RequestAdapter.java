package com.example.doandidong.employee.technicians;
import android.content.Intent;
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

    public RequestAdapter() {
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
            holder.bind(request);

            // Bắt sự kiện nhấp vào một mục
            holder.itemView.setOnClickListener(view -> {
                // Tạo Intent để chuyển sang trang chi tiết
                Intent intent = new Intent(view.getContext(), DetailRequestActivity.class);

                // Đính kèm dữ liệu yêu cầu cụ thể
                intent.putExtra("doctorName", request.getDoctorName());
                intent.putExtra("appointmentType", request.getAppointmentType());
                intent.putExtra("appointmentDate", request.getAppointmentDate());
                intent.putExtra("userName", request.getUserName());
                intent.putExtra("userPhone", request.getUserPhone());
                intent.putExtra("note", request.getNote());

                // Chuyển sang trang chi tiết
                view.getContext().startActivity(intent);
            });
            holder.itemView.setOnClickListener(view -> {
                // Tạo Intent để chuyển sang trang chi tiết
                Intent i = new Intent(view.getContext(), SendRequireActivity.class);

                // Đính kèm dữ liệu yêu cầu cụ thể
                i.putExtra("doctorName", request.getDoctorName());
                i.putExtra("appointmentType", request.getAppointmentType());
                i.putExtra("appointmentDate", request.getAppointmentDate());
                i.putExtra("userName", request.getUserName());
                i.putExtra("userPhone", request.getUserPhone());
                i.putExtra("note", request.getNote());

                // Chuyển sang trang chi tiết
                view.getContext().startActivity(i);
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

        public void bind(RequestModel request) {
            doctorNameTextView.setText(request.getDoctorName());
            appointmentTypeTextView.setText(request.getAppointmentType());
            appointmentDateTextView.setText(request.getAppointmentDate());
        }
    }
}
