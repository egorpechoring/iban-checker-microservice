package com.seb.ibanchecker.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationLogger {
    private static final Logger logger = Logger.getLogger(ApplicationLogger.class.getName());

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }

    public static void warning(String message) {
        logger.warning(message);
    }
}
