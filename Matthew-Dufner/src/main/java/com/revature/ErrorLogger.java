package com.revature;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class ErrorLogger {
    private static List<ErrorLogger> loggerList;
    private int threshold; //0=debug, 1=info, 2 =warning, 3=error/sever, 4=fatal
    private int target; //0=console, 1=file, 2=datasource
    private String filePath;

    public ErrorLogger(int threshold, int target){
        this.threshold = threshold;
        this.target = target;
        loggerList = new LinkedList<>();
    }

    public void log(int level, String message, String content) throws SQLException {

        if (level < threshold) {
            return;
        }

        switch (target) {
            case 0:

                break;
            case 1:

                break;
            case 2:
                LocalDateTime timestamp = LocalDateTime.now();
                String formattedDateTime = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
                break;
        }
    }

    private void consoleLog(int level, String message, String content, String timestamp){

    }

    private void fileLog(int level, String message, String content, String timestamp){

    }

    private void dataSourceLog(int level, String message, String content, String timestamp) throws SQLException {
        String sql = "INSERT INTO logs (message, content, level, timestamp) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = DataSourceManager.getConnection().prepareStatement(sql);
        pstmt.setString(1, message);
        pstmt.setString(2, content);
        pstmt.setInt(3, level);
        pstmt.setString(4, timestamp);
        pstmt.executeUpdate();
    }
}
