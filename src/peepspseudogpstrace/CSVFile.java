package peepspseudogpstrace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author al
 */
public class CSVFile {

    public static List<String[]> getFileData(String fileName,
            String splitChar) throws IOException {
        FileReader theReader = null;
        List<String[]> retVal = new ArrayList<String[]>();

        try {
            theReader = new FileReader(fileName);
            BufferedReader in = new BufferedReader(theReader);

            String theLine = null;

            while ((theLine = in.readLine()) != null) {
                String theLineArr[] = theLine.split(splitChar);
                retVal.add(theLineArr);
            }
        } finally {
            if (null != theReader) {
                try {
                    theReader.close();
                } catch (IOException e) {
                    /* .... */
                }
            }
        }

        return retVal;
    }

    static List<String> getFileLines(String fileName) throws IOException {
        FileReader theReader = null;
        List<String> retVal = new ArrayList<String>();

        try {
            theReader = new FileReader(fileName);
            BufferedReader in = new BufferedReader(theReader);

            String theLine = null;

            while ((theLine = in.readLine()) != null) {
                retVal.add(theLine);
            }
        } finally {
            if (null != theReader) {
                try {
                    theReader.close();
                } catch (IOException e) {
                    /* .... */
                }
            }
        }

        return retVal;
    }
}
