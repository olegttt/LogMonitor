package com.logmonitor;

import com.logmonitor.utils.FileHelper;
import com.logmonitor.utils.LogChecker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LogMonitorController {

    private static final String typeLogFile = "*.log";
    private static final String typeReportFile = "*.txt";

    @FXML
    public TextField fullPathToLogFile;

    @FXML
    protected Button btnChooseLog;

    @FXML
    protected Button btnChoosePlaceAndMakeReport;

    @FXML
    public void chooseLog() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Log Files", typeLogFile));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            fullPathToLogFile.setText(f.getAbsolutePath());
        }
    }

    @FXML
    public void choosePlaceAndMakeReport() throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Report File", typeReportFile));
        File file = fc.showSaveDialog(null);

        List<String> content = new LogChecker(fullPathToLogFile()).resultReport();
        if (file != null) {
            FileHelper.saveTextToFile(content, file);
        }
    }

    public String fullPathToLogFile() {
        return fullPathToLogFile.getCharacters().toString();
    }
}