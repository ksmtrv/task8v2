package ru.vsu.cs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] arr = readFile(args);

        int stepsC = readSomething("step shear of the columns:");
        int stepsR = readSomething("line shift step:");

        printArray(arr);
        System.out.println();

        moveRow(arr, stepsR);
        moveColumns(arr, stepsC);

        writeFile(args, arr);
    }

    private static int[][] readFile(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File input = new File(args[0]);
        try {
            scanner = new Scanner(input);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not found. Enter your details:");
        }
        return readArray(scanner);
    }

    private static int[][] readArray(Scanner scanner) {
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int[][] arr = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
        return arr;
    }

    private static int readSomething(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter %s ", name);
        return scanner.nextInt();
    }

    private static String printArray(int[][] arr) {
        String stringArray = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
                stringArray += arr[i][j] + " ";
            }
            System.out.println();
            stringArray += "\n";
        }
        return stringArray;
    }

    private static void moveColumns(int[][] arr, int stepsC) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int steps = stepsC;
            while (steps-- > 0) {
                int f = arr[i][0];
                for (int j = arr[i].length - 1; j >= 0; j--) {
                    int l = arr[i][j];
                    arr[i][j] = f;
                    f = l;
                }
            }
        }
    }

    private static void moveRow(int[][] arr, int stepsR) {
        for (int i = arr.length + stepsR; i >= 1; i--) {
            int[][] arrNew = arr.clone();
            arr[0] = arrNew[arr.length - 1];
            for (int j = 1; j < arr.length; j++) {
                arr[j] = arrNew[j - 1];
            }
        }
    }

    private static void writeFile(String[] args, int[][] arr) throws Exception {
        FileWriter output = new FileWriter(args[1]);
        output.write(printArray(arr));
        output.close();
    }
}