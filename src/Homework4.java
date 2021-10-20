import java.util.Random;
import java.util.Scanner;

public class Homework4 {
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();
    public static void main (String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkLines(DOT_X) || checkDiagonal(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
           aiTurn();
            printMap();
            if (checkLines(DOT_O) || checkDiagonal(DOT_O)) {
                System.out.println("Победил Искусственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }
    public static boolean checkDiagonal(char symb) {
        boolean right, left;
        right = true;
        left = true;
        for (int i=0; i<3; i++) {
            right &= (map[i][i] == symb);
            left &= (map[3-i-1][i] == symb);
        }
        if (right || left) return true;
        return false;
    }
    public static boolean checkLines(char symb) {
        boolean vertical, rows;
        for (int col = 0; col < 3; col++) {
            vertical = true;
            rows = true;
            for (int row = 0; row < 3; row++) {
                vertical &= (map[col][row] == symb);
                rows &= (map[row][col] == symb);
            }
            if (vertical || rows) return true;
        }
        return false;
    }



    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }
    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты X Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x,y));
        map[y][x] = DOT_X;
    }
    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер ходит в точку" + (x+1) + " " + (y+1));
        map[y][x] = DOT_O;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j= 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    public static void printMap() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }System.out.println();
    }
}
