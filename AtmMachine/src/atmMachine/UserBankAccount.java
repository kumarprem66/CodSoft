package atmMachine;

public class UserBankAccount implements UserInterface{


    private double balance;

    public UserBankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean withdraw(double amount) {

        if(this.balance >= amount){
            this.balance -= amount;
            return true;
        }
        return false;

    }

    @Override
    public void deposit(double amount) {

        this.balance += amount;


    }


}
