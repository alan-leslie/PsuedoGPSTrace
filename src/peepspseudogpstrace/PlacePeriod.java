
package peepspseudogpstrace;

/**
 *
 * @author al
 */
public class PlacePeriod {
    private Position thePosition;
    private String thePlaceName;
    private Period thePeriod;

    public PlacePeriod(Position thePosition, String thePlaceName, Period thePeriod) {
        this.thePosition = thePosition;
        this.thePlaceName = thePlaceName;
        this.thePeriod = thePeriod;
    }

    public Period getThePeriod() {
        return thePeriod;
    }

    public String getThePlaceName() {
        return thePlaceName;
    }

    public Position getThePosition() {
        return thePosition;
    }
}
