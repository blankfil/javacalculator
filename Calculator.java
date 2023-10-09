import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the arithmetic operation: ");
        String operation = scanner.nextLine();

        CalculatorEngine calculatorEngine = new CalculatorEngine();
        try {
            int result = calculatorEngine.calculate(operation);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}

class CalculatorEngine {
    public int calculate(String operation) {
        String[] operationParts = operation.split(" ");

        if (operationParts.length == 3) {
            int operand1 = parseNumber(operationParts[0]);
            String operator = operationParts[1];
            int operand2 = parseNumber(operationParts[2]);

            return performSingleOperation(operand1, operator, operand2);
        } else if (operationParts.length == 5) {
            int operand1 = parseNumber(operationParts[0]);
            String operator1 = operationParts[1];
            int operand2 = parseNumber(operationParts[2]);
            String operator2 = operationParts[3];
            int operand3 = parseNumber(operationParts[4]);

            return performDoubleOperation(operand1, operator1, operand2, operator2, operand3);
        } else {
            throw new IllegalArgumentException("Error: Invalid input!");
        }
    }

    private int parseNumber(String input) {
        int number;
        try {
            number = Integer.parseInt(input);
            if (number < 1 || number > 10) {
                throw new IllegalArgumentException("Error: Numbers must be between 1 and 10 inclusive!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Invalid number format!");
        }
        return number;
    }

    private int performSingleOperation(int operand1, String operator, int operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new IllegalArgumentException("Error: Cannot divide by zero!");
                }
            default:
                throw new IllegalArgumentException("Error: Invalid operator!");
        }
    }

    private int performDoubleOperation(int operand1, String operator1, int operand2, String operator2, int operand3) {
        if (operator1.equals("+") && operator2.equals("-")) {
            return operand1 + operand2 - operand3;
        } else if (operator1.equals("-") && operator2.equals("+")) {
            return operand1 - operand2 + operand3;
        } else if (operator1.equals("*") && operator2.equals("")) {
            return operand1 * operand2 * operand3;
        } else if (operator1.equals("/") && operator2.equals("*")) {
            if (operand2 != 0) {
                return operand1 / operand2 * operand3;
            } else {
                throw new IllegalArgumentException("Error: Cannot divide by zero!");
            }
        } else {
            throw new IllegalArgumentException("Error: Invalid operators or operands!");
        }
    }
}