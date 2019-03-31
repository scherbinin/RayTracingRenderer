package scene.primitives;

/**
 * Created by scher on 24.03.2019.
 */
public class Matrix {
    private int rowsCount;
    private int columnsCount;
    private double[] decimals;

    public Matrix(int rowsCount, int columnsCount, double[] matrix) {
        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        this.decimals = matrix;
    }

    protected double getElement(int index) {
        return decimals[index];
    }

    public double getElement(int i, int j) {
        return decimals[columnsCount * i + j];
    }

    public Matrix multiply(Matrix matrix) {
        if (matrix.rowsCount != this.columnsCount)
            throw new UnsupportedOperationException("Matrix has not compatible dimension");

        double[] result = new double[rowsCount * matrix.getColumnsCount()];
        int index = 0;

        for (int i = 0; i < matrix.getColumnsCount(); i++) {
            for (int j = 0; j < this.rowsCount; j++) {
                double element = 0;

                for (int k = 0; k < this.columnsCount; k++) {
                    element += getElement(j, k) * matrix.getElement(k, i);
                }

                result[index++] = element;
            }

        }

        return new Matrix(rowsCount, matrix.getColumnsCount(), result);
    }

    public Vector toVector(){
        if(((rowsCount == 3 || rowsCount == 4) &&columnsCount ==1) ||
                ((columnsCount == 3 || columnsCount == 4 ) && rowsCount == 1)) {
            return new Vector(decimals[0], decimals[1], decimals[2]);
        }

        throw new UnsupportedOperationException("Matrix can't be converted to vector");
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public static Matrix getScale(double x, double y, double z) {
        return new Matrix(3, 3, new double[] {x , 0, 0, 0, y, 0, 0, 0, z});
    }

    public static Matrix getRotationX(int angle) {
        double radians = Math.toRadians(angle);
        return new Matrix(3,3,
                new double[] {1, 0, 0,
                        0, Math.cos(radians), -Math.sin(radians),
                        0, Math.sin(radians), Math.cos(radians)});
    }

    public static Matrix getRotationY(int angle) {
        double radians = Math.toRadians(angle);
        return new Matrix(3,3,
                new double[] {Math.cos(radians), 0, Math.sin(radians),
                        0, 1, 0,
                        -Math.sin(radians), 0, Math.cos(radians)});
    }

    public static Matrix getRotationZ(int angle) {
        double radians = Math.toRadians(angle);
        return new Matrix(3,3,
                new double[] {Math.cos(radians), -Math.sin(radians), 0,
                        Math.sin(radians), Math.cos(radians), 0,
                        0, 0, 1});
    }

    /*
    The matrix has a bit specific view: all coordinates should be presented like 4th column, but except Z-axis,
    Explanation: For vector, which describe the ray of projection - the when the any coordinate getting equal Zero -
    we are loosing
     */
    public static <T extends Point> Vector translate(T obj, double deltaX, double deltaY, double deltaZ) {



        Matrix transform = new Matrix(4,4, new double[] {1, 0, 0, deltaX,
                                                                    0, 1, 0, deltaY,
                                                                    0, 0, 1, deltaZ,
                                                                    0, 0, 0, 1});
        Matrix primitive = new Matrix(4, 1, new double[] {obj.getX(), obj.getY(), obj.getZ(), 1});

        return transform.multiply(primitive).toVector();
    }

    public static <T extends Point> Vector rotate(T primitive, int angleX, int angleY, int angleZ) {
        Matrix rotated = getRotationX(angleX).multiply((primitive.toMatrix()));
        rotated = getRotationY(angleY).multiply(rotated);
        rotated = getRotationZ(angleZ).multiply(rotated);

        return rotated.toVector();
    }

    @Override
    public boolean equals(Object matrix) {
        if (matrix == null || !(matrix instanceof Matrix))
            return false;

        if (matrix == this)
            return true;

        if (rowsCount == ((Matrix) matrix).getRowsCount() && columnsCount == ((Matrix) matrix).getColumnsCount()) {
            for (int i =0; i < rowsCount*columnsCount; i++) {
                if (decimals[i] != ((Matrix) matrix).getElement(i))
                    return false;
            }

            return true;
        }

        return false;
    }
}
