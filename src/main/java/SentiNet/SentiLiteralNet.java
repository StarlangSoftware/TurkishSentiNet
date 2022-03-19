package SentiNet;

import Xml.XmlDocument;
import Xml.XmlElement;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SentiLiteralNet {

    private HashMap<String, SentiLiteral> sentiLiteralList;

    private void loadSentiNet(XmlDocument xmlDocument){
        String name = "";
        xmlDocument.parse();
        double positiveScore = 0.0, negativeScore = 0.0;
        XmlElement rootNode = xmlDocument.getFirstChild();
        XmlElement sentiLiteralNode = rootNode.getFirstChild();
        sentiLiteralList = new HashMap<>();
        while (sentiLiteralNode != null){
            XmlElement partNode = sentiLiteralNode.getFirstChild();
            while (partNode != null){
                switch (partNode.getName()){
                    case "NAME":
                        name = partNode.getPcData();
                        break;
                    case "PSCORE":
                        positiveScore = Double.parseDouble(partNode.getPcData());
                        break;
                    case "NSCORE":
                        negativeScore = Double.parseDouble(partNode.getPcData());
                        break;

                }
                partNode = partNode.getNextSibling();
            }
            if (!name.isEmpty()){
                sentiLiteralList.put(name, new SentiLiteral(name, positiveScore, negativeScore));
            }
            sentiLiteralNode = sentiLiteralNode.getNextSibling();
            name = "";
            positiveScore = 0.0;
            negativeScore = 0.0;
        }
    }

    /**
     * Constructor of Turkish SentiNet.SentiNet. Reads the turkish_sentiliteralnet.xml file from the resources directory. For each
     * sentiLiteral read, it adds it to the sentiLiteralList.
     */
    public SentiLiteralNet(){
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("turkish_sentiliteralnet.xml");
        loadSentiNet(new XmlDocument(inputStream));
    }

    public SentiLiteralNet(String fileName){
        loadSentiNet(new XmlDocument(fileName));
    }

    /**
     * Accessor for a single SentiLiteral.
     * @param name Name of the searched SentiLiteral.
     * @return SentiLiteral with the given id.
     */
    public SentiLiteral getSentiLiteral(String name){
        return sentiLiteralList.get(name);
    }

    /**
     * Constructs and returns an {@link ArrayList} of ids, which are the lemmas of the {@link SentiLiteral}s having polarity
     * polarityType.
     * @param polarityType PolarityTypes of the searched {@link SentiLiteral}s
     * @return An {@link ArrayList} of lemma having polarityType polarityType.
     */
    private ArrayList<String> getPolarity(PolarityType polarityType){
        ArrayList<String> result = new ArrayList<>();
        for (SentiLiteral sentiLiteral : sentiLiteralList.values()){
            if (sentiLiteral.getPolarity().equals(polarityType)){
                result.add(sentiLiteral.getName());
            }
        }
        return result;
    }

    /**
     * Returns the lemmas of all positive {@link SentiLiteral}s.
     * @return An ArrayList of lemmas of all positive {@link SentiLiteral}s.
     */
    public ArrayList<String> getPositives(){
        return getPolarity(PolarityType.POSITIVE);
    }

    /**
     * Returns the lemmas of all negative {@link SentiLiteral}s.
     * @return An ArrayList of lemmas of all negative {@link SentiLiteral}s.
     */
    public ArrayList<String> getNegatives(){
        return getPolarity(PolarityType.NEGATIVE);
    }

    /**
     * Returns the lemmas of all neutral {@link SentiLiteral}s.
     * @return An ArrayList of lemmas of all neutral {@link SentiLiteral}s.
     */
    public ArrayList<String> getNeutrals(){
        return getPolarity(PolarityType.NEUTRAL);
    }

}
