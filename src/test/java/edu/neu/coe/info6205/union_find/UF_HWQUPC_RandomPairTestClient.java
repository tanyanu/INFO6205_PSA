package edu.neu.coe.info6205.union_find;

import java.util.Random;

public class UF_HWQUPC_RandomPairTestClient {
    public static int getRandomInteger(int noOfSites) {
        int randomInt;
        Random random = new Random();
        if(noOfSites == 0) {
            randomInt = random.nextInt(100);
        } else {
            randomInt = random.nextInt(noOfSites);
        }
        return randomInt;
    }

    public static void main(String[] args) {
        //Random function to generate a random integer in range of 0 - 9
        int noOfSites = getRandomInteger(0);
        System.out.println("Initial number of sites are generated using the random function in range of 0 - 100 which is " + noOfSites);
        System.out.println("Initial number of site are then updated as  noOfSites += noOfSites in each iteration.");

        for (int i = 0; i < 20; i++) {
            noOfSites += noOfSites;
            UF_HWQUPC uf = new UF_HWQUPC(noOfSites);
            int noOfConnections = count(uf, noOfSites);
            System.out.println("Sites - " + noOfSites + " | Connections - " + noOfConnections);
        }
    }

    public static int count(UF_HWQUPC uf, int noOfSites) {
        int noOfConnections = 0;
        int i = 0, j = 0;
        while (uf.components() > 1) {
            i = getRandomInteger(noOfSites);
            j = getRandomInteger(noOfSites);
            uf.connect(i, j);
            noOfConnections++;
        }
        return noOfConnections;
    }
}
