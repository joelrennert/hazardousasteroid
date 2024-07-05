package org.joelr.util;

import org.joelr.model.NasaNeoResponse;

import java.text.DecimalFormat;

public class AsteroidInfoBuilder {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.##");

    public String buildAsteroidInfo(NasaNeoResponse.NearEarthObject neo) {
        StringBuilder sb = new StringBuilder();

        sb.append("<b>").append(neo.getName()).append("</b><br/>");
        sb.append("<b>ID:</b> ").append(neo.getId()).append("<br/>");
        sb.append("<b>Is Potentially Hazardous:</b> ").append(neo.isPotentiallyHazardousAsteroid()).append("<br/>");

        NasaNeoResponse.NearEarthObject.EstimatedDiameter.Diameter diameterKm = neo.getEstimatedDiameter().getKilometers();
        double minDiameterKm = diameterKm.getEstimatedDiameterMin();
        double maxDiameterKm = diameterKm.getEstimatedDiameterMax();
        double minDiameterMiles = minDiameterKm * 0.62137119;
        double maxDiameterMiles = maxDiameterKm * 0.62137119;
        sb.append("<b>Estimated Diameter:</b> ")
                .append(formatNumber(minDiameterKm)).append(" km to ").append(formatNumber(maxDiameterKm)).append(" km, ")
                .append(formatNumber(minDiameterMiles)).append(" miles to ").append(formatNumber(maxDiameterMiles)).append(" miles").append("<br/>");

        if (neo.getCloseApproachData() != null && !neo.getCloseApproachData().isEmpty()) {
            NasaNeoResponse.NearEarthObject.CloseApproachData.MissDistance missDistance = neo.getCloseApproachData().get(0).getMissDistance();
            if (missDistance != null && missDistance.getKilometers() != null) {
                double kilometers = Double.parseDouble(missDistance.getKilometers());
                double miles = kilometers * 0.62137119;
                sb.append("<b>Miss Distance:</b> ").append(formatNumber(kilometers)).append(" km, ").append(formatNumber(miles)).append(" miles").append("<br/>");
            } else {
                sb.append("<b>Miss Distance:</b> Not available<br/>");
            }
        } else {
            sb.append("<b>Miss Distance:</b> Not available<br/>");
        }

        return sb.toString();
    }

    private String formatNumber(double number) {
        if (number >= 1000000) {
            return decimalFormat.format(number / 1000000) + " million";
        } else if (number >= 1000) {
            return decimalFormat.format(number / 1000) + " thousand";
        } else {
            return decimalFormat.format(number);
        }
    }
}