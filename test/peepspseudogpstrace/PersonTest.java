/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peepspseudogpstrace;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author al
 */
public class PersonTest {

    public PersonTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testRubens() {
        try {
            String dataFileName = "rubens.psv";
            String outputFileNamePrefix = "rubens";
            List<String[]> theData = CSVFile.getFileData(dataFileName, "\\|");

            assert (theData.size() == 1);

            PersonPseudoGPSTrace rubens = new PersonPseudoGPSTrace(theData.get(0));
            String theName = rubens.getTheName();
            URL theURL = rubens.getTheURL();
            List<PlacePeriod> theLifetime = rubens.getTheLifetime();

            assert (theName.equalsIgnoreCase("Peter Paul Rubens"));
            assert (theURL.toString().contains("Peter_Paul_Rubens"));
            assert (theLifetime.size() == 6);

            PlacePeriod firstPeriod = theLifetime.get(0);
            PlacePeriod lastPeriod = theLifetime.get(theLifetime.size() - 1);
            
            String firstPeriodString = firstPeriod.getThePeriod().asLongString();
            String lastPeriodString = lastPeriod.getThePeriod().asLongString();
            
            assert(firstPeriodString.equalsIgnoreCase("28 June 1577-01 January 1578"));
            assert(lastPeriodString.equalsIgnoreCase("01 January 1609-30 May 1640"));
            
            assert(rubens.getDOB().equalsIgnoreCase("28/06/1577"));
            assert(rubens.getDOD().equalsIgnoreCase("30/05/1640")); 
            
            assert(firstPeriod.getThePlaceName().equalsIgnoreCase("siegen, germany"));
            assert(lastPeriod.getThePlaceName().equalsIgnoreCase("antwerp, belgium"));
 
            assert(firstPeriod.getThePosition().getLatitude().equalsIgnoreCase("50.883191"));
            assert(firstPeriod.getThePosition().getLongitude().equalsIgnoreCase("8.017137"));
            assert(lastPeriod.getThePosition().getLatitude().equalsIgnoreCase("51.217153"));
            assert(lastPeriod.getThePosition().getLongitude().equalsIgnoreCase("4.40914"));
        } catch (IOException ex) {
            Logger.getLogger(PersonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
