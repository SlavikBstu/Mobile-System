package com.example.apache.shell_linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Apache on 26.02.2017.
 */

public class ShellExecuter {

    public ShellExecuter(){

    }

    public String Executer(String command){
        StringBuffer output = new StringBuffer();

        Process process;
        try{
            process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null){
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String response = output.toString();
        return response;
    }
}
