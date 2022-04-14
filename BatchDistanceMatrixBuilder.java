public class BatchDistanceMatrixBuilder {

    // n-sqaure size (2500 actual)
    private int batchLimit;

    BatchDistanceMatrixBuilder(int batchLimit) {
        this.batchLimit = batchLimit;
    }

    //write batch methods for generation

    //method to get pseudo calculated value mimicking distance between points
    public static int getVal(int posi, int posj, int i, int j) {
        return posi*posj + posi + posj + i*j;
    }
}