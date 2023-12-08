package studentGradeCalculator;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GradeCalculator {


    private static final List<String> subjects = new ArrayList<>();
    private static final List<Integer> marks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean calculate_again = true;
        while(calculate_again){
            try {

                System.out.println("Enter how many subjects you hava..");
                int numberOfSubjects = scanner.nextInt();
                int count = 0;
                System.out.println("Enter all subjects (subject name per line)");
                while (count<numberOfSubjects){

                    try {
                        String enterSubject = scanner.next();
                        subjects.add(enterSubject);
                        count++;
                    }catch (InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Please enter valid input");
                    }
                }
                System.out.println("Enter marks in each subject");
                int count2 = 0;
                while (count2<numberOfSubjects){

                    try{
                        System.out.println(subjects.get(count2) +" : ");

                        int enterMarks = scanner.nextInt();
                        if(enterMarks<=100){
                            marks.add(enterMarks);
                            count2++;
                        }else{
                            System.out.println("Please enter valid range -> 0-100 ");
                        }
                    }catch (Exception e){
                        scanner.nextLine();
                        System.out.println("Enter valid input..");
                    }


                }
                int marksSum = marks.stream().mapToInt(value -> value).sum();
                double avg  = (double) marksSum /numberOfSubjects;

                avg = Math.round(avg*100.0)/100.0;
                System.out.println("Your Total Marks         Percentage         Grade");
                System.out.println("     "+marksSum+"                   "+avg+" %"+"      "+grading(avg));

                calculate_again = false;

            }catch (Exception e){
                System.out.println("Please enter valid input");

                scanner.nextLine();

            }

        }

    }

    public static String grading(double avgMarks){
        if(avgMarks >= 90){
            return "Grade - A++";
        }else if(avgMarks >=70){
            return "Grade - A+";
        }else if(avgMarks >= 60){
            return "Grade - A";
        }
        else if(avgMarks >=50){
            return "Grade - B";
        }else if (avgMarks >= 30){
            return "Grade -C";
        }else{
            return "Fail";
        }
    }
}

