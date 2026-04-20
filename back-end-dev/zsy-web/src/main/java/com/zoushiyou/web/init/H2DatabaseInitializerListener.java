package com.zoushiyou.web.init;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Collectors;

public class H2DatabaseInitializerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            WebApplicationContext springContext = WebApplicationContextUtils
                .getWebApplicationContext(sce.getServletContext());
            
            if (springContext != null) {
                DataSource dataSource = springContext.getBean(DataSource.class);
                
                if (dataSource != null) {
                    initDatabase(dataSource);
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize H2 database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initDatabase(DataSource dataSource) {
        try (InputStream is = getClass().getClassLoader()
                .getResourceAsStream("initDB_h2.sql")) {
            if (is != null) {
                String sqlScript = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));

                try (Connection conn = dataSource.getConnection();
                     Statement stmt = conn.createStatement()) {
                    for (String sql : sqlScript.split(";")) {
                        String trimmedSql = sql.trim();
                        if (!trimmedSql.isEmpty() && !trimmedSql.startsWith("--")) {
                            try {
                                stmt.execute(trimmedSql);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                System.out.println("H2 Database initialized successfully!");
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize H2 database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
