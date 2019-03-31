import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import scene.primitives.Vector;

/**
 * Created by scher on 24.03.2019.
 */
@RunWith(JUnit4.class)
public class VectorTest {
    @Test
    public void checkVectorReflectionByNormalVector() {
        Vector someVector = new Vector(1,1,0);
        Vector someNormalVector = new Vector(1,0, 0).normalize();

        Vector actual = someVector.reflectByNormalVector(someNormalVector);
        Vector expected = new Vector(1,-1,0);

        Assert.assertTrue(expected.equals(actual));
    }
}
