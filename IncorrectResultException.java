import java.util.List;

public class IncorrectResultException extends Exception {

    private int batchSize;
    private List<Integer> values;
    private List<List<Integer>> expectedResult;
    private List<List<Integer>> actualResult;
    IncorrectResultException(int batchSize, List<Integer> values, List<List<Integer>> expectedResult, List<List<Integer>> actualResult) {
        super();
        this.actualResult = actualResult;
        this.batchSize = batchSize;
        this.expectedResult = expectedResult;
        this.values = values;
    }

    public void printInfo() {
        System.err.println("Expected didn't matched with actual result for:-");
        System.err.println("batch size: " + batchSize);
        System.err.println("values: " + values);
        System.err.println("expected result: " + expectedResult);
        System.err.println("actual result: " + actualResult);

    }
}
