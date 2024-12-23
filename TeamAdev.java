import java.util.Scanner;
import java.util.*;
import java.time.*;


public class TeamAdev {

    protected static int calcuateAge(int yearOfBirth) {
        
        // ดึงปีปัจจุบัน
        int currentYear = LocalDate.now().getYear();

        // ถ้าปีเกิดมากกว่าปีปัจจุบัน ให้โยนข้อผิดพลาด
        if (yearOfBirth > currentYear) {
            throw new IllegalArgumentException("Invalid year of birth");
        }

        // คำนวณอายุ
        return currentYear - yearOfBirth;
    }

    //calcuate grades
    protected static String calculateGrade(float score) {
        if (score >= 80){
            return "A";
        } else if (score >= 75){
            return "B+";
        } else if (score >= 70){
            return "B";
        } else if (score >= 65){
            return "C+";
        } else if (score >= 60){
            return "C";
        } else if (score >= 55){
            return "D+";
        } else if (score >= 50){
            return "D";
        } else {
            return "F";
        }
    }

    //create text file
    public static void createFile(String name, int age, String grade) {
        try {
            Formatter formatter = new Formatter("c:/Output/Textfile.txt");
            formatter.format("Name : %s\n", name);
            formatter.format("Age : %d\n", age);
            formatter.format("Software Testing Grade : %s", grade);
            formatter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try{
            String name = "";
            int age = 0;
            String grade = "";

            // สร้าง Scanner object
            Scanner scanner = new Scanner(System.in);
            // รับค่าชื่อ
            System.out.print("Name : ");
            if (scanner.hasNextLine()){
                name = scanner.nextLine();
            } else {
                System.out.println("Invalid input name must be a string");
                scanner.close();
                return;
            }


            //รับค่าปีเกิด
            System.out.print("Year of birth : ");
            if (scanner.hasNextInt()){
                int yearOfBirth = scanner.nextInt();
                // คำนวณอายุ
                age = calcuateAge(yearOfBirth); 
            } else {
                System.out.println("Invalid input year of birth must be a number");
                
                scanner.close();
                return;
            }

            //รับค่าคะแนน
            System.out.print("Score : ");
            if (scanner.hasNextFloat()){
                float score = scanner.nextFloat();
                grade = calculateGrade(score);
            }else{
                System.out.println("Invalid input score must be a number");
                scanner.close();
                return;
            }


            //create text file
            createFile(name, age, grade);

            // ปิด Scanner object
            scanner.close();
        } catch (Exception e) {
            if (e.getMessage().equals("For input string: ''")) {
                System.out.println("Invalid input");
            } else if (e.getMessage().equals("Invalid year of birth")) {
                System.out.println("Invalid year of birth");
            } else {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }
}