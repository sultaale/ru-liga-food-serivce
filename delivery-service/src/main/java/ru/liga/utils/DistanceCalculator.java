package ru.liga.utils;

public class DistanceCalculator {
    
        private static final double EARTH_RADIUS = 6371.00;

        /**
         * Method calculates distance between latitude/longitude points using Haversine formula
         *
         * @param courierCoordinates    the coordinates of a courier
         * @param destinationCoordinates the coordinates of a destination
         * @return the distance between two points rounded to 1 decimal place
         */
        public static double calculateDistance(String courierCoordinates, String destinationCoordinates) {
            String[] courier = courierCoordinates.split(",");
            String[] destination = destinationCoordinates.split(",");
            
            double courierLatitude = Double.parseDouble(courier[0]);
            double courierLongitude = Double.parseDouble(courier[1]);
            double destinationLatitude = Double.parseDouble(destination[0]);
            double destinationLongitude = Double.parseDouble(destination[1]);

            double differenceLatitude = Math.toRadians(destinationLatitude - courierLatitude);
            double differenceLongitude = Math.toRadians(destinationLongitude - courierLongitude);

            double squareHalfChord = Math.sin(differenceLatitude / 2) * Math.sin(differenceLatitude / 2) +
                    Math.cos(Math.toRadians(courierLatitude)) * Math.cos(Math.toRadians(destinationLatitude)) *
                            Math.sin(differenceLongitude / 2) * Math.sin(differenceLongitude / 2);

            double centralAngle = 2 * Math.atan2(Math.sqrt(squareHalfChord), Math.sqrt(1 - squareHalfChord));

            double result = EARTH_RADIUS * centralAngle;
            
            return Math.ceil(result * 10)/ 10;
        }
}
