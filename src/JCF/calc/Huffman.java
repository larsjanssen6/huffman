package JCF.calc;

import java.util.*;
import java.util.concurrent.Callable;

public class Huffman implements Callable{
    private String text;
    private StringBuilder stringBuilder;
    private HashMap<Character, String> charHuff;
    private String fileName;
    public Huffman(String text,String fileName) {
        this.text = text;
        this.fileName = fileName;
        this.stringBuilder = new StringBuilder();
        this.charHuff = new HashMap<Character, String>();
    }

    // input is an array of frequencies, indexed by character code
    public HuffmanTree buildTree(int[] charFreqs) {
        PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        // initially, we have a forest of leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.offer(new HuffmanLeaf(charFreqs[i], (char)i));

        assert trees.size() > 0;
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();

            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
        return trees.poll();
    }

    public void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            charHuff.put(leaf.value,prefix.toString());
            // print out character, frequency, and code for this leaf (which is just the prefix)
            stringBuilder.append("\n" + leaf.value + "\t" + leaf.frequency + "\t" + prefix);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;

            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }

    }
    private String encode(HashMap<Character, String> charHuffMap, String textIn)
    {
        String encode = "";
        for (char i: textIn.toCharArray()
                ) {
            encode += charHuffMap.get(i);
        }
        return encode;
    }
    private String decode(HashMap<Character, String> charHuffMap, String encoded)
    {
        String decode = "";
        while (encoded.length() > 0)
        {
            for(Map.Entry<Character, String> entry : charHuffMap.entrySet()) {
                Character key = entry.getKey();
                String value = entry.getValue();
                int place = encoded.indexOf(value);
                if (place == 0)
                {
                    decode += key;
                    encoded = encoded.substring(value.toString().length());
                }
            }
        }
        return decode;
    }

    @Override
    public String call() throws Exception {
        int[] charFreqs = new int[256];
        // read each character and record the frequencies
        for (char c : text.toCharArray()) {
            charFreqs[c]++;
        }

        // build tree
        HuffmanTree tree = buildTree(charFreqs);

        // print out results
        stringBuilder.append("\nSYMBOL\tWEIGHT\tHUFFMAN CODE");
        printCodes(tree, new StringBuffer());
        String encode = encode(this.charHuff,this.text);
        stringBuilder.append("\nEncoded string\n" + encode);
        HuffmanEncodeSave huffmanEncodeSave = new HuffmanEncodeSave(this.charHuff,encode,this.fileName);
        huffmanEncodeSave.save();

        return stringBuilder.toString();
    }
}
