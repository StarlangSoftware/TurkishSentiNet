public class SentiSynSet {
    private String id;
    private double positiveScore;
    private double negativeScore;

    public SentiSynSet(String id, double positiveScore, double negativeScore) {
        this.id = id;
        this.positiveScore = positiveScore;
        this.negativeScore = negativeScore;
    }

    public double getPositiveScore() {
        return positiveScore;
    }

    public double getNegativeScore() {
        return negativeScore;
    }

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

    public String getId(){
        return id;
    }
}
