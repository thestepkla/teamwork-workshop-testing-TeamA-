import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TeamBdev {
    public static void main(String[] args) {
        // กำหนดเส้นทางไฟล์ที่ต้องการอ่าน
        String filePath = "C:/Output/Textfile.txt";

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

            // แปลงอายุเป็นปีพุทธศักราช
            int buddhistEra = convertToBuddhistEra(age);
            // แปลงเกรดเป็นอันดับ (Rank)
            String rank = convertGradeToRank(grade);

            // แสดงผลข้อมูลบนหน้าจอ
            System.out.println("Name: " + name);
            System.out.println("Buddhist Era: " + buddhistEra);
            System.out.println("Software Testing Rank: " + rank);
        } catch (IOException e) {
            // กรณีที่เกิดข้อผิดพลาดในการอ่านไฟล์
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // ฟังก์ชันแปลงอายุเป็นปีพุทธศักราช
    public static int convertToBuddhistEra(int age) {
        int currentChristianYear = 2024; // ปีปัจจุบันในคริสต์ศักราช
        return (currentChristianYear - age) + 543; // คำนวณปีพุทธศักราช
    }

    // ฟังก์ชันแปลงเกรดเป็นอันดับ (Rank)
    public static String convertGradeToRank(String grade) {
        switch (grade) {
            case "A":
                return "High Distinction";
            case "B+":
                return "Distinction";
            case "B":
                return "Distinction";
            case "C+":
                return "Good";
            case "C":
                return "Good";
            case "D+":
                return "Normal";
            case "D":
                return "Normal";
            case "F":
                return "Failed";
            default:
                return "Unknown Rank"; // กรณีที่เกรดไม่ตรงกับเงื่อนไข
        }
    }
}
