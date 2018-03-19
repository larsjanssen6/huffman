package Tests;

import JCF.calc.Huffman;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HuffmanTest {

    @Test
    public void create_huffman() {
        ExecutorService pool = Executors.newFixedThreadPool(1);

        Huffman task = new Huffman("lars","deze.ser");
        Future<String> futureHuffman = pool.submit(task);
        String output = null;

        try {
            output = futureHuffman.get();
            Assert.assertEquals("\n" + "SYMBOL\tWEIGHT\tHUFFMAN CODE\n" + "a\t1\t00\n" + "s\t1\t01\n" + "r\t1\t10\n" + "l\t1\t11\n" + "Encoded string\n" + "11001001", output);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}