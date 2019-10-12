package SentiNet;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class SentiNet {
    private HashMap<String, SentiSynSet> sentiSynSetList;

    /**
     * Constructor of Turkish SentiNet.SentiNet. Reads the turkish_sentinet.xml file from the resources directory. For each
     * sentiSynSet read, it adds it to the sentiSynSetList.
     */
    public SentiNet(){
        Node rootNode, sentiSynSetNode, partNode;
        Document doc;
        String id = "";
        double positiveScore = 0.0, negativeScore = 0.0;
        ClassLoader classLoader = getClass().getClassLoader();
        DOMParser parser = new DOMParser();
        try {
            parser.parse(new InputSource(classLoader.getResourceAsStream("turkish_sentinet.xml")));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        doc = parser.getDocument();
        rootNode = doc.getFirstChild();
        sentiSynSetNode = rootNode.getFirstChild();
        sentiSynSetList = new HashMap<>();
        while (sentiSynSetNode != null){
            partNode = sentiSynSetNode.getFirstChild();
            while (partNode != null){
                if (partNode.getNodeName().equals("ID")) {
                    id = partNode.getFirstChild().getNodeValue();
                } else {
                    if (partNode.getNodeName().equals("PSCORE")){
                        positiveScore = Double.parseDouble(partNode.getFirstChild().getNodeValue());
                    } else {
                        if (partNode.getNodeName().equals("NSCORE")){
                            negativeScore = Double.parseDouble(partNode.getFirstChild().getNodeValue());
                        }
                    }
                }
                partNode = partNode.getNextSibling();
            }
            if (!id.isEmpty()){
                sentiSynSetList.put(id, new SentiSynSet(id, positiveScore, negativeScore));
            }
            sentiSynSetNode = sentiSynSetNode.getNextSibling();
            id = "";
            positiveScore = 0.0;
            negativeScore = 0.0;
        }
    }

    /**
     * Accessor for a single SentiNet.SentiSynSet.
     * @param id Id of the searched SentiNet.SentiSynSet.
     * @return SentiNet.SentiSynSet with the given id.
     */
    public SentiSynSet getSentiSynSet(String id){
        return sentiSynSetList.get(id);
    }

    /**
     * Adds specified SentiNet.SentiSynSet to the SentiNet.SentiSynSet list.
     *
     * @param sentiSynSet SentiNet.SentiSynSet to be added
     */
    public void addSentiSynSet(SentiSynSet sentiSynSet) {
        sentiSynSetList.put(sentiSynSet.getId(), sentiSynSet);
    }

    /**
     * Removes specified SentiNet.SentiSynSet from the SentiNet.SentiSynSet list.
     *
     * @param sentiSynSet SentiNet.SentiSynSet to be removed
     */
    public void removeSynSet(SentiSynSet sentiSynSet) {
        sentiSynSetList.remove(sentiSynSet.getId());
    }

    /**
     * Constructs and returns an {@link ArrayList} of ids, which are the ids of the {@link SentiSynSet}s having polarity
     * polarityType.
     * @param polarityType PolarityTypes of the searched {@link SentiSynSet}s
     * @return An {@link ArrayList} of id having polarityType polarityType.
     */
    private ArrayList<String> getPolarity(PolarityType polarityType){
        ArrayList<String> result = new ArrayList<>();
        for (SentiSynSet sentiSynSet : sentiSynSetList.values()){
            if (sentiSynSet.getPolarity().equals(polarityType)){
                result.add(sentiSynSet.getId());
            }
        }
        return result;
    }

    /**
     * Returns the ids of all positive {@link SentiSynSet}s.
     * @return An ArrayList of ids of all positive {@link SentiSynSet}s.
     */
    public ArrayList<String> getPositives(){
        return getPolarity(PolarityType.POSITIVE);
    }

    /**
     * Returns the ids of all negative {@link SentiSynSet}s.
     * @return An ArrayList of ids of all negative {@link SentiSynSet}s.
     */
    public ArrayList<String> getNegatives(){
        return getPolarity(PolarityType.NEGATIVE);
    }

    /**
     * Returns the ids of all neutral {@link SentiSynSet}s.
     * @return An ArrayList of ids of all neutral {@link SentiSynSet}s.
     */
    public ArrayList<String> getNeutrals(){
        return getPolarity(PolarityType.NEUTRAL);
    }

    /**
     * Method to write SynSets to the specified file in the XML format.
     *
     * @param fileName file name to write XML files
     */
    public void saveAsXml(String fileName) {
        BufferedWriter outfile;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
            outfile = new BufferedWriter(writer);
            outfile.write("<SYNSETS>\n");
            for (SentiSynSet synSet : sentiSynSetList.values()) {
                synSet.saveAsXml(outfile);
            }
            outfile.write("</SYNSETS>\n");
            outfile.close();
        } catch (IOException ioException) {
            System.out.println("Output file can not be opened");
        }
    }

}
