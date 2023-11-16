package com.example.doandidong.employee.accountant;

public class PrescriptionModel {
    private String userId;
    private String userName;
    private String appointmentType;
    private String appointmentDate;
    private String currentTime;

    public PrescriptionModel() {
        // Default constructor required for Firestore
    }

    public PrescriptionModel(String userId, String userName, String appointmentType, String appointmentDate, String currentTime) {
        this.userId = userId;
        this.userName = userName;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.currentTime = currentTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
