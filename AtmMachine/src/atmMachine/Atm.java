package atmMachine;

import java.util.Scanner;

public class Atm {

        private Scanner scanner;
        private UserBankAccount userBankAccount;

        public Atm(UserBankAccount userBankAccount) {
            this.scanner = new Scanner(System.in);
            this.userBankAccount = userBankAccount;
        }

        private void displayMenu() {
            System.out.println("Welcome to Kotak Bank ATM");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
        }

        public void performTransaction() {
            int choice;

            do {
                displayMenu();
                try {
                    System.out.println("Enter your choice:");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1 -> withdraw();
                        case 2 -> deposit();
                        case 3 -> checkBalance();
                        case 4 -> System.out.println("Thank you for using Kotak Bank ATM.");
                        default -> System.out.println("Invalid choice. Please try again.");
                    }

                }catch (Exception e){
                    System.out.println("============= Invalid input. Please enter a valid number.==============");
                    scanner.next();
                    choice = -1;
                }

            } while (choice != 4);
        }

        private void withdraw() {
            boolean is_okay = true;
            while (is_okay){
                try{
                    System.out.println("Enter the withdrawal amount:");
                    double amount = scanner.nextDouble();
                    boolean success = userBankAccount.withdraw(amount);

                    if (success) {
                        System.out.println("Withdrawal successful. Remaining balance: " + userBankAccount.getBalance());
                    } else {
                        System.out.println("Withdrawal failed. Insufficient funds.");
                        checkBalance();
                    }
                    is_okay = false;
                }catch (Exception e){
                    System.out.println("===============Invalid input. Please enter a valid number.=============");
                    scanner.nextLine();
                }
            }

        }

        private void deposit() {
            boolean is_okay = true;
         while(is_okay){
             try {
                 System.out.println("Enter the deposit amount:");
                 double amount = scanner.nextDouble();
                 userBankAccount.deposit(amount);
                 System.out.println("Deposit successful. New balance: " + userBankAccount.getBalance());
                 is_okay = false;
             }catch (Exception e){
                 System.out.println("================Invalid input. Please enter a valid number.===============");
                 scanner.nextLine();
             }
         }
        }

        private void checkBalance() {
            System.out.println("Your current balance is: " + userBankAccount.getBalance());
        }


}
