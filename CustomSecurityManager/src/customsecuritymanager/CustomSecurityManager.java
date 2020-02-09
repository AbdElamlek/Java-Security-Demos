/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customsecuritymanager;

import java.io.File;
import java.io.FilePermission;
import java.security.AccessControlContext;
import java.security.AccessController;

/**
 *
 * @author Abd-Elmalek
 */
public class CustomSecurityManager extends SecurityManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AccessControlContext con = AccessController.getContext();
        File file = new File("D:\\ITI\\Study Material\\HakerRank\\CustomSecurityManager\\src\\customsecuritymanager\\CustomSecurityManager.policy");
        System.setProperty("java.security.policy", "file:/" + file.getAbsolutePath());
        CustomSecurityManager sm = new CustomSecurityManager();
        System.setSecurityManager(sm);
        sm.checkPermission(new FilePermission("*.txt", "write"), con);
        System.out.println("Allowed!");
    }

}
