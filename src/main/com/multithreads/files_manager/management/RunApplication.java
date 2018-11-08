package com.multithreads.files_manager.management;

import org.apache.log4j.Logger;

/**
 * Main class of the application.
 */
public class RunApplication {

    /**
     * Root logger.
     */
    private final static Logger logger = Logger.getRootLogger();

    /**
     * Start point of the application.
     * @param args arguments
     */
    public static void main(String[] args) {
        logger.info("Main method started.");
        try {
            Communicator communicator = new Communicator(logger);
            communicator.openConsole();
        } catch (Throwable tr) {
            logger.error("Error");
        }
    }
}