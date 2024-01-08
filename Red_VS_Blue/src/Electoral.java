import java.awt.Color;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Electoral {



    private File f;
    private String fileName;
    private FileReader fr;
    private BufferedReader br;
    private String[] currentCoordinates;
    private double[] xCoordinates;
    private double[] yCoordinates;
    private String wa;
    private int year;

    private int numSub;
    private Map<String, double[][]> locations = new HashMap<String,double[][]>();//
    private Map<String, String> pairs = new HashMap<String, String>();

    public void visualize(String wa, int year) {
        try{
            StdDraw.setCanvasSize(1000, 600);
            fileName = "./input/" + wa + ".txt";
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            StdDraw.enableDoubleBuffering();

            double[] xyScale = new double[4];
            this.wa = wa;
            this.year = year;

            //Set<double[][]> coordinatesSet;

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
                    if(srName.indexOf("Parish") > -1)
                    {
                        String[] w = srName.split(" Parish");
                        srName = w[0];
                    }
                    if(srName.equals("St. Louis City"))
                    {
                        srName = "St. Louis";
                    }

                    if(locations.get(srName) == null)
                    {
                        locations.put(srName, pairs);
                    }else{
                        System.out.println(srName);
                        locations.replace(srName, locations.get(srName), addCoordinateValues(locations.get(srName),pairs));
                    }
                    //locations.put(srName, pairs);
                    StdDraw.polygon(pairs[0], pairs[1]);
                }
            }
            setRegions();
            StdDraw.show();
            br.close();


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

    private void drawRegion(String region)
    {
        try{
            fileName = "./input/" + wa + year +  ".txt";
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String currentLine = br.readLine();
            String[] votes = currentLine.split(",");
            while(currentLine != null)
            {
                votes = currentLine.split(",");
                if(votes[0].equals(region))
                {
                    setColor(votes);
                    StdDraw.filledPolygon(locations.get(region)[0], locations.get(region)[1]);
                }
                currentLine = br.readLine();
            }
            StdDraw.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setRegions()
    {
        try{
            fileName = "./input/" + wa + year +  ".txt";
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String currentLine = br.readLine();
            String[] votes = currentLine.split(",");
            while(currentLine != null)
            {
                votes = currentLine.split(",");
                if(locationsKeyExists(votes[0]))
                {
                    setColor(votes);
                    StdDraw.filledPolygon(locations.get(votes[0])[0], locations.get(votes[0])[1]);

                }
                currentLine = br.readLine();
            }

            StdDraw.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public double[] getxCoordinates()
    {
        return xCoordinates;
    }

    public double[] getyCoordinates()
    {
        return yCoordinates;
    }

    private void setColor(String[] eVotes)
    {
        if(Integer.parseInt(eVotes[1]) > Integer.parseInt(eVotes[2]))
        {
            StdDraw.setPenColor(Color.RED);
        }else if(Integer.parseInt(eVotes[1]) < Integer.parseInt(eVotes[2])){
            StdDraw.setPenColor(Color.BLUE);
        }else{
            StdDraw.setPenColor(Color.GREEN);
        }
    }

    private boolean locationsKeyExists(String key)
    {
        if(locations.containsKey(key))
        {
            return true;
        }
        return false;
    }

}
