package com.example.deathnote.DataClass;

public class CrimesData {

    private class Outcome_status{
        String category,date;

        public Outcome_status() {
            category = "Not Available";
            date = "Not Available";
        }

        public Outcome_status(String category, String date) {
            this.category = category;
            this.date = date;
        }
    }

    private class Location{
        private double latitude,longitude;

        public Location(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    private String category;
    private String location_type;
    private String month,id,persistent_id;
    private Outcome_status outcome_status;
    private Location location;

    public CrimesData(String category, String location_type, Location location, String month, String id, String persistent_id,Outcome_status outcome_status) {
        this.category = category;
        this.location_type = location_type;
        this.location = location;
        this.month = month;
        this.id = id;
        this.persistent_id = persistent_id;
        this.outcome_status = outcome_status;
    }

    public CrimesData(String category, String location_type, String month, String id, String persistent_id,Double latitude,Double longitude,String outcomeCategory,String outcomeDate) {
        this.category = category;
        this.location_type = location_type;
        this.month = month;
        this.id = id;
        this.persistent_id = persistent_id;
        this.location = new Location(latitude,longitude);
        this.outcome_status = new Outcome_status(outcomeCategory,outcomeDate);
    }

    public String getOutcome_category() {
        return outcome_status.category;
    }

    public String getOutcome_date(){
        return outcome_status.date;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation_type() {
        return location_type;
    }

    public double getLatitude() {
        return location.latitude;
    }

    public double getLongitude() {
        return location.longitude;
    }

    public Outcome_status getOutcome_status() {
        return outcome_status;
    }

    public String getMonth() {
        return month;
    }

    public String getId() {
        return id;
    }

    public String getPersistent_id() {
        return persistent_id;
    }
}
