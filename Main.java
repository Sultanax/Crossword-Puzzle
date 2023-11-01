import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    String[] holidays = {"Thanksgiving", "Hanukkah", "Christmas", "Kwanzaa", "New Year", "Lunar New Year", "Groundhog", "Valentines", "Presidents", "Martin Luther King", "St Patrick", "Eid", "Passover", "Good Friday", "Easter", "Cinco de Mayo", "Independence", "Rosh Hashana", "Yom Kippur", "Veterans" };
    int n = (int) (Math.random() * 4) + 5;
    ArrayList<String> holidayList = new ArrayList<String>();
    int count = 0;
    while (count < n) {
      int index = (int)(Math.random() * holidays.length);
      String holiday = holidays[index];
      if (!holidayList.contains(holiday)) {
        holidayList.add(holiday);
        count++;
      }
    }
    System.out.println();
    System.out.println("Number of holiday words: " + n);
    System.out.println("The words to find are: " + holidayList);
    System.out.println();
    char[][] grid = crossword(holidayList);
    System.out.println("-----------------------------------------------");
    System.out.println("               Crossword Puzzle:               ");
    System.out.println();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        System.out.print(grid[i][j] + " ");
      }
    System.out.println();
    }
  }

  public static int[] position(String str2, char[][] grid) {
    int[] result = {-1, -1, -1, -1};
    for (int i = 0; i < 25; i++) {
      for (int j = 0; j < 25; j++) {
        if (i + str2.length() <= 25) {
          result = checkPosition(result, grid, str2, i, j, 0, vertical(grid, i, j, str2.length())); // if fits vertical
        }
        if (j + str2.length() <= 25) {
          result = checkPosition(result, grid, str2, i, j, 1, horizontal(grid, i, j, str2.length())); //if fits horizontal
        }
      }
    }
    return result;
  }

  public static int[] checkPosition(int[] result, char[][] grid, String str2, int i, int j, int orientation, String test) {
    int[] newResult = result;
    if ((orientation == 0 && (i + str2.length() == 25 || grid[i + str2.length()][j] == ' ') && (i == 0 || grid[i - 1][j] == ' ')) || (orientation == 1 && (j + str2.length() == 25 || grid[i][j + str2.length()] == ' ') && (j == 0 || grid[i][j - 1] == ' '))) {
      if (orientation == 0) { // vertical
        if (j - 1 >= 0) { // not left end
          vertical(grid, i, j - 1, str2.length());
        }
        if (j + 1 < 25) { // not right end
          vertical(grid, i, j + 1, str2.length());
        }
      } else { // horizontal
        if (i - 1 >= 0) { // not top end
          horizontal(grid, i - 1, j, str2.length());
        }
        if (i + 1 < 25) { // not bottom end
          horizontal(grid, i + 1, j, str2.length());
        }
      }
      int matchCount = mismatch(test, str2);
      if (matchCount > result[0]) {
        newResult = new int[]{matchCount, i, j, orientation}; // if better match, update
      }
    }
    return newResult;
  }

  public static char[][] crossword(ArrayList<String> holidayList) {
    char[][] grid = new char[25][25];
    for (int i = 0; i < 25; i++){
      for (int j = 0; j < 25; j++){
        grid[i][j] = ' ';
      }
    }
    int num = 0;
    int start = 5;
    for (int i = 0; i < holidayList.size(); i++) {
      String str = holidayList.get(i);
      if (num == 0) {
        for (int ind = 0; ind < str.length(); ind++){
          char letter = str.charAt(ind);
          grid[5][start] = letter;
          start++;
        }
      } else {
        int pos[] = position(str, grid);
        if (pos[0] == -1) {
          System.out.println(" ");
        } else if (pos[3] == 1) {
          int a = 0; // If adding word horizontally, add starting at pos[2] in row pos[1]
          for (int j = pos[2]; j < pos[2] + str.length(); j++) {
            grid[pos[1]][j] = str.charAt(a);
            a++;
          }
        } else {
          int b = 0; // If adding vertically, add starting at pos[1] in column pos[2]
          for (int j = pos[1]; j < pos[1] + str.length(); j++) {
            grid[j][pos[2]] = str.charAt(b);
            b++;
          }
        }
      }
      num++;
    }
    return grid;
  }

  public static int mismatch(String str1, String str2) {
    int matchCount = 0;
    boolean mismatch = false;
    for (int i = 0; i < str1.length(); i++) {
      if (str2.charAt(i) == str1.charAt(i)) {
        matchCount++;
      } else if (str1.charAt(i) != ' ') {
        mismatch = true;
        break;
      }
    }
    return mismatch ? -1 : matchCount; // if mismatch = true, return -1; if false, return count
  }

  public static String vertical(char[][] grid, int startRow, int col, int length) {
    String str = "";
    int endRow = startRow + length;
    for (int row = startRow; row < endRow; row++) {
      str += grid[row][col];
    }
    return str;
  }

  public static String horizontal(char[][] grid, int row, int startCol, int length) {
    String str = "";
    int endCol = startCol + length;
    for (int col = startCol; col < endCol; col++) {
      str += grid[row][col];
    }
    return str;
  }
}