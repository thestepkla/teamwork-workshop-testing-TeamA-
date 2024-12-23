import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.*;
import java.util.*;


class returnFile {
    public String name;
    public int age;
    public String grade;

    public returnFile(String name, int age, String grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}

public class TeamAtest {
    //calculateYears พ.ศ เกิด สำหรับไว้เช็คว่าคำนวณถูกต้องหรือไม่ จาก อายุ
    private static int calculateYears(int age) {
        LocalDate date = LocalDate.now();
        int year = date.getYear() + 543;
        return year - age;
    }

    //calRank สำหรับคำนวณเกรด
    private static String calRank(String grade) {
        String result = "";
        if (grade.equals("A")) {
            result = "High Distinction";
        } else if (grade.equals("B") || grade.equals("B+")) {
            result = "Distinction";
        } else if (grade.equals("C") || grade.equals("C+")) {
            result = "Good";
        } else if (grade.equals("D") || grade.equals("D+")) {
            result = "Normal";
        } else if (grade.equals("F")) {
            result = "Failed";
        }
        return result;
    }

    private static String genarateOutput(String name, int year, String rank) {
        StringBuilder result = new StringBuilder();
        result.append("Name: ").append(name).append("\n");
        result.append("Buddhist Era: ").append(year).append("\n");
        result.append("Software Testing Rank: ").append(rank); // No \n here
    
        return result.toString();
    }
    
    
    

    private static returnFile readFileoutput(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line; // ใช้เก็บแต่ละบรรทัดที่อ่านจากไฟล์
            String name = ""; // เก็บชื่อผู้ใช้งานจากไฟล์
            int age = 0; // เก็บอายุจากไฟล์
            String grade = ""; // เก็บเกรดจากไฟล์

            // อ่านไฟล์ทีละบรรทัด
            while ((line = br.readLine()) != null) {
                // ตรวจสอบว่าบรรทัดเริ่มต้นด้วย "Name" หรือไม่
                if (line.startsWith("Name")) {
                    name = line.split(":")[1].trim(); // แยกข้อมูลชื่อออกมา
                } else if (line.startsWith("Age")) {
                    age = Integer.parseInt(line.split(":")[1].trim()); // แยกข้อมูลอายุออกมาและแปลงเป็นตัวเลข
                } else if (line.startsWith("Software Testing Grade")) {
                    grade = line.split(":")[1].trim(); // แยกข้อมูลเกรดออกมา
                }
            }

            return new returnFile(name, age, grade);
        } catch (Exception e) {
            // กรณีที่เกิดข้อผิดพลาดในการอ่านไฟล์
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }
    }


    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    
    private static String normalizeWhitespace(String input) {
        return input.replaceAll("\\s+", " ").trim();
    }

    public static void createFileOutput(String name, int age, String grade, String filePath) {
        try {
            Formatter formatter = new Formatter(filePath);
            formatter.format("Name : %s\n", name);
            formatter.format("Age : %d\n", age);
            formatter.format("Software Testing Grade : %s", grade);
            formatter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvSource({ "2547, 20", "2546, 21", "2545, 22", "2544, 23", "2537, 30", "2542, 25", "2540, 27", "2539, 28" })
    public void testConvertToBuddhistEra(int expected, int age) {
        int convert = TeamBdev.convertToBuddhistEra(age);
        assertEquals(expected, convert);
    }

    @ParameterizedTest
    @CsvSource({ 
        "High Distinction, A", 
        "Distinction, B", 
        "Distinction, B+", 
        "Good, C", 
        "Good, C+", 
        "Normal, D", 
        "Normal, D+", 
        "Failed, F" 
    })
    public void testConvertGradeToRank(String expected, String grade) {
        String convert = TeamBdev.convertGradeToRank(grade);
        assertEquals(expected, convert);
    }

    @ParameterizedTest
    @CsvSource({ 
        "John, 20, A", 
        "Jane, 21, B",
        "Jack, 22, B+",
        "Jim, 22, C",
        "CCA, 23, C+",
        "SCA, 30, D",
        "CSS, 2, D+",
        "DEC, 5, F"
    })
    public void testMain(String name, int age, String grade) {

        outContent.reset();

        String filePath = "C:/Output/Textfile.txt";

        createFileOutput(name, age, grade, filePath);

        //หน่วงเวลาเพื่อให้ไฟล์เขียนเสร็จ
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        returnFile inputData = readFileoutput(filePath);

        int CalYear =  calculateYears(inputData.age);
        String rank = calRank(inputData.grade);

        String output = genarateOutput(inputData.name, CalYear, rank);

        // เรียกใช้ function จาก TeamB เพื่อตรวจสอบ
        TeamBdev.main(null);
        
        assertEquals(normalizeWhitespace(output), normalizeWhitespace(outContent.toString()), "Output not match");
    }
}