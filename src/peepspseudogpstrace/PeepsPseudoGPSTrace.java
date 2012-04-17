package peepspseudogpstrace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author al
 */
public class PeepsPseudoGPSTrace {

    List<PersonPseudoGPSTrace> thePeeps = new ArrayList<PersonPseudoGPSTrace>();
    Logger theLogger = null;

    PeepsPseudoGPSTrace(String dataFileName) {
        theLogger = makeLogger();

        try {
            List<String[]> theData = CSVFile.getFileData(dataFileName, "\\|");
            for (String[] theLine : theData) {
                PersonPseudoGPSTrace theTrace = new PersonPseudoGPSTrace(theLine);
                thePeeps.add(theTrace);
            }
        } catch (IOException ex) {
            Logger.getLogger(PeepsPseudoGPSTrace.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void outputTimelineKML(String outputFileNamePrefix) {
        String targetPath = outputFileNamePrefix + "_timeline.kml";
        FileOutputStream fso = null;

        try {
            fso = new FileOutputStream(new File(targetPath));

            PrintStream ps = new PrintStream(fso);

            ps.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            ps.println();
            ps.print("<kml xmlns=\"http://earth.google.com/kml/2.1\">");
            ps.println();
            ps.print("<Document>");
            ps.println();

            for (PersonPseudoGPSTrace theTrace : thePeeps) {
                theTrace.outputAsTimelineKML(ps);
            }
            ps.print("</Document>");
            ps.println();
            ps.print("</kml>");
        } catch (Exception e) {
            theLogger.log(Level.SEVERE, "Error: ", e);
        } finally {
            try {
                if (fso != null) {
                    fso.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public void outputMapKML(String outputFileNamePrefix) {
        String targetPath = outputFileNamePrefix + "_map.kml";
        FileOutputStream fso = null;

        try {
            fso = new FileOutputStream(new File(targetPath));

            PrintStream ps = new PrintStream(fso);

            ps.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            ps.println();
            ps.print("<kml xmlns=\"http://earth.google.com/kml/2.1\">");
            ps.println();
            ps.print("<Document>");
            ps.println();

            for (PersonPseudoGPSTrace theTrace : thePeeps) {
                theTrace.outputAsMapKML(ps);
            }
            ps.print("</Document>");
            ps.println();
            ps.print("</kml>");
        } catch (Exception e) {
            theLogger.log(Level.SEVERE, "Error: ", e);
        } finally {
            try {
                if (fso != null) {
                    fso.close();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     *
     * @return - valid logger (single file).
     */
    private static Logger makeLogger() {
        Logger lgr = Logger.getLogger("PeepsPseudoGPSTrace");
        lgr.setUseParentHandlers(false);
        lgr.addHandler(simpleFileHandler());
        return lgr;
    }

    /**
     *
     * @return - valid file handler for logger.
     */
    private static FileHandler simpleFileHandler() {
        try {
            FileHandler hdlr = new FileHandler("PeepsPseudoGPSTrace.log");
            hdlr.setFormatter(new SimpleFormatter());
            return hdlr;
        } catch (Exception e) {
            System.out.println("Failed to create log file");
            return null;
        }
    }
        
    int noOfPeeps() {
        return thePeeps.size();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties properties = new Properties();
        FileInputStream is = null;

        try {
            is = new FileInputStream("GPSTrace.properties");
            properties.load(is);
        } catch (IOException e) {
            // ...
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    /* .... */
                }
            }
        }

        String dataFileName = properties.getProperty("dataFileName", "rubens.psv");
        String outputFileNamePrefix = properties.getProperty("outputFileNamePrefix", "rubens");

        PeepsPseudoGPSTrace theMain = new PeepsPseudoGPSTrace(dataFileName);
        theMain.outputTimelineKML(outputFileNamePrefix);
        theMain.outputMapKML(outputFileNamePrefix);
    }
}
