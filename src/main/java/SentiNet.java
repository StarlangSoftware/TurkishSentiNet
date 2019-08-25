import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SentiNet {
    private HashMap<String, SentiSynSet> synSetList;

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
        synSetList = new HashMap<>();
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
                synSetList.put(id, new SentiSynSet(id, positiveScore, negativeScore));
            }
            sentiSynSetNode = sentiSynSetNode.getNextSibling();
            id = "";
            positiveScore = 0.0;
            negativeScore = 0.0;
        }
    }

    public SentiSynSet getSentiSynSet(String id){
        return synSetList.get(id);
    }

    private ArrayList<String> getPolarity(PolarityType polarityType){
        ArrayList<String> result = new ArrayList<>();
        for (SentiSynSet sentiSynSet : synSetList.values()){
            if (sentiSynSet.getPolarity().equals(polarityType)){
                result.add(sentiSynSet.getId());
            }
        }
        return result;
    }

    public ArrayList<String> getPositives(){
        return getPolarity(PolarityType.POSITIVE);
    }

    public ArrayList<String> getNegatives(){
        return getPolarity(PolarityType.NEGATIVE);
    }

    public ArrayList<String> getNeutrals(){
        return getPolarity(PolarityType.NEUTRAL);
    }

}
