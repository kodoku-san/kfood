package com.kfoodsys.entity;

/**
 *
 * @author phuho
 */
public class ConfigSys {
    private String driver;
    private String server;
    private String database;
    private String username;
    private String password;
    
    public ConfigSys() {}

    public ConfigSys(String driver, String server, String datebase, String username, String password) {
        this.driver = driver;
        this.server = server;
        this.database = datebase;
        this.username = username;
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String datebase) {
        this.database = datebase;
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

    @Override
    public String toString() {
        return driver + "|" + server + "|" + database + "|" + username + "|" + password;
    }
    
}
