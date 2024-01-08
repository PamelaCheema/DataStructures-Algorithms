import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
public class EmptyMap {

    private File f;
    private String fileName;
    private FileReader fr;
    private BufferedReader br;
    private String[] currentCoordinates;
    private double[] xCoordinates;
    private double[] yCoordinates;

    private int numSub;


    public void visualize(String wa) {
        try{
            StdDraw.setCanvasSize(1000, 600);
            fileName = "./input/" + wa + ".txt";
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

                    StdDraw.polygon(xCoordinates, yCoordinates);

                }
            }
            StdDraw.show();
            br.close();


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

}
