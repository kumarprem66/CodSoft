package numberGame;

import java.util.Scanner;

public class RandomNumber {

    public static void main(String[] args) {

        System.out.println("Hello! WELCOME TO NUMBER GUESSING GAME");

        int userScore = 0;

        boolean wantToExit = false;
        int round = 1;
        while (!wantToExit){
            int attempt = 1;
            int totalAttempt = 5;
            System.out.println("You have total "+totalAttempt +" attempts to guess the number");

            int generatedNumber = generateRandomNumber();
            System.out.println(generatedNumber);
            String isPrime = checkPrime(generatedNumber);
            String oddEven = oddOrEven(generatedNumber);
            System.out.println("(Hint: - Number is "+oddEven+" and "+isPrime+")");
            Scanner scanner = new Scanner(System.in);
            boolean playing = true;
            while (playing){
                try {
                    System.out.println("Enter your number : ");
                    int userInput = scanner.nextInt();

                    if (userInput<1 || userInput>100){
                        System.out.println("Please Enter Number between 1-100");
                    }else{

                        String output = nearnessOfNumberGuessed(generatedNumber,userInput);
                        System.out.println(output);
                        if(output.equalsIgnoreCase("correct")) {
                            userScore++;
                            playing = false;
                        }
                        System.out.println("Your Score score/attempt: "+userScore+"/"+attempt);
                        System.out.println("Attempt Remaining : "+(totalAttempt-attempt));

                        attempt++;
                        if(attempt>totalAttempt){
                            playing = false;
                        }


                    }
                }catch (Exception e){
                    System.out.println("Enter Correct input....");
                    scanner.nextLine();

                }
            }
            System.out.println();
            System.out.println("=================  Result ==================");
            System.out.println("Correct Number was : "+generatedNumber);
            System.out.println("Your Current Score : " + userScore);
            System.out.println("Do you want to again type 'y' for 'n'");
            String playAgain = scanner.next();
            if(!playAgain.equals("y")){
                wantToExit = true;
            }else{
                round++;
            }
        }


        System.out.println("Result : "+(userScore*100/round) + " % ");



    }
    public static int generateRandomNumber(){
        double d = Math.random();

        return (int) (1+d*100);

    }

    public static String nearnessOfNumberGuessed(int randomNumber,int guessedNumber){
        if((randomNumber-guessedNumber)>=50) {
            return "Too Low (diff > 50)";
        }else if((randomNumber-guessedNumber)>=40) {
            return "Low (diff > 40)";
        }else if((randomNumber-guessedNumber)>=20){
            return "Low (diff > 20)";
        }else if((guessedNumber-randomNumber)>=50){
            return "Too High (diff > 20)";
        }else if((guessedNumber-randomNumber)>=40) {
            return "High (diff > 20)";
        }else if((guessedNumber-randomNumber)>=20) {
            return "High (diff > 20)";
        }
        else if(randomNumber == guessedNumber){
            return "Correct";
        }else if((randomNumber-guessedNumber)>=10){
            return "little lesser (diff > 10)";
        }else if((guessedNumber-randomNumber)>=10){
            return "Little Higher (diff > 10)";
        }else{
            return "too close (diff < 10)";
        }
    }

    public static String oddOrEven(int number){
        if(number%2==0){
            return "Even";
        }else {
            return "odd";
        }
    }

    public static String checkPrime(int number){
        if(number == 1){
            return "Composite Number";
        }
        for(int i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return "Composite Number";
            }
        }
        return "Prime Number";
    }


}
