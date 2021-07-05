package SentiNet;

public class SentiLiteral {

    private String name;
    private double positiveScore;
    private double negativeScore;

    /**
     * Constructor of SentiLiteral. Gets input literal, positiveScore, negativeScore and sets all corresponding attributes.
     * @param name Name of the literal.
     * @param positiveScore Positive score of the literal.
     * @param negativeScore Negative score of the literal.
     */
    public SentiLiteral(String name, double positiveScore, double negativeScore) {
        this.name = name;
        this.positiveScore = positiveScore;
        this.negativeScore = negativeScore;
    }

    /**
     * Accessor for the positiveScore attribute.
     * @return PositiveScore of the SentiLiteral.
     */
    public double getPositiveScore() {
        return positiveScore;
    }

    /**
     * Accessor for the negativeScore attribute.
     * @return NegativeScore of the SentiLiteral.
     */
    public double getNegativeScore() {
        return negativeScore;
    }

    /**
     * Returns the polarityType of the literal. If the positive score is larger than the negative score, the polarity is
     * positive; if the negative score is larger than the positive score, the polarity is negative; if both positive
     * score and negative score are equal, the polarity is neutral.
     * @return SentiNet.PolarityType of the literal.
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
     * Accessor for the name attribute.
     * @return Name of the SentiLiteral.
     */
    public String getName(){
        return name;
    }

}
