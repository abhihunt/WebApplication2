/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

/**
 *
 * @author Abhishek
 */
public class RandomNumberGenerator {
    
    public static String getRendomNumber(int minimum, int maximum){
    
    System.out.print("RandomNumberGenerator ---> "+(int)(Math.random() * maximum));
    return  String.valueOf(minimum + (int)(Math.random() * maximum))    ; 
    
    
    }
    
}
