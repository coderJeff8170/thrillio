package com.cognizant.thrillio.constants;

public class EnvironmentVariables {
    public static final String HOST = System.getenv("HOST");;
    public static final String PASSWORD = System.getenv("PASSWORD");
    public static final String USER = System.getenv("USER");
    public static final String DATABASE = System.getenv("DATABASE");
    public static final String PORT = System.getenv("PORT");
    public static final String THRILLIO_CONNECTION_STRING = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?useSSL=false";
}
