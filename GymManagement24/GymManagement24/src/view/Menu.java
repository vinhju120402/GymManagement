/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import utils.GetInput;

public class Menu extends ArrayList<String>{
    private String title;

    public Menu(String title) {
        this.title = title;
    }
        
    public void addMenu(String option) { 
        this.add(option);   
    }

    public void printMenu() {
        System.out.println("Welcome To " + title); 
        for (String op : this) {
            System.out.println(op);
        }
    }
    
    public int getChoice() {
        return GetInput.getInt("Choose [1.." + this.size() + "]: ", 1, this.size());
    }
}