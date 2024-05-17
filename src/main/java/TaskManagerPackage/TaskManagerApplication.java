/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Caleb & miguel
 */
//              tasks.txt file
//Formatted
//<task name> <date created> <complete> <type>
public class TaskManagerApplication {

    public static void main(String[] args) {

        TaskCreator tc = new TaskCreator();
        TaskManagerController tmc = new TaskManagerController();

        try ( BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter '?' to see list of commands.");
            System.out.println("Enter 'exit' to end session.");
            System.out.println("Max Name Length: " + tc.getNameLength());
            String userInput;
            do {
                System.out.print("> ");
                System.out.print("");
                System.out.flush();
                userInput = reader.readLine().trim();
                if (!userInput.contentEquals(" ")) {
                    tmc.processCommand(userInput);
                }
            } while (!userInput.equalsIgnoreCase("exit"));
        } catch (IOException e) {
            System.out.println("Fatal Error :(");
        }
    }
}
