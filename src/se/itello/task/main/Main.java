/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.task.main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import se.itello.task_main.registration.DataFileRegistrator;

/**
 *
 * @author Furhoff
 */
public class Main {

    public static void main(String[] args) throws IOException {
        DataFileRegistrator dfr = new DataFileRegistrator();
        
        List<Path> toRegister = new ArrayList();
        
        toRegister.add( Paths.get("src/Exempelfil_betalningsservice.txt") );
        toRegister.add( Paths.get("src/Exempelfil_inbetalningstjansten.txt") );
        toRegister.add( Paths.get("src/Exempelfil_felformat.txt") );
        
        
        for (Path path : toRegister) {
            try{
                System.out.println( "\nMain class Try to register: " + path.getFileName() );
                dfr.register(path);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            
        }
        
    }
    
}
