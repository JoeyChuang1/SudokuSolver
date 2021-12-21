package finalproject;

import java.util.*;
import java.io.*;


public class ChessSudoku {
    /* SIZE is the size parameter of the Sudoku puzzle, and N is the square of the size.  For
     * a standard Sudoku puzzle, SIZE is 3 and N is 9.
     */
    public int SIZE, N;

    /* The grid contains all the numbers in the Sudoku puzzle.  Numbers which have
     * not yet been revealed are stored as 0.
     */
    public int grid[][];

    /* Booleans indicating whether of not one or more of the chess rules should be
     * applied to this Sudoku.
     */
    public boolean knightRule;
    public boolean kingRule;
    public boolean queenRule;


    // Field that stores the same Sudoku puzzle solved in all possible ways
    public HashSet<ChessSudoku> solutions = new HashSet<ChessSudoku>();
    /* The solve() method should remove all the unknown characters ('x') in the grid
     * and replace them with the numbers in the correct range that satisfy the constraints
     * of the Sudoku puzzle. If true is provided as input, the method should find finds ALL
     * possible solutions and store them in the field named solutions. */
   

    private boolean Rule1(int a, int c) {
        for (int i = 0; i < N; i++) {
            if (grid[a][i] == c) {
                return false;
            }
        }
        return true;
    }

    private boolean Rule2(int b, int c) {
        for (int i = 0; i < N; i++) {
            if (grid[i][b] == c) {
                return false;
            }
        }
        return true;
    }

    private boolean Rule3(int a, int b, int c) {
        int d = a % SIZE;
        int e = b % SIZE;
        for (int i = (a - d); i < (a - d + SIZE); i++) {
            for (int j = (b - e); j < (b - e + SIZE); j++) {
                if (grid[i][j] == c) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean Rule4(int a, int b, int c) {
        if ((a - 1 >= 0) && (b - 1) >= 0) {
            if (grid[a - 1][b - 1] == c) {
                return false;
            }
        }
        if ((a - 1 >= 0) && (b + 1 < N)) {
            if (grid[a - 1][b + 1] == c) {
                return false;
            }
        }

        if ((a + 1) < N && (b - 1) >= 0) {
            if (grid[a + 1][b - 1] == c) {
                return false;
            }
        }
        if ((a + 1) < N && (b + 1) < N) {
            if (grid[a + 1][b + 1] == c) {
                return false;
            }
        }

        return true;
    }

    private boolean Rule5(int a, int b, int c) {
        if ((a - 2) >= 0 && (b - 1) >= 0) {
            if (grid[a - 2][b - 1] == c) {
                return false;
            }
        }
        if ((a - 1) >= 0 && (b - 2) >= 0) {
            if (grid[a - 1][b - 2] == c) {
                return false;
            }
        }
        if ((a + 1) < N && (b - 2) >= 0) {
            if (grid[a + 1][b - 2] == c) {
                return false;
            }
        }
        if ((a + 2) < N && (b - 1) >= 0) {
            if (grid[a + 2][b - 1] == c) {
                return false;
            }
        }
        if ((a + 2) < N && (b + 1) < N) {
            if (grid[a + 2][b + 1] == c) {
                return false;
            }
        }
        if ((a + 1) < N && (b + 2) < N) {
            if (grid[a + 1][b + 2] == c) {
                return false;
            }
        }
        if ((a - 1) >= 0 && (b + 2) < N) {
            if (grid[a - 1][b + 2] == c) {
                return false;
            }
        }
        if ((a - 2) >= 0 && (b + 1) < N) {
            if (grid[a - 2][b + 1] == c) {
                return false;
            }
        }
        return true;
    }

    private boolean Rule6(int a, int b, int c) {
        int y = a;
        int z = b;
        if ((c) != N) {
            return true;
        }
        while ((z + 1) < N && (y + 1) < N) {
            z = z + 1;
            y = y + 1;
            if (grid[y][z] == c) {
                return false;
            }
        }
        y = a;
        z = b;
        while ((z + 1) < N && (y - 1) >= 0) {
            z = z + 1;
            y = y - 1;
            if (grid[y][z] == c) {
                return false;
            }
        }
        y = a;
        z = b;
        while ((z - 1) >= 0 && (y - 1) >= 0) {
            z = z - 1;
            y = y - 1;
            if (grid[y][z] == c) {
                return false;
            }
        }
        y = a;
        z = b;
        while ((z - 1) >= 0 && (y + 1) < N) {
            z = z - 1;
            y = y + 1;
            if (grid[y][z] == c) {
                return false;
            }
        }

        return true;
    }

    //
    private boolean solveSudoku(int v, int h) {
        h=h+1;
        if (h == N) {
            h = 0;
            v++;
        }
        if (v == N) {
            return true;
        }
        if (grid[v][h] != 0) {
            return solveSudoku(v, h );
        }
       for (int i = 1; i <= N; i++) {

            if (!(knightRule) && !(kingRule) && !(queenRule)) {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            } else if (knightRule && !(kingRule) && !(queenRule)) {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i) && Rule5(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            } else if (!(knightRule) && kingRule && !(queenRule)) {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i) && Rule4(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            } else if (!(knightRule) && !(kingRule) && queenRule) {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i) && Rule6(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            } else if (!(knightRule) && kingRule && queenRule) {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i) && Rule4(v, h, i) && Rule6(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            } else if (knightRule && !(kingRule) && queenRule) {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i) && Rule5(v, h, i) && Rule6(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            } else if (knightRule && kingRule && !(queenRule)) {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i) && Rule4(v, h, i) && Rule5(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            } else {
                if ((Rule1(v, i) && Rule2(h, i) && Rule3(v, h, i) && Rule4(v, h, i) && Rule5(v, h, i) && Rule6(v, h, i))) {
                    grid[v][h] = i;
                    if (solveSudoku(v, h )) {
                        return true;
                    }
                }
            }

        }


        grid[v][h] = 0;
        return false;

    }


    public void solve(boolean allSolutions) {
        solveSudoku(0, -1);

    }

	/*****************************************************************************/
	/* NOTE: YOU SHOULD NOT HAVE TO MODIFY ANY OF THE METHODS BELOW THIS LINE. */
	/*****************************************************************************/

	/* Default constructor.  This will initialize all positions to the default 0
	 * value.  Use the read() function to load the Sudoku puzzle from a file or
	 * the standard input. */
	public ChessSudoku( int size ) {
		SIZE = size;
		N = size*size;

		grid = new int[N][N];
		for( int i = 0; i < N; i++ ) 
			for( int j = 0; j < N; j++ ) 
				grid[i][j] = 0;
	}


	/* readInteger is a helper function for the reading of the input file.  It reads
	 * words until it finds one that represents an integer. For convenience, it will also
	 * recognize the string "x" as equivalent to "0". */
	static int readInteger( InputStream in ) throws Exception {
		int result = 0;
		boolean success = false;

		while( !success ) {
			String word = readWord( in );

			try {
				result = Integer.parseInt( word );
				success = true;
			} catch( Exception e ) {
				// Convert 'x' words into 0's
				if( word.compareTo("x") == 0 ) {
					result = 0;
					success = true;
				}
				// Ignore all other words that are not integers
			}
		}

		return result;
	}


	/* readWord is a helper function that reads a word separated by white space. */
	static String readWord( InputStream in ) throws Exception {
		StringBuffer result = new StringBuffer();
		int currentChar = in.read();
		String whiteSpace = " \t\r\n";
		// Ignore any leading white space
		while( whiteSpace.indexOf(currentChar) > -1 ) {
			currentChar = in.read();
		}

		// Read all characters until you reach white space
		while( whiteSpace.indexOf(currentChar) == -1 ) {
			result.append( (char) currentChar );
			currentChar = in.read();
		}
		return result.toString();
	}


	/* This function reads a Sudoku puzzle from the input stream in.  The Sudoku
	 * grid is filled in one row at at time, from left to right.  All non-valid
	 * characters are ignored by this function and may be used in the Sudoku file
	 * to increase its legibility. */
	public void read( InputStream in ) throws Exception {
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				grid[i][j] = readInteger( in );
			}
		}
	}


	/* Helper function for the printing of Sudoku puzzle.  This function will print
	 * out text, preceded by enough ' ' characters to make sure that the printint out
	 * takes at least width characters.  */
	void printFixedWidth( String text, int width ) {
		for( int i = 0; i < width - text.length(); i++ )
			System.out.print( " " );
		System.out.print( text );
	}


	/* The print() function outputs the Sudoku grid to the standard output, using
	 * a bit of extra formatting to make the result clearly readable. */
	public void print() {
		// Compute the number of digits necessary to print out each number in the Sudoku puzzle
		int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

		// Create a dashed line to separate the boxes 
		int lineLength = (digits + 1) * N + 2 * SIZE - 3;
		StringBuffer line = new StringBuffer();
		for( int lineInit = 0; lineInit < lineLength; lineInit++ )
			line.append('-');

		// Go through the grid, printing out its values separated by spaces
		for( int i = 0; i < N; i++ ) {
			for( int j = 0; j < N; j++ ) {
				printFixedWidth( String.valueOf( grid[i][j] ), digits );
				// Print the vertical lines between boxes 
				if( (j < N-1) && ((j+1) % SIZE == 0) )
					System.out.print( " |" );
				System.out.print( " " );
			}
			System.out.println();

			// Print the horizontal line between boxes
			if( (i < N-1) && ((i+1) % SIZE == 0) )
				System.out.println( line.toString() );
		}
	}


	/* The main function reads in a Sudoku puzzle from the standard input, 
	 * unless a file name is provided as a run-time argument, in which case the
	 * Sudoku puzzle is loaded from that file.  It then solves the puzzle, and
	 * outputs the completed puzzle to the standard output. */
	public static void main( String args[] ) throws Exception {
		InputStream in = new FileInputStream("veryEasy3x3.txt");

		// The first number in all Sudoku files must represent the size of the puzzle.  See
		// the example files for the file format.
		int puzzleSize = readInteger( in );
		if( puzzleSize > 100 || puzzleSize < 1 ) {
			System.out.println("Error: The Sudoku puzzle size must be between 1 and 100.");
			System.exit(-1);
		}

		ChessSudoku s = new ChessSudoku( puzzleSize );
		
		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;
		
		// read the rest of the Sudoku puzzle
		s.read( in );

		System.out.println("Before the solve:");
		s.print();
		System.out.println();

		// Solve the puzzle by finding one solution.
		s.solve(false);

		// Print out the (hopefully completed!) puzzle
		System.out.println("After the solve:");
		s.print();
	}
}

