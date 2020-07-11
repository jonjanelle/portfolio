package com.jjanelle.sudoku;

//Generation approach taken from  https://www.geeksforgeeks.org/program-sudoku-generator/
public class SudokuGenerator {

    private final int nBlanks;
    private SudokuBoard board;
    
    public SudokuGenerator(int nRemoved) 
    {
        this.nBlanks = nRemoved;
    } 
  
    public SudokuBoard generate() 
    { 
        board = new SudokuBoard(nBlanks);
        // Fill the three 3x3 boxes in the main diagonal (since they are independent of each other, randomly fill)
        fillDiagonal(); 
        // Fill remaining 3x3 boxes
        fillRemaining(0, board.getMinorSize()); 
        // Remove digits to complete the puzzle
        removeDigits(); 
        
        return board;
    } 
  
    // Fill the main diagonal of 3x3 squares randomly since they are independent 
    private void fillDiagonal() 
    {
        for (int i = 0; i < board.getSize(); i=i+board.getMinorSize())
            fillBox(i, i); 
    } 
  
    // Fill a 3 x 3 matrix. 
    private void fillBox(int row,int col) 
    { 
        int num; 
        for (int i=0; i < board.getMinorSize(); i++) 
        { 
            for (int j=0; j<board.getMinorSize(); j++) 
            { 
                do
                { 
                    num = randInt(board.getSize()); 
                } while (!board.isPlacementValid(row, col, num)); 
  
                board.setValue(row+i, col+j, num); 
            } 
        } 
    } 
  
    // generate a random int [0, maxValue]
    private int randInt(int maxValue) 
    { 
        return (int)Math.floor((Math.random()*maxValue+1)); 
    } 

    private boolean fillRemaining(int i, int j) 
    { 
        if (j>=board.getSize() && i<board.getSize()-1) 
        { 
            i = i + 1; 
            j = 0; 
        } 
        if (i>=board.getSize() && j>=board.getSize()) 
            return true; 
  
        if (i < board.getMinorSize()) 
        { 
            if (j < board.getMinorSize()) 
                j = board.getMinorSize(); 
        } 
        else if (i < board.getSize()-board.getMinorSize()) 
        { 
            if (j==(int)(i/board.getMinorSize())*board.getMinorSize()) 
                j =  j + board.getMinorSize(); 
        } 
        else
        { 
            if (j == board.getSize() - board.getMinorSize()) 
            { 
                i = i + 1; 
                j = 0; 
                if (i >= board.getSize()) 
                    return true; 
            } 
        } 
  
        for (int num = 1; num <= board.getSize(); num++) 
        { 
            if (board.isPlacementValid(i, j, num)) 
            { 
                board.setValue(i, j, num);
                if (fillRemaining(i, j+1)) 
                    return true; 
  
                board.setValue(i, j, 0);
            } 
        } 
        return false; 
    } 
  
    // clear cells to create puzzle
    private void removeDigits() 
    { 
        int count = board.getNRemoved(); 
        while (count != 0) 
        { 
            int cellId = randInt(board.getSize()*board.getSize()-1); 
  
            int i = (cellId/board.getSize()); 
            int j = cellId % 9; 
            if (j != 0)
                j = j - 1;
  
            if (board.getValue(i, j) != 0) 
            { 
                count--; 
                board.setValue(i, j, 0); 
            } 
        } 
    } 
}
