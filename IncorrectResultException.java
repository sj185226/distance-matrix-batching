import java.util.List;

public class IncorrectResultException extends Exception {

    private int batchSize;
    private List<Integer> values;
    private List<List<Integer>> expectedResult;
    private List<List<Integer>> actualResult;
    private String pos;
    IncorrectResultException(int batchSize, List<Integer> values, List<List<Integer>> expectedResult, List<List<Integer>> actualResult, String pos) {
        super();
        this.actualResult = actualResult;
        this.batchSize = batchSize;
        this.expectedResult = expectedResult;
        this.values = values;
        this.pos = pos;
    }

    public void printInfo() {
        if(pos != null) {
            System.err.println("SizeMismatch for two results at " + pos);
        }
        System.err.println("Expected didn't matched with actual result for:-");
        System.err.println("batch size: " + batchSize);
        System.err.println("values: " + values);
        System.err.println("expected result: " + expectedResult);
        System.err.println("actual result: " + actualResult);

    }
}
