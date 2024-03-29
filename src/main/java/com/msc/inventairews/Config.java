package com.msc.inventairews;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Michael
 */
public class Config {

    private int port;
    private String jdbcURL;
    private String sqlDriver;
    private String dialect;
    private String username;
    private String password;
    
    private static Config config;

    public static Config getInstance() {
        if (config == null) {
            init();
        }
        return config;
    }

    public static void init() {
        init(new File("conf.conf"));
    }
    
    public static void init(File confFile) {
        Gson gson = new Gson();
        String conf = "";
        try {
            conf = FileUtils.readFileToString(confFile, Charset.forName("UTF-8"));
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        config = gson.fromJson(conf, Config.class);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getJdbcURL() {
        return jdbcURL;
    }

    public void setJdbcURL(String jdbcURL) {
        this.jdbcURL = jdbcURL;
    }

    public String getSqlDriver() {
        return sqlDriver;
    }

    public void setSqlDriver(String sqlDriver) {
        this.sqlDriver = sqlDriver;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
