/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cupcake.presentation;

import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Malte
 */
public class CreateUserCommand extends Command {

     private static String generateHTML(String tmpl, String... args) throws Exception {
        String file = getResourceFileContents(tmpl);
        int con = 0;
        for (String arg : args) {
            file = file.replace("$" + con, arg);
            con++;
        }
        return file;
    }
    
    /*
    It is not part of the curriculum (pensum) to understand this method.
    You are more than welcome to bang your head on it though.
     */
    private static String getResourceFileContents(String fileName) throws Exception {
        //Get file from resources folder
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        URL url = classLoader.getResource(fileName);
        //File file = new File(url.getFile());
        File file = new File("/"+fileName);
        String content = new String(Files.readAllBytes(file.toPath()));
        return content;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try {
             out.println("" + generateHTML(getResourceFileContents("CreateUser.html")));
         } catch (Exception ex) {
             Logger.getLogger(CreateUserCommand.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
}
