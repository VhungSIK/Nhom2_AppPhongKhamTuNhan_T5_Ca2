package com.example.doandidong.doctor.doctor_appointment.doctor_request;
public class Prescription {
    private String stt;
    private String medicineName;
    private String dosage;
    private String usage;
    private String route;
    private String days;
    private String doctorName;
    private String userName;
    private String appointmentDate;
    private String appointmentType;
    private String currentTime;
    public Prescription() {
        // Cần có constructor mặc định để Firebase có thể đọc dữ liệu.
    }

    public Prescription(String stt, String medicineName, String dosage, String usage, String route, String days, String doctorName, String userName, String appointmentDate, String appointmentType, String currentTime) {
        this.stt = stt;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.usage = usage;
        this.route = route;
        this.days = days;
        this.doctorName = doctorName;
        this.userName = userName;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.currentTime = currentTime;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
