import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class SelectionAlgorithmTest {

    @Test
    public void testSelect() throws Exception {
        /*
        com.pb.ai.Population inPopulation = new com.pb.ai.Population();
        List<com.pb.ai.Spawn> input = inPopulation.getSpawns();
        input.add(new com.pb.ant.Ant(new com.pb.ai.Genome(10)));
        input.add(new com.pb.ai.Genome(9));   // 3
        input.add(new com.pb.ai.Genome(10));  // 1
        input.add( new com.pb.ai.Genome(7));  // 2
        input.add( new com.pb.ai.Genome(2));  // 4
        input.add( new com.pb.ai.Genome(2));  // 5
        input.add( new com.pb.ai.Genome(3));  // 6
        input.add( new com.pb.ai.Genome(7));
        input.add( new com.pb.ai.Genome(8));
        input.add( new com.pb.ai.Genome(1));
        Random random = mock(Random.class);
        when(random.nextInt(4)).thenReturn(1).thenReturn(2); // tournament sizes
        when(random.nextInt(10)).thenReturn(2);
        when(random.nextInt(9)).thenReturn(2);
        when(random.nextInt(8)).thenReturn(1);
        when(random.nextInt(7)).thenReturn(1);
        when(random.nextInt(6)).thenReturn(1);
        when(random.nextInt(5)).thenReturn(1);
        com.pb.ai.SelectionAlgorithm selectionAlgorithm = new com.pb.ai.SelectionAlgorithm(4, random);
        com.pb.ai.Population actual = selectionAlgorithm.select(inPopulation, 0.5);
        com.pb.ai.Population exp = new com.pb.ai.Population();
        List<com.pb.ai.Genome> expected = exp.getGenomes();
        expected.add(new com.pb.ai.Genome(10)); // 1
        expected.add(new com.pb.ai.Genome(7));   // 2
        expected.add(new com.pb.ai.Genome(9));   // 3
        expected.add(new com.pb.ai.Genome(2));   // 4
        expected.add(new com.pb.ai.Genome(10));   // 4
        assertEquals(actual, exp);
i*/
    }


}