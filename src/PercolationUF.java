public class PercolationUF implements IPercolate {

    boolean [][] myGrid;
    int myOpenCount = 0;
    IUnionFind myFinder = new QuickUWPC();
    private final int VTOP;
    private final int VBOTTOM;

    /**
     * Initialize instance variables
     * @param size is the size of myGrid
     * @param finder is a IUnionFind object
     */
    public PercolationUF(int size, IUnionFind finder){
    	
        VTOP = size * size;
        VBOTTOM = (size * size) + 1;
        myGrid = new boolean[size][size];
        finder.initialize((size * size) + 2);
        myFinder = finder;
        
    }

    @Override
    /**
     * Return whether or not cell is open
     * @param row is the row of cell
     * @param col is the column of cell
     * @return boolean value of myGrid[row][col]
     */
    public boolean isOpen(int row, int col){
        if(!inBounds(row, col)){
            throw new IndexOutOfBoundsException();
        }
        return myGrid[row][col];
    }

    @Override
    /**
     * Return whether or not cell is full
     * @param row is the row of cell
     * @param col is the column of cell
     * @return boolean value of whether or not cell is connected to VTOP
     */
    public boolean isFull(int row, int col){
        if(!inBounds(row, col)){
            throw new IndexOutOfBoundsException();
        }
        return myFinder.connected((row * myGrid.length + col), VTOP);
    }

    @Override
    /**
     * Return whether or not grid percolates
     * @return boolean value of whether or not grid percolates
     */
    public boolean percolates(){
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    /**
     * Return number of cells marked as OPEN
     * @return myOpenCount
     */
    public int numberOfOpenSites(){
        return myOpenCount;
    }

    @Override
    /**
     * Marks cell as open and connects with open neighbors
     * @param row - row of cell
     * @param col - column of cell
     */
    public void open(int row, int col){
        //Check if cell is in bounds
        if(!inBounds(row, col)){
            throw new IndexOutOfBoundsException();
        }
        //Check if cell is open
        if (!isOpen(row, col)){
            //Set cell to open and increment myOpenCount
            myGrid[row][col] = true;
            myOpenCount++;
            //Connect cell with VTOP if it's in top row
            if(row == 0){
                myFinder.union((row * myGrid.length) + col, VTOP);
            }
            //Connect cell with VBOTTOM if it's in bottom row
            if(row == myGrid.length - 1){
                myFinder.union((row * myGrid.length) + col, VBOTTOM);
            }
            int [] rowDelta = {-1, 1, 0, 0};
            int [] colDelta = {0, 0, -1, 1};
            //Check neighbors, and connect cell with any open neighbors
            for (int i = 0; i < rowDelta.length; i++){
                int rowtemp = row + rowDelta[i];
                int coltemp = col + colDelta[i];
                if (inBounds(rowtemp, coltemp)){
                    if (isOpen(rowtemp, coltemp)){
                        myFinder.union((row * myGrid.length) + col, (rowtemp * myGrid.length) + coltemp);
                    }
                }
            }
        }
    }

    /**
     * Check if cell is in bounds
     * @param row - row of cell
     * @param col - column of cell
     * @return whether or not cell is in bounds
     */
    private boolean inBounds (int row, int col){
        if(row < 0 || row >= myGrid.length){
            return false;
        }
        if(col < 0 || col >= myGrid[0].length){
            return false;
        }
        return true;
    }
}
