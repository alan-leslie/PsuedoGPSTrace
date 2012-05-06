
package peepspseudogpstrace;

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
public class MainTest {
    
    public MainTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testRubens(){
        String dataFileName = "rubens.psv";
        String outputFileNamePrefix = "rubens";

        PeepsPseudoGPSTrace theMain = new PeepsPseudoGPSTrace();
        
        assert (theMain.noOfPeeps() == 1);
        
        theMain.outputTimelineKML();
        theMain.outputMapKML();}
}
