import java.io.BufferedWriter;
import java.io.IOException;

public class SentiSynSet {
    private String id;
    private double positiveScore;
    private double negativeScore;

    /**
     * Constructor of SentiSynSet. Gets input id, positiveScore, negativeScore and sets all corresponding attributes.
     * @param id Id of the SentiSynSet.
     * @param positiveScore Positive score of the SentiSynSet.
     * @param negativeScore Negative score of the SentiSynSet.
     */
    public SentiSynSet(String id, double positiveScore, double negativeScore) {
        this.id = id;
        this.positiveScore = positiveScore;
        this.negativeScore = negativeScore;
    }

    /**
     * Accessor for the positiveScore attribute.
     * @return PositiveScore of the SentiSynSet.
     */
    public double getPositiveScore() {
        return positiveScore;
    }

    /**
     * Accessor for the negativeScore attribute.
     * @return NegativeScore of the SentiSynSet.
     */
    public double getNegativeScore() {
        return negativeScore;
    }

    /**
     * Returns the polarityType of the sentiSynSet. If the positive score is larger than the negative score, the polarity is
     * positive; if the negative score is larger than the positive score, the polarity is negative; if both positive
     * score and negative score are equal, the polarity is neutral.
     * @return PolarityType of the sentiSynSet.
     */
    public PolarityType getPolarity(){
        if (positiveScore > negativeScore){
            return PolarityType.POSITIVE;
        } else {
           if (positiveScore < negativeScore){
               return PolarityType.NEGATIVE;
           } else {
               return PolarityType.NEUTRAL;
           }
        }
    }

    /**
     * Method to write SynSets to the specified file in the XML format.
     *
     * @param outfile BufferedWriter to write XML files
     */
    public void saveAsXml(BufferedWriter outfile) {
        try {
            outfile.write("<SYNSET>");
            outfile.write("<ID>" + id + "</ID>");
            outfile.write("<PSCORE>" + positiveScore + "</PSCORE>");
            outfile.write("<NSCORE>" + negativeScore + "</NSCORE>");
            outfile.write("</SYNSET>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Accessor for the id attribute.
     * @return Id of the SentiSynSet.
     */
    public String getId(){
        return id;
    }
}
