
package peepspseudogpstrace;

import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author al
 */
public class PersonPseudoGPSTrace {

    String theName;
    private String theDOB;
    private String theDOD;
    private Position theLifePlace;
    private Period theLifePeriod;
    private URL theURL;
    private String theHREF;
    private List<PlacePeriod> theLifetime = new ArrayList<PlacePeriod>();

    public String getDOB() {
        return theDOB;
    }

    public String getTheHREF() {
        return theHREF;
    }

    public List<PlacePeriod> getTheLifetime() {
        return theLifetime;
    }

    public String getTheName() {
        return theName;
    }

    public Period getLifePeriod() {
        return theLifePeriod;
    }

    public Position getLifePlace() {
        return theLifePlace;
    }

    public String getDOD() {
        return theDOD;
    }

    public URL getTheURL() {
        return theURL;
    }

    PersonPseudoGPSTrace(String[] theData, boolean isKing) throws IncorrectPeriodException {
        theName = theData[0];
        try {
            theURL = new URL(theData[1]);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PersonPseudoGPSTrace.class.getName()).log(Level.SEVERE, null, ex);
        }

        int noOfTransitions = (theData.length - 3) / 3; // drop out the name, url and DOD

        for (int i = 0; i < noOfTransitions; ++i) {
            int startIndex = (i * 3) + 2;
            String startDateString = theData[startIndex];
            String locationName = theData[startIndex + 1];
            String coords = theData[startIndex + 2];
            String endDateString = theData[startIndex + 3];
            
            Date theStartDate = Period.getDate(startDateString);
            Date theEndDate = Period.getDate(endDateString);
            
            if(theStartDate.after(theEndDate)){
                throw new IncorrectPeriodException(startDateString, endDateString);          
            }
            
            String periodString = startDateString + " to " + endDateString;
            String[] splitCoords = coords.split(",");

            Period theLifetimePeriod = Period.getRealPeriod(periodString);
            Position theLifetimePosition = new Position(splitCoords[0].trim(), splitCoords[1].trim());

            if (i == 0) {
                theLifePlace = theLifetimePosition;
            }

            PlacePeriod theLifetimePlace = new PlacePeriod(theLifetimePosition, locationName, theLifetimePeriod);

            theLifetime.add(theLifetimePlace);
        }

        theDOB = theData[2];
        theDOD = theData[theData.length - 1];

        theLifePeriod = Period.getRealPeriod(theDOB + " to " + theDOD);
    }

    /**
     * Output the placemark data in different xml formats.
     * @param ps - the stream to where the data is written
     */
    public void outputAsTimelineKML(PrintStream ps) {
        ps.print("<Placemark>");
        ps.println();
        ps.print("<name>");
        ps.println();
        ps.print(theName);
        ps.println();
        ps.print("</name>");
        ps.println();
        ps.print("<description>");
        ps.println();

        if (theURL != null) {
            ps.print("&lt;p&gt;");
            ps.println();
            ps.print("&lt;a href=\"");
            ps.print(theURL);
            ps.print("\"&gt; more info&gt;&gt;&gt;");
            ps.print("&lt;/a&gt;");
            ps.println();
            ps.print("&lt;/p&gt;");
            ps.println();
        }

        ps.print("</description>");
        ps.println();

        if (theLifePeriod.hasDuration()) {
            ps.print("<TimeSpan>");
            ps.print("<begin>");
            ps.print(theLifePeriod.getStartDate().toString());
            ps.print("</begin>");
            ps.print("<end>");
            ps.print(theLifePeriod.getEndDate().toString());
            ps.print("</end>");
            ps.print("</TimeSpan>");
            ps.println();
        } else {
            ps.print("<TimeStamp>");
            ps.print("<when>");
            ps.print(theLifePeriod.getStartDate().toString());
            ps.print("</when>");
            ps.print("</TimeStamp>");
            ps.println();
        }

        ps.print("<Point>");
        ps.println();
        ps.print("<coordinates>");
        ps.print(theLifePlace.getLongitude());
        ps.print(",");
        ps.print(theLifePlace.getLatitude());
        ps.print("</coordinates>");
        ps.println();
        ps.print("</Point>");
        ps.println();

        ps.print("<ExtendedData>");
        ps.println("<Data name=\"DateString\">");
        ps.print("<value>");
        ps.print(theLifePeriod.asLongString());
        ps.print("</value>");
        ps.println();
        ps.println("</Data>");
        ps.println("<Data name=\"Url\">");
        ps.print("<value>");
        ps.print(theURL);
        ps.print("</value>");
        ps.println();
        ps.println("</Data>");
        ps.print("<Data name=\"theme\">");
        ps.print("<value>");
        ps.print(theName.replaceAll(" ", ""));
        ps.print("</value>");
        ps.println("</Data>");
        ps.print("</ExtendedData>");
        ps.println();

        ps.print("</Placemark>");
        ps.println();
    }

    public void outputAsMapKML(PrintStream ps) {
        outputTransitionsAsKML(ps);
        outputPlaceMarksAsKML(ps);
    }

    /**
     * Output the placemark data in different xml formats.
     * @param ps - the stream to where the data is written
     */
    public void outputPlaceMarksAsKML(PrintStream ps) {
        for (PlacePeriod thePlacePeriod : theLifetime) {
            ps.print("<Placemark>");
            ps.println();
            ps.print("<name>");
            ps.println();
            ps.print(theName);
            ps.println();
            ps.print("</name>");
            ps.println();
            ps.print("<description>");
            ps.println();

            if (theURL != null) {
                ps.print("&lt;p&gt;");
                ps.println();
                ps.print("&lt;a href=\"");
                ps.print(theURL);
                ps.print("\"&gt; more info&gt;&gt;&gt;");
                ps.print("&lt;/a&gt;");
                ps.println();
                ps.print("&lt;/p&gt;");
                ps.println();
            }

            ps.print("</description>");
            ps.println();

            Period thePeriod = thePlacePeriod.getThePeriod();

            if (thePeriod.hasDuration()) {
                ps.print("<TimeSpan>");
                ps.print("<begin>");
                ps.print(thePeriod.getStartDate().toString());
                ps.print("</begin>");
                ps.print("<end>");
                ps.print(thePeriod.getEndDate().toString());
                ps.print("</end>");
                ps.print("</TimeSpan>");
                ps.println();
            } else {
                ps.print("<TimeStamp>");
                ps.print("<when>");
                ps.print(thePeriod.getStartDate().toString());
                ps.print("</when>");
                ps.print("</TimeStamp>");
                ps.println();
            }

            Position thePosition = thePlacePeriod.getThePosition();
            ps.print("<Point>");
            ps.println();
            ps.print("<coordinates>");
            ps.print(thePosition.getLongitude());
            ps.print(",");
            ps.print(thePosition.getLatitude());
            ps.print("</coordinates>");
            ps.println();
            ps.print("</Point>");
            ps.println();

            ps.print("<ExtendedData>");
            ps.println();
            ps.println("<Data name=\"DateString\">");
            ps.print("<value>");
            ps.print(thePeriod.asLongString());
            ps.print("</value>");
            ps.println();
            ps.println("</Data>");
            ps.println("<Data name=\"Url\">");
            ps.print("<value>");
            ps.print(theURL);
            ps.print("</value>");
            ps.println();
            ps.println("</Data>");
            ps.println("<Data name=\"LocationName\">");
            ps.print("<value>");
            ps.print(thePlacePeriod.getThePlaceName());
            ps.print("</value>");
            ps.println();
            ps.println("</Data>");
            ps.print("<Data name=\"theme\">");
            ps.print("<value>");
            ps.print(theName.replaceAll(" ", ""));
            ps.print("</value>");
            ps.println("</Data>");
            ps.print("</ExtendedData>");
                        ps.println();

            ps.print("</Placemark>");
            ps.println();
        }
    }

    public List<String[]> getTransitions() {
        List<String[]> retVal = new ArrayList<String[]>();

        for (int i = 1; i < theLifetime.size(); ++i) {
            String[] theTransition = new String[3];

            PlacePeriod prev = theLifetime.get(i - 1);
            PlacePeriod curr = theLifetime.get(i);

            String thePeriodString = curr.getThePeriod().getStartDate().toString();

            Position prevPosition = prev.getThePosition();
            Position currPosition = curr.getThePosition();

            String prevPosStr = prevPosition.getLongitude() + "," + prevPosition.getLatitude();
            String currPosStr = currPosition.getLongitude() + "," + currPosition.getLatitude();

            theTransition[0] = thePeriodString;
            theTransition[1] = prevPosStr;
            theTransition[2] = currPosStr;

            retVal.add(theTransition);
        }

        return retVal;
    }

    /**
     * Output the placemark data in different xml formats.
     * @param ps - the stream to where the data is written
     */
    public void outputTransitionsAsKML(PrintStream ps) {
        List<String[]> theTransitions = getTransitions();

        for (String[] theTransition : theTransitions) {
            ps.print("<Placemark>");
            ps.println();
            ps.print("<name>");
            ps.println();
            ps.print(theName);
            ps.println();
            ps.print("</name>");
            ps.println();
            ps.print("<description>");
            ps.println();
            ps.print("</description>");
            ps.println();

            ps.print("<TimeStamp>");
            ps.print("<when>");
            ps.print(theTransition[0]);
            ps.print("</when>");
            ps.print("</TimeStamp>");
            ps.println();

            ps.print("<LineString>");
            ps.println();
            ps.print("<tessellate>1</tessellate>");
            ps.println();
            ps.print("<altitudeMode>clampToGround</altitudeMode>");
            ps.println();
            ps.print("<coordinates>");
            ps.print(theTransition[1]);
            ps.print(",0");
            ps.println();
            ps.print(theTransition[2]);
            ps.print(",0");
            ps.println();
            ps.print("</coordinates>");
            ps.println();
            ps.print("</LineString>");
            ps.println();
            ps.print("<ExtendedData>");
            ps.println();
            ps.print("<Data name=\"theme\">");
            ps.print("<value>");
            ps.print(theName.replaceAll(" ", ""));
            ps.print("</value>");
            ps.println("</Data>");
            ps.print("</ExtendedData>");
                        ps.println();
            ps.print("</Placemark>");
            ps.println();
        }
    }
}
