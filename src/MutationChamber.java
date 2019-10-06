import java.util.ArrayList;
import java.util.Random;


// -------------------------------------- Class that handles all brain mutations for Dots
public class MutationChamber {

    //0-100% chance for affected gene to mutate
    double mutationProbability;

    //0 is no affected genes, 100 is all genes affected
    int mutationQuantity;

    //1=light variance in mutation, 2=stronger variance, 3=scrambling of gene
    int mutationStrength;

    int steps;



    public MutationChamber(int steps, double mutationProbability, int mutationQuantity, int mutationStrength) {
        this.mutationProbability = mutationProbability;
        this.mutationQuantity = mutationQuantity;
        this.mutationStrength = mutationStrength;

        this.steps = steps;
    }

    public void firstBrains(ArrayList<Dot> dots) {
        for(Dot dot: dots) {
            dot.setBrain(getNewBrain());
        }
    }

    public ArrayList<Dot> evolve(ArrayList<Dot> fittest, ArrayList<Dot> newHerd) {
        Brain[] cloneBrains = new Brain[fittest.size()];
        Dot[] dotArray = new Dot[fittest.size()];
        int i = 0;
        Random r = new Random();

        for(Dot dot: fittest) {
            dotArray[i] = dot;
            i++;
        }


        for(int j = 0; j < newHerd.size(); j++) {
            cloneBrains[j] = dotArray[r.nextInt(dotArray.length)].getBrain().clone();
            cloneBrains[j].mutate(mutationProbability);
        }
        i=0;

        for(Dot dot: newHerd) {
            dot.setBrain(cloneBrains[i]);
            i++;
        }
        fittest.addAll(newHerd);
        return fittest;
    }

    private Brain getNewBrain() {
        return new Brain(steps);
    }

    private void mutateBrain(Brain brain) {
        brain.mutate(mutationProbability);
    }
}
