package com.example.doandidong.doctor.doctor_appointment.doctor_request;
public class Prescription {
    private String stt;
    private String UserId;
    private String doctorId;

    private String medicineName;
    private String dosage;
    private String usage;
    private String route;
    private String days;
    private String stt1;
    private String medicineName1;
    private String dosage1;
    private String usage1;
    private String route1;
    private String days1;
    private String stt2;
    private String medicineName2;
    private String dosage2;
    private String usage2;
    private String route2;
    private String days2;
    private String stt3;
    private String medicineName3;
    private String dosage3;
    private String usage3;
    private String route3;
    private String days3;
    private String stt4;
    private String medicineName4;
    private String dosage4;
    private String usage4;
    private String route4;
    private String days4;
    private String stt5;
    private String medicineName5;
    private String dosage5;
    private String usage5;
    private String route5;
    private String days5;
    private String doctorName;
    private String userName;
    private String appointmentDate;
    private String appointmentType;
    private String currentTime;
    public Prescription() {
        // Cần có constructor mặc định để Firebase có thể đọc dữ liệu.
    }

    public Prescription(String stt, String medicineName, String dosage, String usage, String route, String days, String doctorName, String userName, String appointmentDate, String appointmentType, String currentTime, String userId) {
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
        this.UserId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
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

    public String getStt2() {
        return stt2;
    }

    public void setStt2(String stt2) {
        this.stt2 = stt2;
    }

    public String getMedicineName2() {
        return medicineName2;
    }

    public void setMedicineName2(String medicineName2) {
        this.medicineName2 = medicineName2;
    }

    public String getDosage2() {
        return dosage2;
    }

    public void setDosage2(String dosage2) {
        this.dosage2 = dosage2;
    }

    public String getUsage2() {
        return usage2;
    }

    public void setUsage2(String usage2) {
        this.usage2 = usage2;
    }

    public String getRoute2() {
        return route2;
    }

    public void setRoute2(String route2) {
        this.route2 = route2;
    }

    public String getDays2() {
        return days2;
    }

    public void setDays2(String days2) {
        this.days2 = days2;
    }

    public String getStt3() {
        return stt3;
    }

    public void setStt3(String stt3) {
        this.stt3 = stt3;
    }

    public String getMedicineName3() {
        return medicineName3;
    }

    public void setMedicineName3(String medicineName3) {
        this.medicineName3 = medicineName3;
    }

    public String getDosage3() {
        return dosage3;
    }

    public void setDosage3(String dosage3) {
        this.dosage3 = dosage3;
    }

    public String getUsage3() {
        return usage3;
    }

    public void setUsage3(String usage3) {
        this.usage3 = usage3;
    }

    public String getRoute3() {
        return route3;
    }

    public void setRoute3(String route3) {
        this.route3 = route3;
    }

    public String getDays3() {
        return days3;
    }

    public void setDays3(String days3) {
        this.days3 = days3;
    }

    public String getStt4() {
        return stt4;
    }

    public void setStt4(String stt4) {
        this.stt4 = stt4;
    }

    public String getMedicineName4() {
        return medicineName4;
    }

    public void setMedicineName4(String medicineName4) {
        this.medicineName4 = medicineName4;
    }

    public String getDosage4() {
        return dosage4;
    }

    public void setDosage4(String dosage4) {
        this.dosage4 = dosage4;
    }

    public String getUsage4() {
        return usage4;
    }

    public void setUsage4(String usage4) {
        this.usage4 = usage4;
    }

    public String getRoute4() {
        return route4;
    }

    public void setRoute4(String route4) {
        this.route4 = route4;
    }

    public String getDays4() {
        return days4;
    }

    public void setDays4(String days4) {
        this.days4 = days4;
    }

    public String getStt5() {
        return stt5;
    }

    public void setStt5(String stt5) {
        this.stt5 = stt5;
    }

    public String getMedicineName5() {
        return medicineName5;
    }

    public void setMedicineName5(String medicineName5) {
        this.medicineName5 = medicineName5;
    }

    public String getDosage5() {
        return dosage5;
    }

    public void setDosage5(String dosage5) {
        this.dosage5 = dosage5;
    }

    public String getUsage5() {
        return usage5;
    }

    public void setUsage5(String usage5) {
        this.usage5 = usage5;
    }

    public String getRoute5() {
        return route5;
    }

    public void setRoute5(String route5) {
        this.route5 = route5;
    }

    public String getDays5() {
        return days5;
    }

    public void setDays5(String days5) {
        this.days5 = days5;
    }

    public String getStt1() {
        return stt1;
    }

    public void setStt1(String stt1) {
        this.stt1 = stt1;
    }

    public String getMedicineName1() {
        return medicineName1;
    }

    public void setMedicineName1(String medicineName1) {
        this.medicineName1 = medicineName1;
    }

    public String getDosage1() {
        return dosage1;
    }

    public void setDosage1(String dosage1) {
        this.dosage1 = dosage1;
    }

    public String getUsage1() {
        return usage1;
    }

    public void setUsage1(String usage1) {
        this.usage1 = usage1;
    }

    public String getRoute1() {
        return route1;
    }

    public void setRoute1(String route1) {
        this.route1 = route1;
    }

    public String getDays1() {
        return days1;
    }

    public void setDays1(String days1) {
        this.days1 = days1;
    }
}

