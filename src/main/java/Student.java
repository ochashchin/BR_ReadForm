import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;

public class Student {

    private int ID;
    private String
            firstName;
    private String lastName;
    private String email;
    private String gender;
    private String mobile;
    private String dob;
    private String currentAddress;
    private String state;
    private String city;
    private File photo;
    private String f = new File("student.xlsx").getAbsolutePath();
    private File file = new File(f.substring(0, f.length() - "student.xlsx".length()) + "\\src\\main\\resources\\student.xlsx");

    private String[]
            hobbies = new String[]{},
            subjects = new String[]{};

    Student(int ID) {
        this.ID = ID;
        parse(ID);
    }

    private void parse(int idRow) {
        FileInputStream inputStream = null;
        try {

            inputStream = new FileInputStream(file);

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);

            Row getRow = firstSheet.getRow(idRow);

            Iterator<Cell> cellIterator = getRow.cellIterator();

            int i = 0;

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                String c = "";
                switch (cell.getCellType()) {
                    case NUMERIC -> c = String.valueOf(cell.getNumericCellValue());
                    case STRING -> c = cell.getStringCellValue();
                }

                if (i != 0) {
                    switch (i) {
                        case 1 -> firstName = c;
                        case 2 -> lastName = c;
                        case 3 -> email = c;
                        case 4 -> gender = c;
                        case 5 -> mobile = c;
                        case 6 -> dob = c;
                        case 7 -> subjects = c.split("\\n");
                        case 8 -> hobbies = c.split("\\n");
                        case 9 -> photo = new File(f.substring(0, f.length() - "student.xlsx".length()) + "\\src\\main\\resources\\" + c);
                        case 10 -> currentAddress = c;
                        case 11 -> state = c;
                        case 12 -> city = c;
                    }
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Student student) {
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);

            XSSFRow row = spreadsheet.getRow(student.getID());
            Cell cell = row.createCell(13);
            cell.setCellValue("PASS");


            FileOutputStream out = new FileOutputStream(new File(f.substring(0, f.length() - "student.xlsx".length()) + "\\src\\main\\resources\\studentUpdated.xlsx"));
            workbook.write(out);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDob() {
        return dob;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public File getPhoto() {
        return photo;
    }

    public String[] getHobbies() {
        return hobbies;
    }
}
