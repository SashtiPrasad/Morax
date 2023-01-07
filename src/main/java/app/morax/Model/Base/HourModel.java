package app.morax.Model.Base;

public class HourModel {

    private int hour24;
    private int hour12;
    private String AMPM;

    public HourModel(String h, String ampm) {
        if (ampm == null) {
            this.hour24 = Integer.parseInt(h);
            if (hour24 > 12) {
                AMPM = "PM";
                hour12 = hour24 - 12;
            }
            else {
                AMPM = "AM";
                hour12 = hour24;
            }
        }
        else {
            AMPM = ampm;
            this.hour12 = Integer.parseInt(h);
            if (AMPM.equals("PM")) {
                this.hour24 = hour12 + 12;
            }
            else {
                this.hour24 = this.hour12;
            }
        }
    }

    public String getHour24() {
        if (hour24 < 10) {
            return "0" + String.valueOf(hour24);
        }
        else {
            return String.valueOf(hour24);
        }
    }

    public String getHour12() {
        if (hour12 < 10) {
            return "0" + String.valueOf(hour12);
        }
        else {
            return String.valueOf(hour12);
        }
    }

    public String getAMPM() {
        return AMPM;
    }
}
