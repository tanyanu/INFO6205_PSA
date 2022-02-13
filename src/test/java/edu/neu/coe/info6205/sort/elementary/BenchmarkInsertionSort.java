package edu.neu.coe.info6205.sort.elementary;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.util.ConfigTest;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.io.IOException;

public class BenchmarkInsertionSort {

    public static void main(String[] args) throws IOException {

        int N = 200;

        for (int i = 0; i <= 5; i++) {
            while (N <= 1600) {
                Helper<Integer> helper = new BaseHelper<>("Insertion sort", N,
                        ConfigTest.setupConfig("true", "0", "1", "", ""));
                SortWithHelper<Integer> arrSorter = new InsertionSort<>(helper);

                Benchmark<Integer[]> benchmarkTimer = new Benchmark_Timer<Integer[]>("Insertion Sort",
                        b -> arrSorter.sort(b));
                Integer[] randomArr = helper.random(Integer.class, r -> r.nextInt(1000));

                System.out.println("************************************************************");
                System.out.println("\n");

                double rndmRunningTime = benchmarkTimer.run(randomArr, N);
                System.out.println("(N) -> " + randomArr.length);
                System.out.println("Running Time Unsorted random elements : " + rndmRunningTime);

                System.out.println("************************************************************");
                System.out.println("\n");
                System.out.println("(N) -> " + randomArr.length);
                Integer[] ascArr = arrSorter.sort(randomArr);
                double ascRunningTime = benchmarkTimer.run(ascArr, N);
                System.out.println("Running Time ASC sorted : " + ascRunningTime);

                System.out.println("************************************************************");
                System.out.println("\n");
                arrSorter.sort(ascArr, ascArr.length - 1, 0);
                System.out.println("(N) -> " + ascArr.length);
                double descRunningOTime = benchmarkTimer.run(ascArr, N);
                System.out.println("Running Time DESC sorted: " + descRunningOTime);

                System.out.println("************************************************************");
                System.out.println("\n");
                arrSorter.sort(ascArr, 0, (ascArr.length) / 3);
                System.out.println("(N) -> " + ascArr.length);
                double partialRunningTime = benchmarkTimer.run(ascArr, N);
                System.out.println("Running Time Partially sorted : " + partialRunningTime);

                System.out.println("************************************************************");
                N = N + 200;
            }
        }

    }

}
