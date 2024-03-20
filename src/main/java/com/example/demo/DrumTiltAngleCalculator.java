package com.example.demo;

import java.awt.geom.Point2D;

public class DrumTiltAngleCalculator {

    public static void main(String[] args) {
        // Define the known points P1, P2, P3, and P4
        Point2D.Double p1 = new Point2D.Double(0, 0);  // Drum left side point
        Point2D.Double p2 = new Point2D.Double(2, 0);  // Drum right side point
        Point2D.Double p3 = new Point2D.Double(1, 1);  // Wheel side right point
        Point2D.Double p4 = new Point2D.Double(1.5, 2);  // Device point (on vehicle rooftop)

        // Calculate vectors from P1 to P4 and P1 to P3
        Point2D.Double vectorP1P4 = new Point2D.Double(p4.getX() - p1.getX(), p4.getY() - p1.getY());
        Point2D.Double vectorP1P3 = new Point2D.Double(p3.getX() - p1.getX(), p3.getY() - p1.getY());

        // Calculate dot product of the two vectors
        double dotProduct = vectorP1P4.x * vectorP1P3.x + vectorP1P4.y * vectorP1P3.y;

        // Calculate magnitudes of the vectors
        double magP1P4 = Math.sqrt(vectorP1P4.x * vectorP1P4.x + vectorP1P4.y * vectorP1P4.y);
        double magP1P3 = Math.sqrt(vectorP1P3.x * vectorP1P3.x + vectorP1P3.y * vectorP1P3.y);

        // Calculate the angle in radians
        double angleRadians = Math.acos(dotProduct / (magP1P4 * magP1P3));

        // Convert angle to degrees
        double angleDegrees = Math.toDegrees(angleRadians);

        System.out.println("Tilted angle of the drum: " + angleDegrees + " degrees");
    }
}
