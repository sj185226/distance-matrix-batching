import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BatchDistanceMatrixTester {
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter (1) to test randomly and 2 to test with data");
        int choice = scanner.nextInt();
        if(choice == 1) {
            test();
            scanner.close();
            return;
        }
        System.out.println("batch size");
        int batchSize = scanner.nextInt();
        System.out.println("values");
        List<Integer> values = Arrays.asList(scanner.nextLine().split(",")).stream().map((i) -> Integer.parseInt(i.trim())).collect(Collectors.toList());
        List<List<Integer>> res = test(batchSize, values);
        System.out.println("result");
        System.out.println(res);
        scanner.close();
    }

    public static List<List<Integer>> test(int batchSize, List<Integer> values) {
        BatchDistanceMatrixBuilder dmb = new BatchDistanceMatrixBuilder(batchSize);
        return dmb.solve(data);
    }

    public static void test() {
        int totalTests = 100;
        int passed=0;
        int failed=0;
        for(int tc=0; tc<totalTests; tc++) {
            try {
                failed++;
                int dataLimit = 10000;
                int cpLimit = 10000-16;

                Random random = new Random();

                int cpCount = random.nextInt(cpLimit)+16;
                int batchSize = random.nextInt(cpCount-1)+1;

                List<Integer> data = new ArrayList<>();
                for(int i=0; i<cpCount; i++) {
                    data.add(random.nextInt(dataLimit));
                }

                List<List<Integer>> expectedResult = new ArrayList<>();
                for(int i=0; i<cpCount; i++) {
                    List<Integer> ls = new ArrayList<>();
                    for(int j=0; j<cpCount; j++) {
                        ls.add(BatchDistanceMatrixBuilder.getVal(i, j, data.get(i), data.get(j)));
                    }
                    expectedResult.add(ls);
                }

                BatchDistanceMatrixBuilder dmb = new BatchDistanceMatrixBuilder(batchSize);
                List<List<Integer>> actualResult = dmb.solve(data);

                checkResult(batchSize, data, actualResult, expectedResult);
                failed--;
                passed++;
            } catch(IncorrectResultException ex) {
                ex.printInfo();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void checkResult(int batchSize, List<Integer> data, List<List<Integer>> actualResult, List<List<Integer>> expectedResult) throws IncorrectResultException {
        if(actualResult.size() != expectedResult.size()) {
            throw new IncorrectResultException(batchSize, data, expectedResult, actualResult, "row Count");
        }

        for(int i=0; i<actualResult.size(); i++) {
            if(actualResult.get(i).size() != expectedResult.get(i).size()) {
                throw new IncorrectResultException(batchSize, data, expectedResult, actualResult, "at row " + i);
            }
            for(int j=0; j<actualResult.get(i).size(); j++) {
                if(actualResult.get(i).get(j) != expectedResult.get(i).get(j)) {
                    throw new IncorrectResultException(batchSize, data, expectedResult, actualResult, null);
                }
            }
        }
    }
}
