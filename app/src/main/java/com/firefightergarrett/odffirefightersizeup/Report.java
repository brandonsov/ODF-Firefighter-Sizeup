package com.firefightergarrett.odffirefightersizeup;

import java.util.ArrayList;

public class Report {
    private String fireNumber,fireName,commander,description,latAndLong, spreadPotential;
    private ArrayList<String> incidentStatus, fuelType;
    private int incidentSize,slope;

    private Report(ReportBuilder builder) {
        this.fireNumber = builder.fireNumber;
        this.fireName = builder.fireName;
        this.commander = builder.commander;
        this.description = builder.description;
        this.latAndLong = builder.latAndLong;
        this.spreadPotential = builder.spreadPotential;
        this.incidentStatus = builder.incidentStatus;
        this.fuelType = builder.fuelType;
        this.incidentSize = builder.incidentSize;
        this.slope = builder.slope;
    }

    public static class ReportBuilder {
        private String fireNumber,fireName,commander,description,latAndLong, spreadPotential;
        private ArrayList<String> incidentStatus, fuelType;
        private int incidentSize,slope;

        public ReportBuilder(String fireNumber, String fireName) {
            this.fireNumber = fireNumber;
            this.fireName = fireName;
        }

        public ReportBuilder commander(String commander) {
            this.commander = commander;
            return this;
        }

        public ReportBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ReportBuilder latAndLong(String latAndLong) {
            this.latAndLong = latAndLong;
            return this;
        }

        public ReportBuilder spreadPotential(String spreadPotential) {
            this.spreadPotential = spreadPotential;
            return this;
        }

        public ReportBuilder incidentStatus(ArrayList<String> incidentStatus) {
            this.incidentStatus = incidentStatus;
            return this;
        }

        public ReportBuilder fuelType(ArrayList<String> fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public ReportBuilder incidentSize(int incidentSize) {
            this.incidentSize = incidentSize;
            return this;
        }

        public ReportBuilder slope(int slope) {
            this.slope = slope;
            return this;
        }
    }

}
