import com.pb.ai.Genome;
import com.pb.ai.MutationAlgorithm;
import com.pb.ai.Population;
import com.pb.ai.Spawn;
import com.pb.ant.Ant;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.*;

/**
 * Created by eranh on 2/21/16.
 */
public class MutationAlgorithmTest {

    @Test
    public void testMutate() throws Exception {
        Population population = new Population();
        List<Spawn> list = population.getSpawns();
        Genome mock0 = mock(Genome.class);
        Genome mock1 = mock(Genome.class);
        Genome mock2 = mock(Genome.class);
        Genome mock3 = mock(Genome.class);
        Genome mock4 = mock(Genome.class);
        list.add(new Ant(mock0));
        list.add(new Ant(mock1));
        list.add(new Ant(mock2));
        list.add(new Ant(mock3));
        list.add(new Ant(mock4));

        Random rand = mock(Random.class);
        when(rand.nextInt(5)).thenReturn(0)
                .thenReturn(3);
        MutationAlgorithm mutationAlgorithm = new MutationAlgorithm(rand);
        mutationAlgorithm.mutate(population, 2.0/5.0, 1, 2);

        verify(mock0).mutate(1, 2);
        verify(mock3).mutate(1, 2);
        verifyNoMoreInteractions(mock0, mock3);
        verifyZeroInteractions(mock1,mock2,mock4);

    }
}