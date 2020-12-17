package com.example.bustracking;

public class UserHelperClass {
    String busNumber,busLocation,busDestination,busStartTime,busReachTime;

    public UserHelperClass(String busNumber, String busLocation, String busDestination, String busStartTime, String busReachTime) {
        this.busNumber = busNumber;
        this.busLocation = busLocation;
        this.busDestination = busDestination;
        this.busStartTime = busStartTime;
        this.busReachTime = busReachTime;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusLocation() {
        return busLocation;
    }

    public void setBusLocation(String busLocation) {
        this.busLocation = busLocation;
    }

    public String getBusDestination() {
        return busDestination;
    }

    public void setBusDestination(String busDestination) {
        this.busDestination = busDestination;
    }

    public String getBusStartTime() {
        return busStartTime;
    }

    public void setBusStartTime(String busStartTime) {
        this.busStartTime = busStartTime;
    }

    public String getBusReachTime() {
        return busReachTime;
    }

    public void setBusReachTime(String busReachTime) {
        this.busReachTime = busReachTime;
    }




}
