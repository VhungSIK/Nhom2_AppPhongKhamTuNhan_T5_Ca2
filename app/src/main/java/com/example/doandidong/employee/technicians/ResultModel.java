package com.example.doandidong.employee.technicians;

import java.util.Date;

public class ResultModel {
    private String doctorName;
    private String userName;
    private String appointmentDate;
    private String appointmentType;
    private String currentTime;
    private String bloodGroup;
    private String quantification;
    private String index;
    private String totalAnalysis;

    public ResultModel() {
        // Required empty public constructor for Firebase
    }

    public ResultModel(String doctorName, String userName, String appointmentDate, String appointmentType, String currentTime,
                       String bloodGroup, String quantification, String index, String totalAnalysis) {
        this.doctorName = doctorName;
        this.userName = userName;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.currentTime = currentTime;
        this.bloodGroup = bloodGroup;
        this.quantification = quantification;
        this.index = index;
        this.totalAnalysis = totalAnalysis;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getQuantification() {
        return quantification;
    }

    public String getIndex() {
        return index;
    }

    public String getTotalAnalysis() {
        return totalAnalysis;
    }
}
