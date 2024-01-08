import java.awt.*;

public class Snow {



    public static void base(int n, int x, int y, int size)
    {
        for(int i = 0; i <= 360; i += (60))
        {
            double rad = i * (Math.PI/180);
            int tempX = (int)(x + (Math.cos(rad)) * size);
            int tempY = (int)(y + (Math.sin(rad)) * size);
            StdDraw.line(x, y, tempX, tempY);
        }
    }

    public static void snow(int n, int x, int y, int size)
    {

            StdDraw.setPenColor(153, 255, 255);

        if(n == 0)
        {
            base(n, x, y, size);
        }

        if(n > 0)
        {
            for(int i = 0; i < 360; i+= 60)
            {
                double rad = i * (Math.PI/180);
                int tempX = (int)(x + Math.cos(rad) * size);
                int tempY = (int)(y + Math.sin(rad) * size);
                StdDraw.line(x, y, tempX, tempY);
                snow(n - 1, tempX, tempY, size/2);
            }
        }
    }




    public static void main(String[] args)
    {
        StdDraw.setXscale(-10, 10);
        StdDraw.setYscale(-10, 10);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledSquare(0, 0, 10);
        base(7,0, 0, 5);
    }
}
