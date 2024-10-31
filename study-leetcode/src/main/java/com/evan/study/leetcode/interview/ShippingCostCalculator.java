package com.evan.study.leetcode.interview;

public class ShippingCostCalculator {
    public static double calculateShippingCost(String shippingMethod, double packageWeight) {
        double cost = 0.0;
        if (shippingMethod.equals("Standard")) {
            if (packageWeight <= 1.0) {
                cost = 5.0;
            } else if (packageWeight <= 5.0) {
                cost = 10.0;
            } else {
                cost = 20.0;
            }
        } else if (shippingMethod.equals("Express")) {
            if (packageWeight <= 1.0) {
                cost = 10.0;
            } else if (packageWeight <= 5.0) {
                cost = 20.0;
            } else {
                cost = 40.0;
            }
        } else if (shippingMethod.equals("Overnight")) {
            if (packageWeight <= 1.0) {
                cost = 20.0;
            } else if (packageWeight <= 5.0) {
                cost = 40.0;
            } else {
                cost = 80.0;
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        System.out.println(calculateShippingCost("Standard", 3.0)); // Should return 10.0
        System.out.println(calculateShippingCost("Express", 0.5));  // Should return 10.0
        System.out.println(calculateShippingCost("Overnight", 6.0)); // Should return 80.0
    }
}