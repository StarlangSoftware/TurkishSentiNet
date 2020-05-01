package SentiNet;

import org.junit.Test;

import static org.junit.Assert.*;

public class SentiNetTest {
    SentiNet sentiNet = new SentiNet();

    @Test
    public void getPositives() {
        assertEquals(3100, sentiNet.getPositives().size());
    }

    @Test
    public void getNegatives() {
        assertEquals(10191, sentiNet.getNegatives().size());
    }

    @Test
    public void getNeutrals() {
        assertEquals(63534, sentiNet.getNeutrals().size());
    }
}