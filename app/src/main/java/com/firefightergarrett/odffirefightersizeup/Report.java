package com.firefightergarrett.odffirefightersizeup;

import java.io.Serializable;
import java.util.ArrayList;

public class Report implements Serializable {
    private String fireNumber,fireName,commander,description,latAndLong,
            spreadPotential,incidentSize,slope;
    private ArrayList<String> incidentStatus, fuelType;

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

    public String getFireNumber() {
        return fireNumber;
    }

    public String getFireName() {
        return fireName;
    }

    public String getCommander() {
        return commander;
    }

    public String getDescription() {
        return description;
    }

    public String getLatAndLong() {
        return latAndLong;
    }

    public String getSpreadPotential() {
        return spreadPotential;
    }

    public String getIncidentSize() {
        return incidentSize;
    }

    public String getSlope() {
        return slope;
    }

    public static class ReportBuilder {
        private String fireNumber,fireName,commander,description,latAndLong,
                spreadPotential,incidentSize,slope;
        private ArrayList<String> incidentStatus, fuelType;

        public ReportBuilder(String fireNumber, String fireName) {
            this.fireNumber = fireNumber;
            this.fireName = fireName;
        }

        public ReportBuilder setCommander(String commander) {
            this.commander = commander;
            return this;
        }

        public ReportBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ReportBuilder setLatAndLong(String latAndLong) {
            this.latAndLong = latAndLong;
            return this;
        }

        public ReportBuilder setSpreadPotential(String spreadPotential) {
            this.spreadPotential = spreadPotential;
            return this;
        }

        public ReportBuilder setIncidentStatus(ArrayList<String> incidentStatus) {
            this.incidentStatus = incidentStatus;
            return this;
        }

        public ReportBuilder setFuelType(ArrayList<String> fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        public ReportBuilder setIncidentSize(String incidentSize) {
            this.incidentSize = incidentSize;
            return this;
        }

        public ReportBuilder setSlope(String slope) {
            this.slope = slope;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }

}
