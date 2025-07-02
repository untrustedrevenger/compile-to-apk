/**
 * Calculator - A comprehensive calculator class demonstrating Java OOP principles
 * This class provides basic arithmetic operations and advanced mathematical functions.
 */
public class Calculator {
    
    // Instance variables for calculator state
    private double lastResult;
    private String lastOperation;
    
    // Constructor
    public Calculator() {
        this.lastResult = 0.0;
        this.lastOperation = "none";
    }
    
    /**
     * Basic arithmetic operations
     */
    public double add(double a, double b) {
        double result = a + b;
        updateState(result, "addition");
        return result;
    }
    
    public double subtract(double a, double b) {
        double result = a - b;
        updateState(result, "subtraction");
        return result;
    }
    
    public double multiply(double a, double b) {
        double result = a * b;
        updateState(result, "multiplication");
        return result;
    }
    
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed!");
        }
        double result = a / b;
        updateState(result, "division");
        return result;
    }
    
    /**
     * Advanced mathematical operations
     */
    public double power(double base, double exponent) {
        double result = Math.pow(base, exponent);
        updateState(result, "exponentiation");
        return result;
    }
    
    public double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number!");
        }
        double result = Math.sqrt(number);
        updateState(result, "square root");
        return result;
    }
    
    public double factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers!");
        }
        if (n > 20) {
            throw new IllegalArgumentException("Factorial too large to calculate!");
        }
        
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        updateState(result, "factorial");
        return result;
    }
    
    public double logarithm(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Logarithm is only defined for positive numbers!");
        }
        double result = Math.log(number);
        updateState(result, "natural logarithm");
        return result;
    }
    
    public double logarithm10(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Logarithm is only defined for positive numbers!");
        }
        double result = Math.log10(number);
        updateState(result, "base-10 logarithm");
        return result;
    }
    
    /**
     * Trigonometric functions (angles in radians)
     */
    public double sine(double angle) {
        double result = Math.sin(angle);
        updateState(result, "sine");
        return result;
    }
    
    public double cosine(double angle) {
        double result = Math.cos(angle);
        updateState(result, "cosine");
        return result;
    }
    
    public double tangent(double angle) {
        double result = Math.tan(angle);
        updateState(result, "tangent");
        return result;
    }
    
    /**
     * Utility functions
     */
    public double percentage(double value, double percent) {
        double result = (value * percent) / 100;
        updateState(result, "percentage calculation");
        return result;
    }
    
    public double absolute(double number) {
        double result = Math.abs(number);
        updateState(result, "absolute value");
        return result;
    }
    
    public double ceiling(double number) {
        double result = Math.ceil(number);
        updateState(result, "ceiling");
        return result;
    }
    
    public double floor(double number) {
        double result = Math.floor(number);
        updateState(result, "floor");
        return result;
    }
    
    public double round(double number, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        double result = Math.round(number * scale) / scale;
        updateState(result, "rounding");
        return result;
    }
    
    /**
     * Calculator state management
     */
    private void updateState(double result, String operation) {
        this.lastResult = result;
        this.lastOperation = operation;
    }
    
    public double getLastResult() {
        return lastResult;
    }
    
    public String getLastOperation() {
        return lastOperation;
    }
    
    public void clearMemory() {
        this.lastResult = 0.0;
        this.lastOperation = "none";
    }
    
    /**
     * Display calculator information
     */
    public void printCalculatorInfo() {
        System.out.println("🧮 Advanced Java Calculator");
        System.out.println("===========================");
        System.out.println("Last Result: " + lastResult);
        System.out.println("Last Operation: " + lastOperation);
        System.out.println("");
        System.out.println("Available Operations:");
        System.out.println("• Basic: +, -, ×, ÷");
        System.out.println("• Advanced: power, square root, factorial");
        System.out.println("• Logarithmic: ln, log10");
        System.out.println("• Trigonometric: sin, cos, tan");
        System.out.println("• Utility: percentage, absolute, ceiling, floor, round");
    }
    
    /**
     * Static utility methods for quick calculations
     */
    public static double quickAdd(double a, double b) {
        return a + b;
    }
    
    public static double quickMultiply(double a, double b) {
        return a * b;
    }
    
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
    
    public static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
    
    /**
     * Convert degrees to radians and vice versa
     */
    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }
    
    public static double radiansToDegrees(double radians) {
        return radians * 180 / Math.PI;
    }
}