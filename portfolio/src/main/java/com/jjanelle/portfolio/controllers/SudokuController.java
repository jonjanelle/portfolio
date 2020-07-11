package com.jjanelle.portfolio.controllers;

import com.jjanelle.sudoku.SudokuBoard;
import com.jjanelle.sudoku.SudokuGenerator;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuController {

    @CrossOrigin
    @GetMapping("/sudoku/getBoard")
    SudokuBoard getBoard(@RequestParam int nRemoved) {
        SudokuBoard board = new SudokuGenerator(nRemoved).generate();
        return board;
    }
    
    @PostMapping("/sudoku/checkBoard")
    boolean checkBoard(@RequestBody SudokuBoard board) {
        
        return board.isSolved();
    }
}
