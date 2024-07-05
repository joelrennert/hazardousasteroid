package org.joelr.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaNeoResponse {
    @JsonProperty("near_earth_objects")
    private List<NearEarthObject> nearEarthObjects;

    public List<NearEarthObject> getNearEarthObjects() {
        return nearEarthObjects;
    }

    public void setNearEarthObjects(List<NearEarthObject> nearEarthObjects) {
        this.nearEarthObjects = nearEarthObjects;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NearEarthObject {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("is_potentially_hazardous_asteroid")
        private boolean isPotentiallyHazardousAsteroid;

        @JsonProperty("estimated_diameter")
        private EstimatedDiameter estimatedDiameter;

        @JsonProperty("close_approach_data")
        private List<CloseApproachData> closeApproachData;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPotentiallyHazardousAsteroid() {
            return isPotentiallyHazardousAsteroid;
        }

        public void setPotentiallyHazardousAsteroid(boolean potentiallyHazardousAsteroid) {
            isPotentiallyHazardousAsteroid = potentiallyHazardousAsteroid;
        }

        public EstimatedDiameter getEstimatedDiameter() {
            return estimatedDiameter;
        }

        public void setEstimatedDiameter(EstimatedDiameter estimatedDiameter) {
            this.estimatedDiameter = estimatedDiameter;
        }

        public List<CloseApproachData> getCloseApproachData() {
            return closeApproachData;
        }

        public void setCloseApproachData(List<CloseApproachData> closeApproachData) {
            this.closeApproachData = closeApproachData;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class EstimatedDiameter {
            private Diameter kilometers;

            public Diameter getKilometers() {
                return kilometers;
            }

            public void setKilometers(Diameter kilometers) {
                this.kilometers = kilometers;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Diameter {
                @JsonProperty("estimated_diameter_min")
                private double estimatedDiameterMin;

                @JsonProperty("estimated_diameter_max")
                private double estimatedDiameterMax;

                public double getEstimatedDiameterMin() {
                    return estimatedDiameterMin;
                }

                public void setEstimatedDiameterMin(double estimatedDiameterMin) {
                    this.estimatedDiameterMin = estimatedDiameterMin;
                }

                public double getEstimatedDiameterMax() {
                    return estimatedDiameterMax;
                }

                public void setEstimatedDiameterMax(double estimatedDiameterMax) {
                    this.estimatedDiameterMax = estimatedDiameterMax;
                }
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class CloseApproachData {
            @JsonProperty("miss_distance")
            private MissDistance missDistance;

            public MissDistance getMissDistance() {
                return missDistance;
            }

            public void setMissDistance(MissDistance missDistance) {
                this.missDistance = missDistance;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class MissDistance {
                private String astronomical;
                private String lunar;
                private String kilometers;
                private String miles;

                public String getAstronomical() {
                    return astronomical;
                }

                public void setAstronomical(String astronomical) {
                    this.astronomical = astronomical;
                }

                public String getLunar() {
                    return lunar;
                }

                public void setLunar(String lunar) {
                    this.lunar = lunar;
                }

                public String getKilometers() {
                    return kilometers;
                }

                public void setKilometers(String kilometers) {
                    this.kilometers = kilometers;
                }

                public String getMiles() {
                    return miles;
                }

                public void setMiles(String miles) {
                    this.miles = miles;
                }
            }
        }
    }
}