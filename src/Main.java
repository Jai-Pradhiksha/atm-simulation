/*
Write a Java program that simulates an ATM machine. The program should handle the following exceptions:
InsufficientFundsException: Thrown when a user tries to withdraw an amount greater than their account balance.
InvalidAmountException: Thrown when a user tries to deposit/withdraw a negative or zero amount.
The program should have the following functionalities:
Initialise the account balance with a default value.
Provide options for the user to deposit or withdraw funds.
Handle exceptions appropriately and display meaningful error messages to the user.
 */

import java.util.Scanner;

class ATM {
    private int accountBalance;

    public int getAccountBalance() {
        return accountBalance;
    }

    public void addMoney(int amountToBeDeposited) {
        accountBalance += amountToBeDeposited;
    }

    public void takeMoney(int amountToBeWithdrawn) {
        accountBalance -= amountToBeWithdrawn;
    }
}

class IndianBankATM extends ATM {

    public void initialize() throws InvalidAmountException, InsufficientFundsException {
        Scanner scanner = new Scanner(System.in);

        int userChoice, inputAmount;

        do {
            System.out.println("____________");
            System.out.println("1 - Deposit Amount");
            System.out.println("2 - Withdraw Amount");
            System.out.println("3 - Check Balance");
            System.out.println("4 - Exit");
            System.out.println("____________");
            System.out.print("Enter your preferred option ... ");

            userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1:
                    inputAmount = readAmount("Enter the amount to be deposited ... Rs.");
                    if (isValidAmount(inputAmount)) {
                        depositAmount(inputAmount);
                    }
                    break;
                case 2:
                    inputAmount = readAmount("Enter the amount to be withdrawn ... Rs.");
                    if (isValidAmount(inputAmount)) {
                        if (getAccountBalance() < inputAmount) {
                            throw new InsufficientFundsException("Insufficient account balance.");
                        }
                        withDrawAmount(inputAmount);
                    }
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you, Have a nice day :)");
                    System.exit(0);
                default:
                    System.out.println("____________");
                    System.out.println("Invalid option, please retry with a valid option");
            }
        } while (true);
    }

    private int readAmount(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private boolean isValidAmount(int inputAmount) throws InvalidAmountException {
        if (inputAmount > 0) {
            return true;
        }
        throw new InvalidAmountException("Amount must be greater than 0.");
    }

    private void depositAmount(int inputAmount) {
        addMoney(inputAmount);
        checkBalance();
    }

    private void withDrawAmount(int inputAmount) {
        takeMoney(inputAmount);
        checkBalance();
    }

    private void checkBalance() {
        System.out.println("____________");
        System.out.println("Your balance is Rs. " + getAccountBalance());
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class ATMUserInterface {
    private IndianBankATM atm;

    public ATMUserInterface(IndianBankATM atm) {
        this.atm = atm;
    }

    public void start() {
        try {
            atm.initialize();
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        IndianBankATM ATM = new IndianBankATM();
        ATMUserInterface userInterface = new ATMUserInterface(ATM);
        userInterface.start();
    }
}
