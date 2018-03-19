package Tests;

import JCF.calc.Huffman;
import JCF.calc.HuffmanLeaf;
import JCF.calc.HuffmanNode;
import JCF.calc.HuffmanTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.PriorityQueue;

public class BuildTreeCorrectly {

    @Before
    public void setup() throws IOException {

    }

    @Test
    public void create_huffman_node_correctly() {
        String text = "lars";

        Huffman huffman = new Huffman(text, "file");

        int[] charFreqs = new int[256];
        // read each character and record the frequencies
        for (char c : text.toCharArray()) {
            charFreqs[c]++;
        }

        Assert.assertEquals(4, huffman.buildTree(charFreqs).frequency);
    }
}