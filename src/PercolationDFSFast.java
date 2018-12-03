
public class PercolationDFSFast extends PercolationDFS{

    /**
     * Initialize myGrid of size (size x size)
     * @param size - size of myGrid
     */
    public PercolationDFSFast(int size){
        super(size);
    }

    @Override
    /**
     * Determine of cell should be marked as full and if so, call dfs(row, col)
     * @param row is the row of cell
     * @param col is the column of cell
     */
    protected void updateOnOpen(int row, int col) {
        
    	//If cell is in top row it should be marked as full
        if (row == 0){
            dfs(row,col);
            return;
        }
        
        //Check if any of its four neighbors are full; if at least one of them is, then cell should be marked as full
        else {
        	
            int [] rowDev = {-1, 1, 0, 0};
            int [] colDev = {0, 0, -1, 1};
            
            for (int i = 0; i < rowDev.length; i++){
            	
                int rowtemp = row + rowDev[i];
                int coltemp = col + colDev[i];
                
                if (inBounds(rowtemp, coltemp)){
                	
                    if (isFull(rowtemp, coltemp)){
                        dfs(row, col);
                        return;
                    }
                    
                }
                
            }
        }
    }
}
