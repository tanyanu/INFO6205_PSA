package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much. TODO tidy it
 * up a bit.
 */
public class Main {

    public static void main(String[] args) {
        processArgs(args);
        Scanner userInput = new Scanner(System.in);
        int defaultThreadCount = 0;
        int arrSize = 0; //2000000
            System.out.println("Please enter thread count - ");
            String threadCountVal = userInput.nextLine();
            defaultThreadCount = Integer.parseInt(threadCountVal);
            System.out.println("Please enter size of the array - ");
            String arrSizeVal = userInput.nextLine();
            arrSize = Integer.parseInt(arrSizeVal);

        Random random = new Random();
        int updatedArrSize = arrSize;
        for (int threadCount = defaultThreadCount; threadCount <= 32; threadCount = 2 * threadCount, updatedArrSize = 2
                * arrSize) {
            int[] arr = new int[updatedArrSize];
            ArrayList<Long> timeList = new ArrayList<>();
            ParSort.fjPool = new ForkJoinPool(threadCount);
            System.out.println("Degree of parallelism: " + ParSort.fjPool.getParallelism());
            for (int j = 50; j < 100; j++) {
                ParSort.cutoff = 10000 * (j + 1);
                long time;
                long startTime = System.currentTimeMillis();
                for (int t = 0; t < 10; t++) {
                    for (int i = 0; i < arr.length; i++)
                        arr[i] = random.nextInt(10000000);
                    ParSort.sort(arr, 0, arr.length);
                }
                long endTime = System.currentTimeMillis();
                time = (endTime - startTime);
                timeList.add(time);

                System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10 times Time :" + time + "ms");
            }
            try {
                String fileName = "result_" + threadCount + "_" + updatedArrSize + ".csv";
                FileOutputStream fis = new FileOutputStream("./src/parSortResults/" + fileName);
                OutputStreamWriter isr = new OutputStreamWriter(fis);
                BufferedWriter bw = new BufferedWriter(isr);
                int j = 50;
                for (long i : timeList) {
                    String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
                    j++;
                    bw.write(content);
                    bw.flush();
                }
                bw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-"))
                xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N"))
            setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) // noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();

}
