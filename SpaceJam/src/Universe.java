
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.*;
public class Universe {

    private int numParticles;
    private Particle[] particles;
    private double radius;
    private double deltaT;
    private File f;
    private String fileName;
    private FileReader fr;
    private BufferedReader br;
    private String[] currentPlanet;
    public Universe(String planetsFileName, String universeFileName, double dt) {
        fileName = planetsFileName;
        deltaT = dt;
        readAndLoadUniverse(universeFileName);
    }
    public void readAndLoadUniverse(String fileName)
    {
        //Particle p = new Particle();
        try {
            fileName = "./src/input/" + this.fileName;
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            numParticles = Integer.parseInt(br.readLine());
            radius = Double.parseDouble(br.readLine());
            ArrayList<String> planets = new ArrayList<String>();
            //String[] currentPlanet;
            Particle[] particles1 = new Particle[numParticles];
            //System.out.println(numParticles + " " + radius);
            for(int i = 0; i < numParticles; i++)
            {
                currentPlanet = (br.readLine().split(" "));
                particles1[i] = particles1[i].buildParticleFromFile(currentPlanet);

            }
            particles = particles1;
        }catch(Exception e){

        }
    }

    public void calculateForces()
    {

    }

    public void updateVelocityAndPos()
    {
        for(int i = 0; i < particles.length; i++)
        {
            double ax = particles[i].getFX()/particles[i].getMass();
            double ay = particles[i].getFY()/particles[i].getMass();
            particles[i].setVX((particles[i].getVX()) + (deltaT * (ax)));
            particles[i].setVY((particles[i].getVY()) + (deltaT * ay));

            particles[i].setX(particles[i].getX() + (deltaT * particles[i].getVX()));
        }
    }

    public void timeStepUniverse()
    {
        double t = 0.0;
    }

    public String toString()
    {
        return fileName;
    }
}