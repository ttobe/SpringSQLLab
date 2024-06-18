package com.springsqllab.springsqllab.service;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class QueryService {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public String executeQuery(String problemId, String query) {
        // SQL 검증
        if (!isSelectQuery(query)) {
            throw new IllegalArgumentException("Only SELECT queries are allowed.");
        }

        String dbUrl = url + problemId; // 문제 ID를 URL 에 추가하는 방식

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            // print result like table
            return printResult(rs);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String printResult(ResultSet rs) throws SQLException {
        // Process ResultSet and format results
        StringBuilder result = new StringBuilder();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Append column names as the header
        for (int i = 1; i <= columnCount; i++) {
            result.append(metaData.getColumnName(i)).append("\t");
        }
        result.append("\n");

        // Append rows
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                result.append(rs.getString(i)).append("\t");
            }
            result.append("\n");
        }

        return result.toString();
    }

    private boolean isSelectQuery(String query) {
        try {
            Select select = (Select) CCJSqlParserUtil.parse(query);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}