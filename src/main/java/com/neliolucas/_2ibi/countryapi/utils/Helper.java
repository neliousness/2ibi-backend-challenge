package com.neliolucas._2ibi.countryapi.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nelio
 * @date 20/03/2021
 */

public class Helper {

    /**
     * statically invoked logger
     * @param o class name
     * @param message log message
     * @return
     */
    public static void logInfo(Object o, String message)
    {
        Logger logger = Logger.getLogger(o.getClass().getName());
        logger.info(message);
    }
}
