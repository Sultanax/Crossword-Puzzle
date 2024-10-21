# Crossword Puzzle Solver

<img src="https://github.com/user-attachments/assets/736dfdaf-4713-4e4e-b1f0-1cc74b46abc1" alt="crossword-puzzle" width="800" height="400" />

This is our code for our AP CSA holiday-themed crossword puzzle solver.

The following is an explanation of each function:

1. main()
    Main function of the program that generates a random list of holiday words, creates a crossword puzzle, and displays the grid.
2. crossword()
    Generates a 25x25 grid and fills it with holiday words, positioning them based on the available space and matching criteria.
3. position()
    Determines the positions where a given word could fit in the crossword puzzle, either vertically or horizontally.
4. checkPosition()
    Checks and evaluates the position of a word in the grid, considering its length and orientation (vertical or horizontal).
5. mismatch()
    Compares two strings to calculate the number of matching characters, allowing for a count of correct character placements.
6. vertical()
    Retrieves a string of characters in a vertical sequence from the grid, given a starting row, column, and length.
7. horizontal()
    Retrieves a string of characters in a horizontal sequence from the grid, given a starting column, row, and length.

The program creates a dynamic crossword puzzle game by selecting a random set of holiday words from an array. It generates a 25x25 crossword grid, placing holiday names both vertically and horizontally. The program solves the puzzle by fitting words into the grid, considering available space and orientation. Once the puzzle is solved, the program depicts the array of chosen holiday words and the solved crossword puzzle!
