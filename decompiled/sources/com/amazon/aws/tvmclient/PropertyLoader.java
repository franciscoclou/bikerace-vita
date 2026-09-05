package com.amazon.aws.tvmclient;

import android.util.Log;
import java.util.Properties;

/* JADX INFO: loaded from: /home/francisco/bike_race/apk/classes.dex */
public class PropertyLoader {
    private static PropertyLoader instance = null;
    private String gameSessionsTableName;
    private boolean hasCredentials;
    private String tokenVendingMachineURL;
    private boolean useSSL;
    private String usersTableName;

    public static PropertyLoader getInstance() {
        if (instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }

    private PropertyLoader() {
        this.hasCredentials = false;
        this.tokenVendingMachineURL = null;
        this.useSSL = false;
        this.usersTableName = null;
        this.gameSessionsTableName = null;
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream(AmazonConfig.getAwsCredentialsPropertiesResource()));
            this.tokenVendingMachineURL = properties.getProperty("tokenVendingMachineURL");
            this.useSSL = Boolean.parseBoolean(properties.getProperty("useSSL"));
            this.usersTableName = properties.getProperty("usersTableName");
            this.gameSessionsTableName = properties.getProperty("gameSessionsTableName");
            if (this.tokenVendingMachineURL == null || this.tokenVendingMachineURL.equals("") || this.tokenVendingMachineURL.equals("CHANGEME") || this.usersTableName.equals("") || this.gameSessionsTableName.equals("")) {
                this.tokenVendingMachineURL = null;
                this.useSSL = false;
                this.hasCredentials = false;
                this.usersTableName = null;
                this.gameSessionsTableName = null;
            } else {
                this.hasCredentials = true;
            }
        } catch (Exception e) {
            Log.e("PropertyLoader", "Unable to read property file.");
        }
    }

    public boolean hasCredentials() {
        return this.hasCredentials;
    }

    public String getTokenVendingMachineURL() {
        return this.tokenVendingMachineURL;
    }

    public boolean useSSL() {
        return this.useSSL;
    }

    public String getUsersTableName() {
        return this.usersTableName;
    }

    public String getGameSessionsTableName() {
        return this.gameSessionsTableName;
    }
}
