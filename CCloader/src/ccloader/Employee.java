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
public class Employee {
    public static int count = 0;
    String name;
    int id;
    long number;

    public Employee() {
        count++;
    }

    public Employee(String name, int id, long number) {
        this.name = name;
        this.id = id;
        this.number = number;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
    
    
}
