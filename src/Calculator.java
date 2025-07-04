import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {

    public static double add(double[] numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static double subtract(double[] numbers) {
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result -= numbers[i];
        }
        return result;
    }

    public static double multiply(double[] numbers) {
        double result = 1;
        for (double num : numbers) {
            result *= num;
        }
        return result;
    }

    public static double divide(double[] numbers) {
        double result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            result /= numbers[i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> numbersList = new ArrayList<>();

        System.out.println("Welcome to the Calculator!");
        System.out.println("Enter numbers one by one (type 'done' to finish):");

        while (true) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            try {
                numbersList.add(Double.parseDouble(input));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'done' to finish.");
            }
        }

        if (numbersList.size() < 1) {
            System.out.println("You must enter at least one number!");
            scanner.close();
            return;
        }

        double[] numbers = numbersList.stream().mapToDouble(Double::doubleValue).toArray();

        try {
            System.out.println("\nResults for all operations:");
            System.out.println("Addition: " + add(numbers));
            System.out.println("Subtraction: " + subtract(numbers));
            System.out.println("Multiplication: " + multiply(numbers));
            System.out.println("Division: " + divide(numbers));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
