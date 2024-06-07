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

    public InputValidator() {

    }

    /**
     * Validates the given name based on the specified minimum and maximum
     * length constraints.
     *
     * @param name the name to be validated
     * @param max the maximum allowed length for the name
     * @param min the minimum allowed length for the name
     * @return -1 if the name is empty, 0 if the name is too short, 1 if the
     * name is too long, 2 if the name is within the valid length range
     */
    @Override
    public int validateTaskName(String name, int max, int min) {
        //-1 == name is empty
        //0 == name is too short
        //1 == name is too long
        //2 == name is ok
        int nameLength = name.length();
        if (name.isEmpty()) {
            return -1;
        }
        if (nameLength >= max) {
            return 1;
        } else if (nameLength < min) {
            return 0;
        }
        return 2;
    }

//validates if input is Alphabetical
    /*
    BELOW METHOD IS FOR CONSOLE INPUT ONLY
    HAS NO INTERACTION WITH GUI
     */
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
    /*
    BELOW METHOD IS FOR CONSOLE INPUT ONLY
    HAS NO INTERACTION WITH GUI
     */
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
    /*
   OBSELETE
     */
//    @Override
//    public String validateTaskExists(String userInput, int totalTasks) {
//        Scanner scanKbd = new Scanner(System.in);
//
//        while (totalTasks < Integer.parseInt(userInput) || Integer.parseInt(userInput) <= 0) {
//            if (userInput.equalsIgnoreCase("exit")) {
//                return "exit";
//            }
//            System.out.println("Task Not Found! Double Check Task Exists or Check Task Number.");
//            System.out.print("> ");
//            userInput = scanKbd.nextLine().trim();
//            while (!validateNumber(userInput) || userInput.equalsIgnoreCase("exit")) {
//                if (userInput.equalsIgnoreCase("exit")) {
//                    return "exit";
//                }// must also make sure the task exists.
//                System.out.println("Incorrect Input.");
//                System.out.println("Please Select a Task Number: ");
//                System.out.print("> ");
//                userInput = scanKbd.nextLine().trim();
//            }
//        }
//
//        return userInput;
//    }

    /*
   OBSELETE
     */
    //simple method to return boolean result if the tasks.txt file is empty or not.
//    @Override
//    public boolean isEmpty() {
//        return tfm.readTasks() == null;
//    }
}
