/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.util.Scanner;

/**
 *
 * @author maste
 */
public class InputValidator extends Validator {

    TaskFileManager tfm = new TaskFileManager();

    //validates if input is Alphabetical
    @Override
    public boolean validateAlphabetical(String input) {
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    //validates if input is a number
    @Override
    public boolean validateNumber(String input) {
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    //validates task exists
    //continues to prompt user for correct input until tasks the exists is found.
    //if user enters "exit" then the loop ends, user is taken back to main console
    //where they can continue to enter commands.
    @Override
    public String validateTaskExists(String userInput, int totalTasks) {
        Scanner scanKbd = new Scanner(System.in);

        while (totalTasks < Integer.parseInt(userInput) || Integer.parseInt(userInput) <= 0) {
            if (userInput.equalsIgnoreCase("exit")) {
                return "exit";
            }
            System.out.println("Task Not Found! Double Check Task Exists or Check Task Number.");
            System.out.print("> ");
            userInput = scanKbd.nextLine().trim();
            while (!validateNumber(userInput) || userInput.equalsIgnoreCase("exit")) {
                if (userInput.equalsIgnoreCase("exit")) {
                    return "exit";
                }// must also make sure the task exists.
                System.out.println("Incorrect Input.");
                System.out.println("Please Select a Task Number: ");
                System.out.print("> ");
                userInput = scanKbd.nextLine().trim();
            }
        }

        return userInput;
    }

    //simple method to return boolean result if the tasks.txt file is empty or not.
    @Override
    public boolean isEmpty() {
        return tfm.readTasks() == null;
    }
}
