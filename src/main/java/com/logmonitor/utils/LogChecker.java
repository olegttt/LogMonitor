package com.logmonitor.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LogChecker {

    public List<String> report = new ArrayList<>();
    private final String[] userIds = new String[] {"[123]", "[256]", "[190]"};
    private HashMap<String, Long> countErrorsByUser = new HashMap<>();
    private final String text;

    public LogChecker(String pathToLogFile) throws IOException {
        File file = new File(pathToLogFile);
        text = new String(Files.readAllBytes(file.toPath()), "Windows-1251");
    }

    private List<String> strings() {
        return List.of(text.split("\\r\\n\\r\\n"));
    }

    private List<String> errors() {
        return strings().stream().filter(str -> str.contains("] ERROR: ")).collect(Collectors.toList());
    }

    private int countAllErrors() {
        return errors().size();
    }

    private HashMap<String, Long> countErrorsByUser() {
        for (int i = 0; i < userIds.length; i++) {
            int finalI = i;
            Long count = errors().stream().filter(str -> str.contains(userIds[finalI])).count();
            countErrorsByUser.put(userIds[i], count);
        }
        return countErrorsByUser;
    }

    public List<String> resultReport() {
        report.add("All errors count: " + countAllErrors());
        for (String userId : userIds) {
            report.add("Count errors of User ID " + userId + " = " + countErrorsByUser().get(userId));
        }
        report.add("");
        report.add("Errors Log:");
        report.addAll(errors());

        return report;
    }
}