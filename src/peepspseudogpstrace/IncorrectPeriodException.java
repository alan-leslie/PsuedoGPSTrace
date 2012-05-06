/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peepspseudogpstrace;

/**
 *
 * @author al
 */
public class IncorrectPeriodException extends Exception {
    private final String startDate;
    private final String endDate;
    
    IncorrectPeriodException(String startDate, String endDate){
        this.startDate = startDate;
        this.endDate = endDate;       
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }
    
}
