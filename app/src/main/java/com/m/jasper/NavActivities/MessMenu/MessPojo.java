package com.m.jasper.NavActivities.MessMenu;
public class MessPojo {
        public String getBreakFast() {
            return breakFast;
        }

        public void setBreakFast(String breakFast) {
            this.breakFast = breakFast;
        }

        public String getLunch() {
            return lunch;
        }

        public void setLunch(String lunch) {
            this.lunch = lunch;
        }

        public String getDinner() {
            return dinner;
        }

        public void setDinner(String dinner) {
            this.dinner = dinner;
        }

        String breakFast;

    public MessPojo(String day,String breakFast, String lunch, String dinner) {
        this.breakFast = breakFast;
        this.day=day;
        this.lunch = lunch;
        this.dinner = dinner;
    }
    public MessPojo(){

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    String day;
    String lunch;
    String dinner;

}
