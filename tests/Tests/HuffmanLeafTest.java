package Tests;

import JCF.calc.HuffmanLeaf;
import org.junit.Assert;
import org.junit.Test;

public class HuffmanLeafTest {

    @Test
    public void create_leaf_correctly() {
        HuffmanLeaf huffmanLeaf = new HuffmanLeaf(1, 'l');

        Assert.assertEquals(huffmanLeaf.value, 'l');
        Assert.assertEquals(huffmanLeaf.frequency, 1);
    }
}