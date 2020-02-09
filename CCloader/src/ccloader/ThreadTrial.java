/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccloader;

/**
 *
 * @author Abd-Elmalek
 */
public class ThreadTrial {

    public ThreadTrial() {
           
        new Thread(()->{
              System.out.println("runnable loader "+ this.getClass().getClassLoader());
        }).start();
        
    }
    
    
    
}
