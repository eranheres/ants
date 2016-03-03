import com.pb.ai.Genome;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * Created by eranh on 2/19/16.
 */
public class GenomeTest {

    @Test
    public void testMutate() throws Exception {
        Random random = mock(Random.class);
        double[] dna = new double[] {20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0};
        Genome genome = new Genome(dna, 0.0, random);
        when(random.nextDouble())
                .thenReturn(0.5)   // Mutate 0.5*0.5*8 = 2
                .thenReturn(0.2)   // Mutate first pos 1
                .thenReturn(0.75)  // Mutate by 2*0.75 - 1 = 0.5
                .thenReturn(0.9)   // Mutate first pos 7
                .thenReturn(0.25); // Mutate by 2*0.25 - 1 = -0.5
        genome.mutate(0.5, 2);
        List<Double> expected = Arrays.asList(20.0, 21.5, 22.0, 23.0, 24.0, 25.0, 26.0, 26.5);
        assertEquals(genome.getChromo(), expected);
    }

    @Test
    public void testCrossover() throws Exception {
        Random random = mock(Random.class);
        double[] dna1 = new double[] {20.0, 21.0, 22.0, 23.0, 24.0, 25.0, 26.0, 27.0};
        Genome genome1 = new Genome(dna1, 0.0, random);
        double[] dna2 = new double[] {70.0, 71.0, 72.0, 73.0, 74.0, 75.0, 76.0, 77.0};
        Genome genome2 = new Genome(dna2, 0.0, random);
        when(random.nextDouble()).thenReturn(0.5).thenReturn(0.75);

        genome1.crossover(genome2);
        List<Double> exp1 = Arrays.asList(20.0, 21.0, 22.0, 23.0, 74.0, 75.0, 76.0, 27.0);
        List<Double> exp2 = Arrays.asList(70.0, 71.0, 72.0, 73.0, 24.0, 25.0, 26.0, 77.0);
        assertEquals(genome1.getChromo(), exp1);
        assertEquals(genome2.getChromo(), exp2);

    }
}