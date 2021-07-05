package SentiNet;

import org.junit.Test;

import static org.junit.Assert.*;

public class SentiLiteralNetTest {

    SentiLiteralNet sentiNet = new SentiLiteralNet();

    @Test
    public void getPositives() {
        assertEquals(4335, sentiNet.getPositives().size());
    }

    @Test
    public void getNegatives() {
        assertEquals(13011, sentiNet.getNegatives().size());
    }

    @Test
    public void getNeutrals() {
        assertEquals(62379, sentiNet.getNeutrals().size());
    }
}