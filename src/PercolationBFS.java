import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{

    /**
     * Initialize myGrid of size (size x size)
     * @param size - size of myGrid
     */
    public PercolationBFS (int size){
        super(size);
    }

    @Override
    /**
     * Uses breadth-first search in order to mark cells as FULL
     * @param row is the row of cell
     * @param col is the column of cell
     */
    protected void dfs(int row, int  col){
    	
        //Check if cell is in bounds
        if (!inBounds(row, col)) return;
        
        //Check if cell is Full or isn't open
        if (isFull(row, col) || !isOpen(row, col)) return;
        
        //Mark cell as FULL
        myGrid[row][col] = FULL;
        int [] rowDev = {-1, 1, 0, 0};
        int [] colDev = {0, 0, -1, 1};
        
        //Create queue
        Queue<Integer> queue = new LinkedList<>();
        int value = (row * myGrid.length) + col;
        
        //Add mapped value of cell to queue
        queue.add(value);
        
        //Check four neighbors of cell and if they are open and not full then mark them as FULL and add to queue
        while (queue.size() != 0){
        	
            Integer temp = queue.remove();
            
            for (int i = 0; i < rowDev.length; i++){
            	
                int rowtemp = (temp / myGrid.length) + rowDev[i];
                int coltemp = (temp % myGrid.length) + colDev[i];
                
                if (inBounds(rowtemp, coltemp)){
                	
                    if (isOpen(rowtemp, coltemp) && !isFull(rowtemp, coltemp)){
                    	
                        myGrid[rowtemp][coltemp] = FULL;
                        queue.add((rowtemp * myGrid.length) + coltemp);
                        
                    }
                }
            }
        }
    }
}
