import atmMachine.Atm;
import atmMachine.UserBankAccount;

public class Main {
    public static void main(String[] args) {


        UserBankAccount userBankAccount = new UserBankAccount(5000);
        Atm atm = new Atm(userBankAccount);
        atm.performTransaction();
    }
}