package services;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CSVLogger {
    private CSVLogger(){}

    private static java.util.logging.Formatter CSVFormatter = new Formatter() {
        @Override
        public String format(LogRecord record) {
            StringBuilder myBuilder = new StringBuilder();
            Calendar c = Calendar.getInstance();
            Date currentDate = c.getTime();
            myBuilder.append(record.getLevel()+","+currentDate.toString()+","+record.getMessage()+"\n");
            return myBuilder.toString();
        }
    };

    public static Logger getInstance ()  {
        Logger logger = Logger.getLogger("services.hotelservices");

        FileHandler mHandler = null;
        try {
            mHandler = new FileHandler("C:\\Users\\George\\Desktop\\mavenproiectpao\\Logger.csv", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mHandler.setFormatter(CSVFormatter);
        logger.addHandler(mHandler);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        return logger;
    }



}
