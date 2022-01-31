package Tests;

import com.logmonitor.utils.FileHelper;
import com.logmonitor.utils.LogChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class UnitTests {

    @Test
    public void test1() throws IOException {
        List<String> testLog =
                new LogChecker(System.getProperty("user.dir") + "\\src\\test\\resources\\Log.log")
                        .resultReport();
        File newReport = new File(System.getProperty("user.dir") + "\\build\\resources\\test\\newReport.txt");
        FileHelper.saveTextToFile(testLog, newReport);
        String actualReport = new String(Files.readAllBytes(newReport.toPath()), "Windows-1251");

        File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\ExampleReport.txt");
        String expectedReport = new String(Files.readAllBytes(file.toPath()), "Windows-1251");

        Assertions.assertEquals(expectedReport, actualReport, "Эталонный и сформированный отчёты не одинаковы");
    }
}
