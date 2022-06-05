/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shanedevers.javasudoku;
import java.util.*;

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
        int failCount = 0;
        for (int i = 0; i < contents.length; i++) {
            for (int j = 0; j < contents[i].length; j++) {
                int choice = getValidChoice(i, j);
                if (choice > 0) {
                    contents[i][j] = choice;
                } else {
                    contents[i] = new int[9];
                    failCount++;
                    if (failCount < 10) {
                        i--;
                    } else {
                        i = -1;
                        contents = new int[9][9];
                    }
                    j = -1;
                    break;
                }
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
    
    public int getValidChoice(int i, int j) {
        ArrayList<Integer> valid = new ArrayList<Integer>();
        for (int k = 1; k < 10; k++) {
            valid.add(k);
        }
        for (int k = 1; k < 10; k++) {
            for (int l = 0; l < contents[i].length; l++) {
                if (valid.contains(k) && contents[i][l] == k && j != l) {
                    valid.remove(Integer.valueOf(k));
                }
            }
        }
        for (int k = 1; k < 10; k++) {
            for (int l = 0; l < contents[i].length; l++) {
                if (valid.contains(k) && contents[l][j] == k && i != l) {
                    valid.remove(Integer.valueOf(k));
                }
            }
        }
        int lLow = (i / 3) * 3;
        int mLow = (j / 3) * 3;
        for (int k = 1; k < 10; k++) {
            for (int l = lLow; l < lLow + 3; l++) {
                for (int m = mLow; m < mLow + 3; m++) {
                    if (valid.contains(k) && contents[l][m] == k && (i != l || j != m)) {
                        valid.remove(Integer.valueOf(k));
                    }
                }
            }       
        }
        Random rand = new Random();
        int choice = -1;
        if (valid.size() > 0) {
            int index = rand.nextInt(valid.size());
            choice = valid.get(index);
        }
        return choice;
    }
    
    public int getContents(int i, int j) {
        return contents[i][j];
    }
    
    public int getSize() {
        return size;
    }
    
}
