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
            
            List<String[]> theTransitions = rubens.getTransitions();
            
            assert(theTransitions.size() == 5);
            String[] theTransition = theTransitions.get(4);
            
            assert(theTransition[0].equalsIgnoreCase("Thu Jan 01 00:00:00 GMT 1609"));
            assert(theTransition[1].equalsIgnoreCase("10.796249,45.160526"));
            assert(theTransition[2].equalsIgnoreCase("4.40914,51.217153"));

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
    
    @Test
    public void testVelasquez() {
        try {
            String dataFileName = "velasquez.psv";
            String outputFileNamePrefix = "velasquez";
            List<String[]> theData = CSVFile.getFileData(dataFileName, "\\|");

            assert (theData.size() == 1);

            PersonPseudoGPSTrace velasquez = new PersonPseudoGPSTrace(theData.get(0));
            String theName = velasquez.getTheName();
            URL theURL = velasquez.getTheURL();
            List<PlacePeriod> theLifetime = velasquez.getTheLifetime();

//            assert (theName.equalsIgnoreCase("Peter Paul Rubens"));
//            assert (theURL.toString().contains("Peter_Paul_Rubens"));
            assert (theLifetime.size() == 2);
            
            List<String[]> theTransitions = velasquez.getTransitions();
            
            assert(theTransitions.size() == 1);
            String[] theTransition = theTransitions.get(0);
            
            assert(theTransition[0].equalsIgnoreCase("Wed Jun 01 00:00:00 GMT 1622"));
            assert(theTransition[1].equalsIgnoreCase("-5.986944,37.377222"));
            assert(theTransition[2].equalsIgnoreCase("-3.683333,40.4"));
                        
            PlacePeriod firstPeriod = theLifetime.get(0);
            PlacePeriod lastPeriod = theLifetime.get(theLifetime.size() - 1);
            
            String firstPeriodString = firstPeriod.getThePeriod().asLongString();
            String lastPeriodString = lastPeriod.getThePeriod().asLongString();
            
            assert(firstPeriodString.equalsIgnoreCase("06 June 1599-01 June 1622"));
            assert(lastPeriodString.equalsIgnoreCase("01 June 1622-06 August 1660"));
            
            assert(velasquez.getDOB().equalsIgnoreCase("06/06/1599"));
            assert(velasquez.getDOD().equalsIgnoreCase("06/08/1660")); 
            
            assert(firstPeriod.getThePlaceName().equalsIgnoreCase("seville, spain"));
            assert(lastPeriod.getThePlaceName().equalsIgnoreCase("madrid, spain"));
            
            assert(firstPeriod.getThePosition().getLatitude().equalsIgnoreCase("37.377222"));
            assert(firstPeriod.getThePosition().getLongitude().equalsIgnoreCase("-5.986944"));
            assert(lastPeriod.getThePosition().getLatitude().equalsIgnoreCase("40.4"));
            assert(lastPeriod.getThePosition().getLongitude().equalsIgnoreCase("-3.683333"));
        } catch (IOException ex) {
            Logger.getLogger(PersonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Test
    public void testRibalta() {
        try {
            String dataFileName = "ribalta.psv";
            String outputFileNamePrefix = "ribalta";
            List<String[]> theData = CSVFile.getFileData(dataFileName, "\\|");

            assert (theData.size() == 1);

            PersonPseudoGPSTrace ribalta = new PersonPseudoGPSTrace(theData.get(0));
            String theName = ribalta.getTheName();
            URL theURL = ribalta.getTheURL();
            List<PlacePeriod> theLifetime = ribalta.getTheLifetime();

//            assert (theName.equalsIgnoreCase("Peter Paul Rubens"));
//            assert (theURL.toString().contains("Peter_Paul_Rubens"));
            assert (theLifetime.size() == 1);
            
            List<String[]> theTransitions = ribalta.getTransitions();
            
            assert(theTransitions.isEmpty());
                        
            PlacePeriod firstPeriod = theLifetime.get(0);
            
            String firstPeriodString = firstPeriod.getThePeriod().asLongString();
            
            assert(firstPeriodString.equalsIgnoreCase("02 June 1565-14 January 1628"));
            
            assert(ribalta.getDOB().equalsIgnoreCase("02/06/1565"));
            assert(ribalta.getDOD().equalsIgnoreCase("14/01/1628"));             
        } catch (IOException ex) {
            Logger.getLogger(PersonTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
