/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shanedevers.javasudoku;
import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author shane
 */
public class Grid {
    
    private int[][] contents;
    private int size;
    
    public Grid() {
        contents = new int[9][9];
        size = contents.length;
        Random rand = new Random();
        for (int i = 0; i < contents.length; i++) {
            for (int j = 0; j < contents[i].length; j++) {
                do {
                    contents[i][j] = rand.nextInt(9) + 1;
                } while (checkCollision(i,j));
            }
        }
    }
    
    public boolean checkCollision(int i, int j) {
        for (int k = 0; k < contents.length; k++) {
            if (contents[i][j] == contents[i][k] && j != k) {
                return true;
            }
        }
        for (int k = 0; k < contents.length; k++) {
            if (contents[i][j] == contents[k][j] && i != k) {
                return true;
            }
        }
        int kLow = (i / 3) * 3;
        int lLow = (j / 3) * 3;
        for (int k = kLow; k < kLow + 3; k++) {
            for (int l = lLow; l < lLow + 3; l++) {
                if (contents[i][j] == contents[k][l] && (i != k || j != l)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int getContents(int i, int j) {
        return contents[i][j];
    }
    
    public int getSize() {
        return size;
    }
    
}
