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
public class TaskUpdater {

    private final Validator iv;
    private final Printer tp;
    private final TaskFileManager tfm;

    public TaskUpdater(Validator validator, Printer printer, TaskFileManager taskFileManager) {
        this.iv = validator;
        this.tp = printer;
        this.tfm = taskFileManager;
    }

    protected void updateCompleteStatus() {
        Scanner scanKbd = new Scanner(System.in);
        if (tfm.readTasks() == null) {
            System.out.println("No Tasks to Update!"); // checks if tasks.txt is empty, by checking if first line is null.
            return;
        }
        //validates user input
        //ensures user enters a number
        int totalTasks = tp.printDisplayAll();
        System.out.println("Select task number (e.g. 1,2,3) to update to complete");
        System.out.print(">");
        String userInput = scanKbd.nextLine();
        while (!iv.validateNumber(userInput) || userInput.equalsIgnoreCase("exit")) {
            if (userInput.equalsIgnoreCase("exit")) {
                return;
            }
            System.out.println("Please Select a Task Number");
            userInput = scanKbd.nextLine();
        }
        //validates tasks exists
        userInput = iv.validateTaskExists(userInput, totalTasks);
        int lineToUpdate = Integer.parseInt(userInput);

        //validates user input is between 1 and 3
        System.out.println(" (1) for name \n (2) for status \n (3) for type");
        userInput = scanKbd.nextLine();
        while (!iv.validateNumber(userInput) || Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > 3) {
            if (userInput.equalsIgnoreCase("exit")) {
                return;
            }
            System.out.println("Incorrect input");
            System.out.println("(1) for name \n (2) for status \n (3) for type");
            userInput = scanKbd.nextLine();
        }

        //saves tasks.txt to arrayList
        ArrayList<String> taskListToUpdate = tfm.readTasks();

        //temporary arraylist
        ArrayList<String> updatedTaskList = new ArrayList<>();
        int current;
        switch (Integer.parseInt(userInput)) {
            //update task name
            case 1:
                System.out.println("Enter Name: ");
                System.out.print("> ");
                String newName = scanKbd.nextLine(); // Assuming scanKbd is your Scanner for keyboard input
                current = 0;
                for (String task : taskListToUpdate) {
                    current++;
                    //if task number selected == current position, update the name of the task at that position
                    if (current == lineToUpdate) {
                        String[] parts = task.split("\\|");
                        if (parts.length >= 1) {
                            parts[0] = newName;
                            task = String.join("|", parts);
                        }
                    }
                    //keep constructing the new task list with the tasks. and also add the updated task name
                    updatedTaskList.add(task);
                }
                System.out.println("Name Updated.");
                // Now updatedTaskList contains the updated tasks with the new name
                // Write updatedTaskList back to the file or perform any further actions
                break;

            //update task completed status
            case 2:
                current = 0;
                for (String task : taskListToUpdate) {
                    current++;
                    //similar function to above, if current matches the task number, change the status to opposite.
                    if (current == lineToUpdate) {
                        String[] parts = task.split("\\|");
                        if (parts.length == 4 && parts[2].equalsIgnoreCase("false")) {
                            parts[2] = "true";
                        } else if (parts.length == 4 && parts[2].equalsIgnoreCase("true")) {
                            parts[2] = "false";
                        }
                        task = String.join("|", parts);
                    }
                    updatedTaskList.add(task);
                }
                System.out.println("Status Updated.");
// Now updatedTaskList contains the updated tasks
// Write updatedTaskList back to the file or perform any further actions
                break;

            //update task type - work/home
            case 3:
                current = 0;
                for (String task : taskListToUpdate) {
                    current++;
                    if (current == lineToUpdate) {
                        String[] parts = task.split("\\|");
                        if (parts.length >= 4) {
                            String taskType = parts[3].trim();
                            if (taskType.equalsIgnoreCase("work")) {
                                parts[3] = "Home";
                            } else if (taskType.equalsIgnoreCase("home")) {
                                parts[3] = "Work";
                            }
                            task = String.join("|", parts);
                        }
                    }
                    updatedTaskList.add(task);
                }
                System.out.println("Type Updated.");
                break;
            default:
                break;
        }
//finally writes updated list to tasks.txt
        tfm.writeTasks(updatedTaskList);
    }
}
