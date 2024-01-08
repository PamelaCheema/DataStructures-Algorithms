import jdk.nashorn.api.tree.BinaryTree;

import java.io.*;
import java.util.*;

public class BT {


    private Map<String, Integer> characters = new HashMap<String, Integer>();

    private PriorityQueue<BitTreeNode> charQueue = new PriorityQueue<BitTreeNode>();

    private Map<String, String> codeMap = new HashMap<>();



    String fileName;
    String fExt;
    File f;
    FileReader fr;
    BufferedReader br;

    public BT(String fileName, String fExt)
    {
        this.fileName = fileName;
        this.fExt = fExt;

        encode(fileName, fExt);
        compress();

    }

    public void encode(String fileName, String fExt)
    {
        addCharacters(fileName, fExt);

        priorityChars();

        System.out.println("Priority Queue: " + printQueue());

        sortQueue();

        System.out.println("Priority Queue: " + printQueue());

        setCodeMap(charQueue.peek(), "");

        codeMap.remove(null);

        System.out.println(codeMap);

    }

    public void compress()
    {
        //Writer writer = null;
        try {

            PrintWriter writer = new PrintWriter(fileName + ".bwt.bits.txt", "UTF-8");
            fileName = "./input/" + fileName + fExt;

            f = new File(fileName);

            fr = new FileReader(f);

            br = new BufferedReader(fr);

            String current;// = br.readLine();

            String[] temp;//= line.split("");

            while(br.ready())
            {
                current = br.readLine();

                temp = current.split("");

                for(int i = 0; i < temp.length; i++)
                {
                    if(checkSpecialChar(temp[i]) != null)
                    {
                        current = checkSpecialChar(temp[i]);
                    }else{
                        current = temp[i];
                    }
                    writer.print(codeMap.get(current));
                }
                writer.println();
            }

            writer.println(codeMap.get("EOF"));
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decompress()
    {
        try {
            f = new File(fileName);

            fr = new FileReader(f);

            br = new BufferedReader(fr);

            String current;//= br.readLine();

            String[] temp;

            while(br.ready())
            {
                current = br.readLine();

                temp = current.split("");

                traverse(current, charQueue.peek());
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void traverse(String current, BitTreeNode node)
    {
        if(node.left == null && node.right == null)
        {
            //System.out.print(node.chr);
            //traverse(current, charQueue.peek());
            return;
        }

        if(current.charAt(0) == '0')
        {
            traverse(current.substring(1), node.left);
        }

        if(current.charAt(0) == '1')
        {
            traverse(current.substring(1), node.right);
        }
    }

    /**
     * adds all the characters from the text file into a Tree Map
     * @param fileName
     * @param fExt
     */
    private void addCharacters(String fileName, String fExt)
    {
        try{
            fileName = "./input/" + fileName + fExt;

            f = new File(fileName);

            fr = new FileReader(f);

            br = new BufferedReader(fr);

            String current;

            String[] temp;

            //Parses through file and adds characters to tree map
            while(br.ready())
            {
                current = br.readLine();

                temp = current.split("");

                for(int i = 0; i < temp.length; i++)
                {

                    if(checkSpecialChar(temp[i]) != null)
                    {

                        temp[i] = checkSpecialChar(temp[i]);

                    }
                    if(characters.containsKey(temp[i]))
                    {

                        characters.replace(temp[i], characters.get(temp[i]) + 1);

                    }else {

                        characters.put(temp[i], 1);

                    }
                }
                characters.put("EOF", 1);
            }

        }catch(Exception e){

            e.printStackTrace();

        }
    }

    /**
     * puts all the elements from the map into a priority queue
     */
    private void priorityChars()
    {
        Iterator<String> list = characters.keySet().iterator();

        String currentKey;

        BitTreeNode[] tempNodeArray = new BitTreeNode[characters.size()];

        int tempIndex = 0;

        while(list.hasNext())
        {

            currentKey = list.next();

            BitTreeNode temp = new BitTreeNode(characters.get(currentKey), currentKey);

            tempNodeArray[tempIndex] =  temp;

            tempIndex++;

        }

        tempNodeArray = Sort.bubble(tempNodeArray);

        for(int i = 0; i < tempNodeArray.length; i++)
        {
            charQueue.add(tempNodeArray[i]);
        }

    }

    private void setCodeMap(BitTreeNode node, String total)
    {

        if(node.left == null && node.right == null)
        {
            return;
        }else{



            if(node.left.freq > 0)
            {
                codeMap.put(node.left.chr, total += "0");
            }

            setCodeMap(node.left, total);

            total = total.substring(0, total.length() - 1);

            if(node.right.freq > 0)
            {
                codeMap.put(node.right.chr, total += "1");
            }

            setCodeMap(node.right, total);
        }
    }

    private void sortQueue()
    {
        BitTreeNode[] bt;

        while(charQueue.size() != 1)
        {
            BitTreeNode b1 = charQueue.poll();
            b1.setCode(0);
            order();

            BitTreeNode b2 = charQueue.poll();
            b2.setCode(1);
            order();

            BitTreeNode tempNode = new BitTreeNode(b1.getFreq() + b2.getFreq(), b1, b2);

            charQueue.add(tempNode);

            order();
        }

    }

    private void order()
    {
        BitTreeNode[] bt = charQueue.toArray(new BitTreeNode[0]);

        bt = Sort.bubble(bt);

        charQueue.clear();

        for(int i = 0; i < bt.length; i++)
        {
            charQueue.add(bt[i]);
        }
    }



    /**
     * Checks for space and new line special characters
     * @param key
     * @return key type
     */
    private String checkSpecialChar(String key)
    {
        if(key.equals(" "))
        {
            return "sp.";
        }else if(key.equals(""))
        {
            return "nl";
        }

        return null;
    }


    /**
     * prints the queue of BitTreeNodes
     * @return
     */
    private String printQueue()
    {
        return "" + charQueue;
    }

    private String printCodeMap()
    {
        String allCharacters = "";

        Iterator<String> list = codeMap.keySet().iterator();

        String currentKey;

        while(list.hasNext())
        {

            currentKey = list.next();

            allCharacters += currentKey + " " + codeMap.get(currentKey) + "\n";

        }

        return allCharacters;
    }

    /**
     *
     * @return elements in characters map
     */
    public String toString()
    {
        String allCharacters = "";

        Iterator<String> list = characters.keySet().iterator();

        String currentKey;

        while(list.hasNext())
        {

            currentKey = list.next();

            allCharacters += currentKey + " " + characters.get(currentKey) + "\n";

        }

        return allCharacters;
    }

}
