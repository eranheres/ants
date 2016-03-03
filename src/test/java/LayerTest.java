import com.pb.ai.Layer;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LayerTest {

    @Test
    public void testGenerate() throws Exception {
        Layer layer = Layer.generate(10, 3);
        assertEquals(layer.getAmountOfNeurons(), 10);
        assertEquals(layer.getInputsPerNeuron(), 3);

    }
}