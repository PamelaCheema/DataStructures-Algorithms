public class Particle {

    private double x;
    private double y;
    private double vx;
    private double vy;
    private double fx;
    private double fy;
    private double mass;
    private String fileName;
    public Particle(double x, double y, double vx, double vy, double mass, String fileName)
    {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.mass = mass;
        this.fileName = fileName;
    }
    public static Particle buildParticleFromFile(String[] line)
    {
        double xCoordinate = Double.parseDouble(line[0]);
        double yCoordinate = Double.parseDouble(line[1]);
        double xInitialVelocity = Double.parseDouble(line[2]);
        double yInitialVelocity = Double.parseDouble(line[3]);
        double bodyMass = Double.parseDouble(line[4]);
        String planetFile = line[5].trim();
        return new Particle(xCoordinate, yCoordinate, xInitialVelocity, yInitialVelocity, bodyMass, planetFile);
    }
    public double getDistance(Particle other)
    {
        double distance = Math.sqrt(Math.pow(other.x - this.x, 2.0) + Math.pow(other.x - this.x, 2.0));
        return distance;
    }
    public double setX(double x)
    {
        return this.x = x;
    }
    public double setY(double y)
    {
        return this.y = y;
    }
    public double setVX(double vx)
    {
        return this.vx = vx;
    }
    public double setVY(double vy)
    {
        return this.vy = vy;
    }
    public double setFX(double fx)
    {
        return this.fx = fx;
    }
    public double setFY(double fy)
    {
        return this.fy = fy;
    }
    public double setMass(double mass)
    {
        return this.mass = mass;
    }
    public String setFileName(String fileName)
    {
        return this.fileName = fileName;
    }
    public double getX()
    {
        return x;
    }
    public double getY()
    {
        return y;
    }
    public double getVX()
    {
        return vx;
    }
    public double getVY()
    {
        return vy;
    }
    public double getFX()
    {
        return fx;
    }
    public double getFY()
    {
        return fy;
    }
    public double getMass()
    {
        return mass;
    }
    public String toString()
    {
        return getX() + " " + getY() + " " + getVX() + " " +  getVY() + " " + getFX() + " " + getFY() + " " + getMass();
    }
}
