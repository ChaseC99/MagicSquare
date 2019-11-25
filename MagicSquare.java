// Magic Square
//  Create by Chase Carnaroli

public class MagicSquare {
    public static void main(String[] args) {
        // Get input
        System.out.print("Enter n: ");
        int n = Integer.parseInt(System.console().readLine());

        // Verify that input is odd
        if (n%2 == 0) {
            throw new IllegalArgumentException("n must be an odd number");
        }

        // Create 2D square
        int[][] magicSquare = new int[n][n];

        // Assign top middle position
        int i = 1;
        magicSquare[0][n/2] = i;
        i++;

        // Create magic square
        magicSquare = magic_helper_recursive(n/2, 0, i, n*n, magicSquare);

        // Output
        printSquare(magicSquare);
    }

    // Recursive strategy
    private static int[][] magic_helper_recursive(int x, int y, int i, int max, int[][] square) {
        // Base case
        if (i > max) {
            return square;
        }

        // Find next postion
        // Default is up one and to the right
        // If at an edge of the square, it loops around to the other side
        int newX = x == square.length - 1 ? 0 : x + 1;
        int newY = y == 0 ? square.length - 1 : y-1;

        // Check if next postion is open
        if (square[newY][newX] == 0) {
            // Next postiion is open, assign i to it
            square[newY][newX] = i;

            i++;
            return magic_helper(newX, newY, i, max, square);
        } else {
            // Next postion is taken, assign i to the one below current position
            square[y+1][x] = i;

            i++;
            return magic_helper(x, y+1, i, max, square);
        }
    }

    // Loop strategy
    private static int[][] magic_helper(int x, int y, int start, int max, int[][] square) {
        // Intialize variables
        int newX, newY;    
        
        // Loop while i is less than the max
        for(int i=start; i <= max; i++){
            // Find next position
            newX = x == square.length - 1 ? 0 : x + 1;
            newY = y == 0 ? square.length - 1 : y-1;        
 
            // Check if next postion is open
            if (square[newY][newX] == 0) {
                // Next postion is open, assign i to it
                square[newY][newX] = i;
            
                // Update current position
                x = newX;
                y = newY;
            } else {
                // Next position is taken, assign i to the one below current position
                square[y+1][x] = i;

                // Update current position
                y = y+1;
            }   
        }

        // Return completed magic square
        return square;
    }

    // Print Row
    //  Prints a row divider
    //  Example: size = 3
    //      "|----|----|----|
    private static void printRow(int size) {
        for(int i=0; i < size; i++) { System.out.print("|----"); }
        System.out.println("|");
    }

    // Print Square
    //  Prints a 2D array as a grid
    //  Example: square = [[1, 2, 3],[4, 5, 6], [7, 8, 9]]
    //      |----|----|----|
    //      |1   |4   |7   |
    //      |----|----|----|
    //      |2   |5   |8   |
    //      |----|----|----|
    //      |3   |6   |9   |
    //      |----|----|----|
    private static void printSquare(int[][] square) {
        // Create top row
        printRow(square.length);
    
        // Print rows
        for(int y=0; y < square.length; y++) {
            // Print all of the values in the row
            for (int x=0; x < square[0].length; x++) {
                System.out.print(String.format("|%-4d", square[y][x]));
            }

            // Print the sum of the row
            System.out.print("| => ");
            int sum = sumRow(square, y);
            System.out.println(sum); 
            
            printRow(square.length);
        }

        // Print bottom sum
        System.out.println();
        for(int x=0; x < square.length; x++) {
            System.out.print(String.format(" %-4d", sumCol(square, x)));
        }

        System.out.println();
    }

    // Sum Row
    //  Given a 2D array and a row,
    //  return the sum of that row
    private static int sumRow(int[][] square, int row) {
        int sum = 0;
        for(int x=0; x<square.length; x++) {
            sum += square[row][x];
        } 
        return sum;
    }

    // Sum Col
    //  Given a 2D array and a col,
    //  return the sum of that col
    private static int sumCol(int[][] square, int col) {
        int sum = 0;
        for(int y=0; y<square.length; y++) {
            sum += square[y][col];
        }
        return sum;
    }
}
