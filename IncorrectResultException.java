import java.util.List;

public class IncorrectResultException extends Exception {

    private int batchSize;
    private List<Integer> values;
    private List<List<Integer>> expectedResult;
    private List<List<Integer>> actualResult;
    private String pos;
    private LogWriter lw;
    IncorrectResultException(int batchSize, List<Integer> values, List<List<Integer>> expectedResult, List<List<Integer>> actualResult, String pos) {
        super();
        this.actualResult = actualResult;
        this.batchSize = batchSize;
        this.expectedResult = expectedResult;
        this.values = values;
        this.pos = pos;
        lw = new LogWriter();
    }

    public void printInfo() {
        if(pos != null) {
            lw.println("SizeMismatch for two results at " + pos);
        }
        lw.println("Expected didn't matched with actual result for:-");
        lw.println("batch size: " + batchSize);
        lw.println("data length " + actualResult.size());
        lw.println("values: " + values);
        lw.printResult("expected result: ", expectedResult);
        lw.printResult("actual result: ", actualResult);

    }
}
