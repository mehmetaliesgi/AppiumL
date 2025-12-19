package tests;

import utils.helpers.ExcelReader;

public class ExcelReaderTest {

    public static void main(String[] args) {
        ExcelReader reader = new ExcelReader("src/test/resources/testdata.xlsx", "LoginData");

        System.out.println("Toplam Satır: " + reader.getRowCount());
        System.out.println("Toplam Sütun: " + reader.getColumnCount());
        System.out.println();

        System.out.println("------- TÜM TEST DATA -------");
        for (int i = 1; i <= reader.getRowCount(); i++) {
            String testCase = reader.getCellData(i, "TestCase");
            String email = reader.getCellData(i, "Email");
            String password = reader.getCellData(i, "Password");

            System.out.println("Satır " + i + ":");
            System.out.println("  TestCase: " + testCase);
            System.out.println("  Email: " + email);
            System.out.println("  Password: " + password);

            System.out.println();
        }

        reader.close();
    }
}