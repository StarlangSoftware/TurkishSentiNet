import java.util.ArrayList;

public class TestSentiNet {

    public static void main(String[] args){
        SentiNet sentiNet = new SentiNet();
        ArrayList<String> p = sentiNet.getPositives();
        ArrayList<String> n = sentiNet.getNegatives();
        ArrayList<String> nt = sentiNet.getNeutrals();
    }
}
