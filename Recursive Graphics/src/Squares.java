public class Squares {

    public static void main(String[] args)
    {
        StdDraw.setXscale(-1, 1);
        StdDraw.setYscale(-1, 1);
        draw(7, 0, 0, .5);
    }

    public static void draw(int n, double x, double y, double radius)
    {
        if(n == 1)
        {
            drawSquare(n, x, y, radius);
        }else{
            drawSquare(n, x, y, radius);
        }

        if(n > 1)
        {
            draw(n -1, Transform2D.translate(x, y, radius, radius, 'x'), Transform2D.translate(x, y, radius, radius, 'y'), radius/2);
            draw(n -1, Transform2D.translate(x, y, -radius, -radius, 'x'), Transform2D.translate(x, y, -radius, -radius, 'y'), radius/2);
            draw(n -1, Transform2D.translate(x, y, -radius, radius, 'x'), Transform2D.translate(x, y, -radius, radius, 'y'), radius/2);
            draw(n -1, Transform2D.translate(x, y, radius, -radius, 'x'), Transform2D.translate(x, y, radius, -radius, 'y'), radius/2);
        }
    }

    private static void drawSquare(int n, double x, double y, double radius)
    {
        if(n%2 == 0)
        {
            StdDraw.setPenColor(255, 51, 153);
            drawCircle(x, y, radius);
        }else {
            StdDraw.setPenColor(153, 204, 255);
            StdDraw.square(x, y, radius);
        }
    }

    private static void drawCircle(double x, double y, double radius)
    {
        StdDraw.circle(x, y, radius);
    }
}
