/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccloader;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abd-Elmalek
 */
public class MainClass {
    
    public static void main(String[] args) {
        CCloader cCloader = new CCloader(MainClass.class.getClassLoader());
        Employee employee = new Employee();
        System.out.println(employee.getClass().getClassLoader());
        System.out.println(Employee.count);
        Class EmpClass;
        try {
            EmpClass = cCloader.loadClass("ccloader.Employee");
            System.err.println(EmpClass.getClassLoader());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(Employee.count);
        new ThreadTrial();
        
        
    } 
    
    
}
