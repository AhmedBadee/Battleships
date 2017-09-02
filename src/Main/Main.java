package Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int playerHits = 0;
        int computerHits = 0;

        boolean turnResult;

        int[][] field = new int[10][10];

        updateField(field);
        playerDeploy(field);
        computerDeploy(field);

        while (playerHits != 5 || computerHits != 5) {
            turnResult = playerTurn(field);
            if (turnResult) {
                playerHits++;
            }

            turnResult = computerTurn(field);
            if(turnResult) {
                computerHits++;
            }
        }
    }

    private static void playerDeploy(int[][] field) {
        int x;
        int y;
        Scanner input = new Scanner(System.in);

        for (int inputs = 0; inputs < 5; inputs++) {
            System.out.print("Enter X coordinate for your " + (inputs + 1) + " ship: ");
            x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + (inputs + 1) + " ship: ");
            y = input.nextInt();

            if (x >= 10 | y >= 10) {
                System.out.println("This position is outside the grid!");
                inputs -= 1;
            } else {
                if (field[x][y] != 0) {
                    System.out.println("This position is occupied!");
                    inputs -= 1;
                } else {
                    field[x][y] = 1;
                }
            }
        }

        updateField(field);
    }

    private static void computerDeploy(int[][] field) {
        int x;
        int y;

        System.out.println("Computer is deploying ships!");

        for (int inputs = 0; inputs < 5; inputs++) {
            x = (int) (Math.random() * 9);
            y = (int) (Math.random() * 9);

            if (field[x][y] != 0) {
                System.out.println("This position is occupied!");
                inputs -= 1;
            } else {
                field[x][y] = 2;
                System.out.println((inputs + 1) + " ship DEPLOYED");
            }
        }

        updateField(field);
    }

    private static void updateField(int[][] field) {

        System.out.println();
        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < field.length; i++) {
            System.out.print(i);
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 0 || field[i][j] == 2)
                    System.out.print("  ");
                else if (field[i][j] == 1)
                    System.out.print("@ ");
                else {
                    System.out.print("x ");
                }
            }
            System.out.println(i);
        }

        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static boolean playerTurn(int[][] field) {
        int x;
        int y;
        Scanner input = new Scanner(System.in);

        System.out.println("Your Turn: ");
        System.out.println("Enter X coordinate for your target ");
        x = input.nextInt();
        System.out.println("Enter Y coordinate for your target ");
        y = input.nextInt();

        if (field[x][y] == 2) {
            System.out.println("You hit :D");
            field[x][y] = 'x';
            updateField(field);
            return true;
        } else if (field[x][y] == 1) {
            System.out.println("You missed :(");
            updateField(field);
            return false;
        } else {
            System.out.println("You missed :(");
            field[x][y] = 'x';
            updateField(field);
            return false;
        }
    }

    private static boolean computerTurn(int[][] field) {
        int x = (int) (Math.random() * 9);
        int y = (int) (Math.random() * 9);

        if (field[x][y] == 1) {
            System.out.println("Computer hit :D");
            field[x][y] = 'x';
            updateField(field);
            return true;
        } else if (field[x][y] == 2) {
            System.out.println("Computer missed :(");
            updateField(field);
            return false;
        } else {
            System.out.println("Computer missed :(");
            field[x][y] = 'x';
            updateField(field);
            return false;
        }
    }
}
