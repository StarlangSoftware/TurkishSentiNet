package SentiNet;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SentiSynSetTest {
    SentiNet sentiNet = new SentiNet();

    @Test
    public void saveAsXml() {
        SentiSynSet sentiSynSet;
        String line;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("test.xml"), StandardCharsets.UTF_8);
            BufferedWriter outFile = new BufferedWriter(writer);
            sentiSynSet = sentiNet.getSentiSynSet("TUR10-1093230");
            sentiSynSet.saveAsXml(outFile);
            sentiSynSet = sentiNet.getSentiSynSet("TUR10-0730690");
            sentiSynSet.saveAsXml(outFile);
            sentiSynSet = sentiNet.getSentiSynSet("TUR10-0969360");
            sentiSynSet.saveAsXml(outFile);
            outFile.close();
            Scanner input = new Scanner(new File("test.xml"));
            line = input.nextLine();
            assertEquals("<SYNSET><ID>TUR10-1093230</ID><PSCORE>0.25</PSCORE><NSCORE>0.0</NSCORE></SYNSET>", line);
            line = input.nextLine();
            assertEquals("<SYNSET><ID>TUR10-0730690</ID><PSCORE>0.0</PSCORE><NSCORE>0.0</NSCORE></SYNSET>", line);
            line = input.nextLine();
            assertEquals("<SYNSET><ID>TUR10-0969360</ID><PSCORE>0.0</PSCORE><NSCORE>1.0</NSCORE></SYNSET>", line);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}