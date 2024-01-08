import java.awt.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;


public class ElectoralMap {

    private String[] votes;
    private String state;
    private int year;

    private Map<String, String> parties = new HashMap<String,String>();
    private Map<String, String> places = new HashMap<String, String>();
    Map<String, double[][]> m = new TreeMap<String, double[][]>();
    public void visualize(String wa, int i) {

        if(wa.equals("USA-county")) {
            drawUSACounty(wa, i);
        }else{
            try {

                File f;
                String fileName;
                FileReader fr;
                BufferedReader br;
                String current;
                fileName = "./input/" + wa + i + ".txt";
                f = new File(fileName);
                fr = new FileReader(f);
                br = new BufferedReader(fr);
                current = br.readLine();
                votes = current.split(",");

                state = wa;
                year = i;

                current = br.readLine();

                while (current != null) {
                    votes = current.split(",");

                    parties.put(votes[0], compareVotes(votes));

                    current = br.readLine();
                }

                setRegions();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void drawUSACounty(String wa, int year)
    {
        //EmptyMap em = new EmptyMap();
        //em.visualize(wa);
        try{
            File f;
            String fileName;
            FileReader fr;
            BufferedReader br;
            StdDraw.setCanvasSize(1000, 600);
            fileName = "./input/" + wa + ".txt";
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String[] currentCoordinates;
            double[] xyScale = new double[4];
            double[] xCoordinates;
            double[] yCoordinates;

            int numSub;
            currentCoordinates = br.readLine().split("   ");
            xyScale[0] = Double.parseDouble(currentCoordinates[0]);
            xyScale[1] = Double.parseDouble(currentCoordinates[1]);
            currentCoordinates = br.readLine().split("   ");
            xyScale[2] = Double.parseDouble(currentCoordinates[0]);
            xyScale[3] = Double.parseDouble(currentCoordinates[1]);

            numSub = Integer.parseInt(br.readLine());

            StdDraw.setXscale(xyScale[0], xyScale[2]);
            StdDraw.setYscale(xyScale[1], xyScale[3]);

            String currentLine;
            for(int i = 0; i < numSub; i++)
            {
                currentLine = br.readLine();
                if(currentLine.equals(""))
                {
                    currentLine = br.readLine();
                    String srName = currentLine;
                    currentLine = br.readLine();

                    //places.put(currentLine, srName);
                    if(!placesKeyExists(currentLine))
                    {
                        places.put(currentLine, srName);
                    }

                    currentLine = br.readLine();


                    xCoordinates = new double[Integer.parseInt(currentLine)];
                    yCoordinates = new double[Integer.parseInt(currentLine)];
                    for(int j = 0; j < Integer.parseInt(currentLine); j++)
                    {
                        currentCoordinates = br.readLine().split("   ");
                        xCoordinates[j] = Double.parseDouble(currentCoordinates[0]);
                        yCoordinates[j] = Double.parseDouble(currentCoordinates[1]);
                    }
                    double[][] pairs = {xCoordinates, yCoordinates};
                    if(srName.indexOf("Parish") > -1)
                    {
                        String[] w = srName.split(" Parish");
                        srName = w[0];
                    }
                    if(srName.equals("St. Louis City"))
                    {
                        srName = "St. Louis";
                    }
                    if(!mKeyExists(srName))
                    {
                        m.put(srName, pairs);
                    }else{
                        System.out.println(srName);
                        m.replace(srName, m.get(srName), addCoordinateValues(m.get(srName),pairs));
                    }

                }
            }
            Iterator<String> list = places.keySet().iterator();
            String currentKey;

            while(list.hasNext())
            {
                currentKey = list.next();
                setVotes(currentKey, year);

            }
            StdDraw.show();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private double[][] addCoordinateValues(double[][] orginalCoordinates, double[][] newCoordinates)
    {
        //add coordinates to the existing pairs
        double[] tempXCoordinates = new double[orginalCoordinates[0].length + newCoordinates[0].length];
        double[] tempYCoordinates = new double[orginalCoordinates[0].length + newCoordinates[0].length];
        for(int i = 0; i < orginalCoordinates[0].length; i++)
        {
            tempXCoordinates[i] = orginalCoordinates[0][i];
            tempYCoordinates[i] = orginalCoordinates[0][i];
        }
        for(int i = orginalCoordinates[0].length; i < newCoordinates[0].length; i++)
        {
            tempXCoordinates[i] = newCoordinates[1][i];
            tempYCoordinates[i] = newCoordinates[1][i];
        }
        double[][] tempCoordinates = {tempXCoordinates, tempYCoordinates};
        return tempCoordinates;
    }


    private void setVotes(String sr, int yr)
    {
        try{

            File f;
            String fileName;
            FileReader fr;
            BufferedReader br;
            String current;
            String[] votes;
            fileName = "./input/" + sr + yr + ".txt";
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            current = br.readLine();
            votes = current.split(",");


            current = br.readLine();

            while(current != null)
            {
                votes = current.split(",");



                if(mKeyExists(votes[0]))
                {
                    setUSAColor(compareVotes(votes));
                    StdDraw.filledPolygon(m.get(votes[0])[0], m.get(votes[0])[1]);
                }

                current = br.readLine();
            }
            StdDraw.show();
            br.close();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setRegions()
    {
        try{
            File f;
            String fileName;
            FileReader fr;
            BufferedReader br;
            String[] currentCoordinates;
            double[] xCoordinates;
            double[] yCoordinates;
            int numSub;
            StdDraw.setCanvasSize(1000, 600);
            fileName = "./input/" + state + ".txt";
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            StdDraw.enableDoubleBuffering();

            double[] xyScale = new double[4];



            currentCoordinates = br.readLine().split("   ");
            xyScale[0] = Double.parseDouble(currentCoordinates[0]);
            xyScale[1] = Double.parseDouble(currentCoordinates[1]);
            currentCoordinates = br.readLine().split("   ");
            xyScale[2] = Double.parseDouble(currentCoordinates[0]);
            xyScale[3] = Double.parseDouble(currentCoordinates[1]);

            numSub = Integer.parseInt(br.readLine());

            StdDraw.setXscale(xyScale[0], xyScale[2]);
            StdDraw.setYscale(xyScale[1], xyScale[3]);

            String currentLine;

            for(int i = 0; i < numSub; i++)
            {
                currentLine = br.readLine();
                if(currentLine.equals(""))
                {
                    currentLine = br.readLine();
                    String srName = currentLine;
                    currentLine = br.readLine();
                    currentLine = br.readLine();


                    xCoordinates = new double[Integer.parseInt(currentLine)];
                    yCoordinates = new double[Integer.parseInt(currentLine)];
                    for(int j = 0; j < Integer.parseInt(currentLine); j++)
                    {
                        currentCoordinates = br.readLine().split("   ");
                        xCoordinates[j] = Double.parseDouble(currentCoordinates[0]);
                        yCoordinates[j] = Double.parseDouble(currentCoordinates[1]);
                    }
                    double[][] pairs = {xCoordinates, yCoordinates};//
                    if(partyKeyExists(srName))
                    {
                        setColor(srName);
                        StdDraw.filledPolygon(xCoordinates, yCoordinates);
                    }

                }
            }
            StdDraw.show();
            br.close();


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void setColor(String key)
    {
        if(parties.get(key).equals("red"))
        {
            StdDraw.setPenColor(Color.BLUE);
        }else{
            StdDraw.setPenColor(Color.RED);
        }
    }

    private void setUSAColor(String color)
    {
        if(color.equals("blue"))
        {
            StdDraw.setPenColor(Color.BLUE);
        }else if(color.equals("red")){
            StdDraw.setPenColor(Color.RED);
        }else{
            StdDraw.setPenColor(Color.GREEN);
        }
    }

    private String compareVotes(String[] eVotes)
    {
        if(Integer.parseInt(eVotes[1]) < Integer.parseInt(eVotes[2]))
        {
            return "red";
        }else if(Integer.parseInt(eVotes[1]) > Integer.parseInt(eVotes[2])){
            return "blue";
        }else{
            return "green";
        }
    }

    private boolean partyKeyExists(String key)
    {
        if(parties.containsKey(key))
        {
            return true;
        }
        return false;
    }
    private boolean mKeyExists(String key)
    {
        if(m.containsKey(key))
        {
            return true;
        }
        return false;
    }

    private boolean placesKeyExists(String key)
    {
        if(places.containsKey(key))
        {
            return true;
        }
        return false;
    }

}
