public class Tester {

    public static void main(String[] args)
    {
        Picture picture = new Picture("./input/7x10.png");
        SeamCarver sc = new SeamCarver(picture);
        //System.out.println(sc.findVerticalSeam());
        System.out.println(sc.findHorizontalSeam());
        //System.out.println(sc.width());
        //System.out.println(sc.energy(2, 2));
    }
}
