package org.example.util;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public class LoggerUtility {
    private static final Logger logger = LoggerFactory.getLogger(LoggerUtility.class);

    private LoggerUtility(Class<?> clazz) {

    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void trace(String message) {
        logger.trace(message);
    }
}
