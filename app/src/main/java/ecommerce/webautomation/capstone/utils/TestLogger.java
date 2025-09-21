package ecommerce.webautomation.capstone.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLogger {
    private static final Logger logger = LogManager.getLogger(new TestLogger().getClass());

    // Private constructor to prevent instantiation
    private TestLogger() {
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    // Add more log methods as needed
    public static void shutdown() {
        LogManager.shutdown();
    }

}