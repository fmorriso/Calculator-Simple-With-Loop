import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        displayJavaVersion();
        boolean keepCalculating = true;
        while (keepCalculating)
        {
            performOneCalculation();
            keepCalculating = askIfWeShouldContinueCalculating();
        }
        System.out.println("Thank you for using my calculator");
    }

    /**
     * Asks the user if they want to continue calculating.
     *
     * @return - true if the user wants to continue calculating; otherwise, returns false.
     */
    private static boolean askIfWeShouldContinueCalculating()
    {
        Scanner kb = new Scanner(System.in);
        boolean awaitingValidResponse = true;
        boolean result = false;
        while (awaitingValidResponse)
        {
            System.out.print("Do you want to make another calculation (y/n or press Enter key for yes)?>");
            String response = kb.nextLine();
            // no response at all is the same as Yes
            if (response.length() == 0) 
            {
                result = true;
                awaitingValidResponse = false;
                continue;
            }
            // trim response to a single, lowercase value
            response = response.trim().substring(0, 1).toLowerCase();
            // all we want is just y or n
            if (response.equals("n")) {
                result = false;
                awaitingValidResponse = false;
                continue;
            }
            if (response.equals("y")) {
                result = true;
                awaitingValidResponse = false;
                continue;
            }
            // if we get this far, the user did not give us a proper response,
            // so let them know and ask again.
            System.out.println("Invalid response. Try again.");
        }        
        return result;
    }

    /**
     * Performs one calculation by asking the user for two numbers and an operation to perform on those
     * two numbers.  The numbers, the type of operation and the results of the calculation are displayed.
     */
    private static void performOneCalculation()
    {
        double a = promptForNumber("Enter the first number >");
        double b = promptForNumber("Enter the second number >");
        String operation = getOperation();
        double result = performOperation(a, b, operation);
        System.out.format("result of %.2f %s %.2f = %.2f%n", a, operation, b, result);
    }

    /**
     * Performs the specified operation on the two numbers and returns the result of that operation.
     *
     * @param a         - the first double precision number involved in the operation.
     * @param b         - the second double precision number involved in the operation.
     * @param operation - the type of operation such as + (addition), - (subtraction)
     *                  * (multiplication), / (division) or % (modulo).
     * @return
     */
    private static double performOperation(double a, double b, String operation)
    {
        if (operation.equals("+")) return a + b;
        else if (operation.equals("-")) return a - b;
        else if (operation.equals("*")) return a * b;
        else if (operation.equals("/")) return a / b;
        else if (operation.equals("%")) return a % b;
        else return Double.NaN;
    }

    /**
     * @param msg - the message to display as a prompt for getting the desired type of number.
     *            If an invalid number or something that is not a number is entered, the user
     *            is prompted again.
     * @return - the double precision number.
     */
    private static double promptForNumber(String msg)
    {
        Scanner kb = new Scanner(System.in);
        double num = Double.NaN;
        System.out.print(msg);
        boolean needValidNumber = true;
        do
        {
            try
            {
                num = Double.parseDouble(kb.nextLine());
                needValidNumber = false;
            } catch (NumberFormatException ex)
            {
                System.out.println("That's not a number. Try again.");
            }
        } while (needValidNumber);
        return num;
    }


    /**
     * Prompts for a valid operation. If an invalid operation is entered, the user is
     * informed and is asked for a valid operation again.  The process continues until the user
     * enters a valid operation choice.
     *
     * @return the valid operation
     */
    private static String getOperation()
    {
        Scanner kb = new Scanner(System.in);
        String validOperations = "+-*/%";
        String operation = "";
        boolean waitingForValidOperation = true;
        System.out.println("Valid operations are " + validOperations);
        do
        {
            System.out.print("What operation do you want to perform?>");
            operation = kb.nextLine();
            if (validOperations.indexOf(operation) >= 0)
                waitingForValidOperation = false;
            else
                System.out.println("Invalid choice. Try again.");

        } while (waitingForValidOperation);
        return operation;

    }

    /**
     * Displays the current Java run-time version.
     */
    private static void displayJavaVersion()
    {
        String version = System.getProperty("java.runtime.version");
        System.out.println("java.runtime.version=" + version);
    }
}