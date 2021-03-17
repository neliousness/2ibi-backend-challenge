package com.neliolucas._2ibi.countryapi.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Helper {

    public static String log(Object o, String message) {

        Logger logger = Logger.getLogger(o.getClass().getName());
        logger.log(Level.INFO, message);
        return message;
    }
}
