package Tests;

import JCF.calc.Huffman;
import JCF.calc.HuffmanDecode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DecodeCorrectly {

    @Before
    public void setup() throws IOException {

    }

    @Test
    public void decode_correctly() throws IOException, ClassNotFoundException {
        File file = new File("deze.ser");
        FileInputStream stream = new FileInputStream(file);

        HuffmanDecode decode = new HuffmanDecode(stream, "01111110110101100110111100101001000");
        Assert.assertEquals("lars janssen", decode.decode());
    }
}