package com.example.loveforhealth;

class Workout {
    private String date;
    private String type;
    private int duration;

    Workout (String date, String type, int duration)
    {
        this.date = date;
        this.type = type;
        this.duration = duration;
    }


    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
