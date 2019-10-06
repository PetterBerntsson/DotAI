import java.applet.Applet;
import java.awt.*;
import java.util.ArrayList;

public class DotAI extends Applet implements Runnable {


    Graphics gfx;
    Image img;

    final int WIDTH = 700, HEIGHT = 700;
    Thread thread;

    int population = 500;
    int startX = 250;
    int startY = 400;

    Herder herder;
    MutationChamber mutationChamber;
    Goal goal;
    Evaluator evaluator;

    final double MP = 0.09;
    final int MQ = 1;
    final int MS = 1;


    int steps;
    double harshness = 0.8;





    public void init() {
        this.resize(WIDTH, HEIGHT);
        img = createImage(WIDTH, HEIGHT);
        gfx = img.getGraphics();

        // -------------------------------

        steps = 8000;
        goal = new Goal(250, 40);

        herder = new Herder(startX, startY, goal);
        herder.createHerd(population);


        mutationChamber = new MutationChamber(steps, MP, MQ, MS);
        mutationChamber.firstBrains(herder.getHerd());

        evaluator = new Evaluator(harshness, goal);


        herder.initializeMoveSets();


        // -------------------------------


        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, WIDTH, HEIGHT);
        goal.draw(gfx);
        herder.update(gfx);

        if(herder.allDead()) {
            nextGeneration();
        }


        g.drawImage(img, 0,0, this);

    }

    public void update(Graphics g) {
        paint(g);
    }

    public void run() {
        for(;;) {



            repaint();

        }
    }

    public void nextGeneration() {
        ArrayList<Dot> newHerd = mutationChamber.evolve(evaluator.getFittest(herder.getHerd()), herder.getNewHerd(population, harshness));
        herder.setNewHerd(newHerd);
        herder.initializeMoveSets();
    }



}