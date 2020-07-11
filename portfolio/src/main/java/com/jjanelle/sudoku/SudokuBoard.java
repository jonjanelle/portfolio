package com.jjanelle.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuBoard {
    private final int size = 9;
    private final int minorSize = 3;
    private int nRemoved;
    private int[][] board;
    
    public SudokuBoard() {
        
    }
    
    public SudokuBoard(int nRemoved) {
        this.board = new int[size][size];
        this.nRemoved = nRemoved;
    }
    
    
    public void setValue(int row, int col, int value) {
        if (row < size && col < size) {
            board[row][col] = value;
        }
    }
    
    public int getValue(int row, int col) throws IllegalArgumentException {
        if (row < size && col < size) {
            return board[row][col];
        }
        
        throw new IllegalArgumentException("Row or column value out of range");  
    }
    
    public boolean isPlacementValid(int row, int col, int value) {
        return validInRow(row, value) && validInCol(col, value) && validInBox(row-row%minorSize, col-col%minorSize, value);
    }
    
    public boolean isSolved() {
        for (int i = 0; i < size; i++) {
            if (!(isRowSolved(i) || isColSolved(i)))
                return false;
        }
        
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (!isBoxSoved(i*3, j*3))
                    return false;
        
        return true;
    }
    
    public int getNRemoved() {
        return nRemoved;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getMinorSize() {
        return minorSize;
    }
    
    public int[][] getBoard() {
        return board;
    }
    
    public void setBoard(int[][] board) {
        this.board = board;
    }
    
    private boolean isRowSolved(int row) {
        Set<Integer> seenValues = new HashSet<>();
        for (int col = 0; col < size; col++) {
            if (board[row][col] < 1 || board[row][col] > size || seenValues.contains(board[row][col]))
                return false;
            seenValues.add(board[row][col]);
        }
        return true;
    }
    
    private boolean isColSolved(int col) {
        Set<Integer> seenValues = new HashSet<>();
        for (int row = 0; row < size; row++) {
            if (board[row][col] < 1 || board[row][col] > size || seenValues.contains(board[row][col]))
                return false;
            seenValues.add(board[row][col]);
        }
        return true;
    }
    
    private boolean isBoxSoved(int rowStart, int colStart) {
        Set<Integer> seenValues = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = board[rowStart+i][colStart+j];
                if (value < 1 || value > size || seenValues.contains(value)) 
                    return false; 
                seenValues.add(value);
            }
        }
        return true; 
    }
    
    private boolean validInRow(int row,int value) 
    { 
        for (int j = 0; j < size; j++) 
           if (board[row][j] == value) 
                return false; 
        return true; 
    } 
  
     
    private boolean validInCol(int col,int value) 
    { 
        for (int i = 0; i < size; i++) 
            if (board[i][col] == value) 
                return false; 
        return true; 
    } 
    
    private boolean validInBox(int rowStart, int colStart, int value) 
    { 
        for (int i = 0; i < 3; i++) 
            for (int j = 0; j < 3; j++) 
                if (board[rowStart+i][colStart+j] == value) 
                    return false; 
  
        return true; 
    } 
}
