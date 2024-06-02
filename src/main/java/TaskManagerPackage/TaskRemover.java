/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Caleb
 */
public class TaskRemover {

    private final TaskPrinter tp = new TaskPrinter();
    private final Validator iv = new InputValidator();
    private final TaskFileManager tfm = new TaskFileManager();
    private final Scanner scan = new Scanner(System.in);
    private final DBManager dbm = new DBManager();

    public TaskRemover(Printer printer, Validator validator, TaskFileManager taskFileManager) {
    }

    public TaskRemover() {

    }

    //USED WITH GUI - REMOVES TASK FROM DB
    protected void removeTaskFromDB(int taskId) {
        dbm.remove(taskId);
    }

    protected void removeAllTasksFromDB() {
        dbm.removeAll();
    }

    //ALL METHODS BELOW ARE USED WITH CONSOLE.
    private int removeTaskDialogueDB() {
        int taskNumber;
        int totalTasks;
        if (dbm.isEmpty()) {
            System.out.println("No Tasks to Remove!");
        } else {
            totalTasks = tp.printAllTasksFromDB();
            System.out.println("Total Tasks: " + totalTasks);
            String userInput;
            System.out.println("Select the Task Number (e.g. 1,2,3..) to Remove: ");
            System.out.print("> ");
            userInput = scan.nextLine().trim();

            // Validate userInput
            while (true) {
                if (userInput.equalsIgnoreCase("exit")) {
                    return -1;
                }

                if (!iv.validateNumber(userInput)) {
                    System.out.println("Incorrect Input.");
                    System.out.println("Please Select a Task Number: ");
                    System.out.print("> ");
                    userInput = scan.nextLine().trim();
                    continue;
                }

                taskNumber = Integer.parseInt(userInput);

                if (!dbm.exists(taskNumber)) {
                    System.out.println("Task Not Found! Double Check Task Exists or Check Task Number.");
                    System.out.print("> ");
                    userInput = scan.nextLine().trim();
                } else {
                    return taskNumber;
                }
            }
        }
        return -1;
    }

    private boolean removeAllDialogueDB() {
        System.out.println("Are you sure you want to remove all Tasks?");
        System.out.println("(Y) OR (N)");
        System.out.println("> ");
        String userInput = scan.nextLine().trim();
        if (!userInput.equalsIgnoreCase("Y") && !userInput.equalsIgnoreCase("N")) { // keeps looping until user enters "Y" or "N" or exits
            while (true) {
                if (userInput.equalsIgnoreCase("exit")) {
                    return false;
                }
                System.out.println("(Y) OR (N)");
                System.out.println("> ");
                userInput = scan.nextLine().trim();
                if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N")) {
                    break;
                }
            }
        }
        if (userInput.equalsIgnoreCase("Y")) {
            return true;
        } else {
            System.out.println("Cancelled.");
        }
        return false;
    }

    //USED WITH CONSOLE
    protected void removeTaskFromConsoleDB() {
        int taskToRemove = removeTaskDialogueDB();
        dbm.remove(taskToRemove);
    }

    //USED WITH CONSOLE
    protected void removeAllFromConsoleDB() {
        if (removeAllDialogueDB()) {
            dbm.removeAll();
        }
    }

    //obselete -- updated to work with DB
    protected void removeTask() {
//        //removeTaskDialogue gets the task number from the user and it's used here.
//        ArrayList<String> newTaskList;
//        int taskNumber = removeTaskDialogue();
//        if (taskNumber >= 1) {
//            //creates ArrayList of tasks except the task number from removeTaskDialogue
//            newTaskList = tfm.readTasksExcept(taskNumber);
//            //overwrites text file with contents of arrayList
//            tfm.writeTasks(newTaskList);
//            System.out.println("Task Removed Successfully.");
//        }
    }

    //obselete -- updated to work with DB
    protected void removeAll() {
//        if (removeAllDialogue()) {
//            //overwrites text file with empty arraylist
//            tfm.writeTasks(new ArrayList<>());
//            System.out.println("Tasks Removed Successfully.");
//        }
    }

    //obselete -- updated to work with DB
    private int removeTaskDialogue() {
//        int taskNumber;
//        int totalTasks;
//        if (iv.isEmpty()) {
//            System.out.println("No Tasks to Remove!");
//        } else {
//            totalTasks = tp.printDisplayAll();
//            String userInput;
//            System.out.println("Select the Task Number (e.g. 1,2,3..) to Remove: ");
//            System.out.print("> ");
//            userInput = scan.nextLine().trim();
//
//            // Validate userInput
//            while (true) {
//                if (userInput.equalsIgnoreCase("exit")) {
//                    return -1;
//                }
//
//                if (!iv.validateNumber(userInput)) {
//                    System.out.println("Incorrect Input.");
//                    System.out.println("Please Select a Task Number: ");
//                    System.out.print("> ");
//                    userInput = scan.nextLine().trim();
//                    continue;
//                }
//
//                taskNumber = Integer.parseInt(userInput);
//
//                if (taskNumber <= 0 || taskNumber > totalTasks) {
//                    System.out.println("Task Not Found! Double Check Task Exists or Check Task Number.");
//                    System.out.print("> ");
//                    userInput = scan.nextLine().trim();
//                } else {
//                    return taskNumber;
//                }
//            }
//        }
        return -1;
    }

    //obselete -- updated to work with DB
    private boolean removeAllDialogue() {
//        //confirms with user if they want to remove all tasks.
//        if (iv.isEmpty() == true) {
//            System.out.println("No Tasks to Remove!");
//            return false;
//        }
//        System.out.println("Are you sure you want to remove all Tasks?");
//        System.out.println("(Y) OR (N)");
//        System.out.println("> ");
//        String userInput = scan.nextLine().trim();
//        if (!userInput.equalsIgnoreCase("Y") && !userInput.equalsIgnoreCase("N")) { // keeps looping until user enters "Y" or "N" or exits
//            while (true) {
//                if (userInput.equalsIgnoreCase("exit")) {
//                    return false;
//                }
//                System.out.println("(Y) OR (N)");
//                System.out.println("> ");
//                userInput = scan.nextLine().trim();
//                if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N")) {
//                    break;
//                }
//            }
//        }
//        if (userInput.equalsIgnoreCase("Y")) {
//            return true;
//        } else {
//            System.out.println("Cancelled.");
//        }
        return false;
    }
}
