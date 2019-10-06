import java.security.SecureRandom;
import java.util.ArrayDeque;
import java.util.Random;



// -------------------------------------- Brain handles self-mutation and producing move-sets for Dots
public class Brain {

    int[] genome;
    int size;
    SecureRandom r;

    public Brain(int steps) {
        initialize(steps);
    }

    private void initialize(int steps) {
        this.size = steps;
        genome = new int[steps];
        r = new SecureRandom();

        for(int i = 0; i < steps; i++) {
            genome[i] = r.nextInt(8);
        }
    }

    private Brain() {// for creating a clone brain
    }

    public void cloneInitialize(int steps, int[] originalGenome) {
        this.size = steps;
        this.genome = new int[size];
        r = new SecureRandom();

        for (int i = 0; i < size; i++) {
            genome[i] = originalGenome[i];
        }
    }

    public void mutate(double mutationProbability) {


        for(int i = 0; i < size; i++) {

            if(r.nextInt(100) <= (int) mutationProbability*100) {
                genome[i] = r.nextInt(8);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public ArrayDeque<Movement> getMoveSet() {
        ArrayDeque<Movement> moveSet = new ArrayDeque<>();
        for(int i = 0; i < genome.length; i++ ) {
            switch(genome[i]) {
                case 0:
                    moveSet.add(new Movement(0,1));
                    break;
                case 1:
                    moveSet.add(new Movement(1,1));
                    break;
                case 2:
                    moveSet.add(new Movement(1,0));
                    break;
                case 3:
                    moveSet.add(new Movement(1,-1));
                    break;
                case 4:
                    moveSet.add(new Movement(0,-1));
                    break;
                case 5:
                    moveSet.add(new Movement(-1,-1));
                    break;
                case 6:
                    moveSet.add(new Movement(-1,0));
                    break;
                case 7:
                    moveSet.add(new Movement(-1,1));
                    break;
            }
        }


        return moveSet;
    }


    public Brain clone() {
        Brain clone = new Brain(); // leave empty construct when cloning
        clone.cloneInitialize(size, this.genome);
        return clone;
    }
}
