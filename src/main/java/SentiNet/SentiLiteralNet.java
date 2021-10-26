package SentiNet;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SentiLiteralNet {

    private HashMap<String, SentiLiteral> sentiLiteralList;

    private void loadSentiNet(InputSource inputSource){
        Node rootNode, sentiLiteralNode, partNode;
        Document doc = null;
        String name = "";
        double positiveScore = 0.0, negativeScore = 0.0;
        DocumentBuilder builder;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(inputSource);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        rootNode = doc.getFirstChild();
        sentiLiteralNode = rootNode.getFirstChild();
        sentiLiteralList = new HashMap<>();
        while (sentiLiteralNode != null){
            partNode = sentiLiteralNode.getFirstChild();
            while (partNode != null){
                switch (partNode.getNodeName()){
                    case "NAME":
                        name = partNode.getFirstChild().getNodeValue();
                        break;
                    case "PSCORE":
                        positiveScore = Double.parseDouble(partNode.getFirstChild().getNodeValue());
                        break;
                    case "NSCORE":
                        negativeScore = Double.parseDouble(partNode.getFirstChild().getNodeValue());
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
        InputSource inputSource = new InputSource(classLoader.getResourceAsStream("turkish_sentiliteralnet.xml"));
        loadSentiNet(inputSource);
    }

    public SentiLiteralNet(String fileName){
        loadSentiNet(new InputSource(fileName));
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
