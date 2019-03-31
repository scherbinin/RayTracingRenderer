import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import scene.primitives.Matrix;
import scene.primitives.Vector;

/**
 * Created by scher on 24.03.2019.
 */

@RunWith(JUnit4.class)
public class MatrixTest {
    @Test
    public void matrixMultiTest(){
        Matrix a = new Matrix(3,3, new double[]{1,2,3,4,5,6,7,8,9});
        Matrix b = new Matrix(3,1, new double[]{1,2,3});
        Matrix expected = new Matrix(3,1, new double[]{14, 32, 50});

        Matrix actual = a.multiply(b);

        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void matrixTranslationTest(){
        Vector vector = new Vector(1,2,3);
        Vector expected = new Vector(2,1, 4);

        Vector actual = Matrix.translate(vector, 1, -1, 1);

        Assert.assertTrue(actual.equals(expected));
    }
}
