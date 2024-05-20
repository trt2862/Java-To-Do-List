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
public class TaskCreator {

    //Minimum/Maximum value incase you want to create restrictions in future.
    private final int MAX_TASK_NAME_LENGTH = 32;
    private final int MIN_TASK_NAME_LENGTH = 1;
    Task task;
    DBManager dbm = new DBManager();

    public TaskCreator() {
    }

    //returns maximum name length of a task.
    public int getNameLength() {
        return MAX_TASK_NAME_LENGTH;
    }

    protected void createTaskDB() {
        Scanner scan = new Scanner(System.in);
        //prompt user for task name
        System.out.println("Task Name? ");
        System.out.print("> ");
        String taskName = scan.nextLine().trim();
        if (taskName.equalsIgnoreCase("exit")) {
            System.out.println("Cancelled");
            return;
        }
        //check task fits parameters of name. taken to main console with error message if task name doesnt
        //suit requirements. where user can continue to enter commands.
        if (taskName.isEmpty()) {
            System.out.println("Task Name Must Contain a Value!");
        } else if (taskName.length() >= MAX_TASK_NAME_LENGTH) {
            System.out.println("Task Name is too Long!");
            System.out.println("Maximum " + MAX_TASK_NAME_LENGTH + " Characters");
        } else if (taskName.length() <= MIN_TASK_NAME_LENGTH) {
            System.out.println("Task Name is too Short!");
            System.out.println("Minimum " + MIN_TASK_NAME_LENGTH + " Characters");
        } else {
            //if name fits requirements, set task type. (work or home.)
            System.out.println("Is this task for Home or Work? (Type 'Home' or 'Work'):");
            System.out.print("> ");
            String taskType = scan.nextLine().trim();

            //continues to check for home or work until user enters correct input
            //if exit is entered, user is taken to main console where they can continue
            //to enter commands.
            while (!taskType.equalsIgnoreCase("Home") && !taskType.equalsIgnoreCase("Work")) {
                if (taskType.equalsIgnoreCase("exit")) {
                    return;
                }
                System.out.println("Error.");
                System.out.println("Is this task for Home or Work? (Type 'Home' or 'Work'):");
                System.out.print("> ");
                taskType = scan.nextLine().trim();

            }

            if (taskType.equalsIgnoreCase("Home")) {
                task = new HomeTask();
            } else if (taskType.equalsIgnoreCase("Work")) {
                task = new WorkTask();
            }
            task.setTaskName(taskName);
            dbm.add(task);
        }
    }

    //obselete -- updated to work with DB
    protected Task createTask() {
//        //File IO class
//        TaskFileManager tfm = new TaskFileManager();
//        Scanner scan = new Scanner(System.in);
//        //prompt user for task name
//        System.out.println("Task Name? ");
//        System.out.print("> ");
//        String taskName = scan.nextLine().trim();
//        if (taskName.equalsIgnoreCase("exit")) {
//            System.out.println("Cancelled");
//            return null;
//        }
//        //check task fits parameters of name. taken to main console with error message if task name doesnt
//        //suit requirements. where user can continue to enter commands.
//        if (taskName.isEmpty()) {
//            System.out.println("Task Name Must Contain a Value!");
//        } else if (taskName.length() >= MAX_TASK_NAME_LENGTH) {
//            System.out.println("Task Name is too Long!");
//            System.out.println("Maximum " + MAX_TASK_NAME_LENGTH + " Characters");
//        } else if (taskName.length() <= MIN_TASK_NAME_LENGTH) {
//            System.out.println("Task Name is too Short!");
//            System.out.println("Minimum " + MIN_TASK_NAME_LENGTH + " Characters");
//        } else {
//            //if name fits requirements, set task type. (work or home.)
//            System.out.println("Is this task for Home or Work? (Type 'Home' or 'Work'):");
//            System.out.print("> ");
//            String taskType = scan.nextLine().trim();
//
//            //continues to check for home or work until user enters correct input
//            //if exit is entered, user is taken to main console where they can continue
//            //to enter commands.
//            while (!taskType.equalsIgnoreCase("Home") && !taskType.equalsIgnoreCase("Work")) {
//                if (taskType.equalsIgnoreCase("exit")) {
//                    return null;
//                }
//                System.out.println("Error.");
//                System.out.println("Is this task for Home or Work? (Type 'Home' or 'Work'):");
//                System.out.print("> ");
//                taskType = scan.nextLine().trim();
//
//            }
//
//            if (taskType.equalsIgnoreCase("Home")) {
//                task = new HomeTask();
//            } else if (taskType.equalsIgnoreCase("Work")) {
//                task = new WorkTask();
//            }
//            task.setTaskName(taskName);
//
//            if (taskType.equalsIgnoreCase("Work") || taskType.equalsIgnoreCase("Home")) {
//
//                if (taskType.equalsIgnoreCase("Work")) {
//                    StringBuilder build = new StringBuilder();
//                    ArrayList<String> newTasks = new ArrayList<>();
//                    build.append(task.getTaskName());
//                    build.append("|  ");
//                    build.append(task.getDate());
//                    build.append("  |");
//                    build.append(Boolean.toString(task.isCompleted()));
//                    build.append("|");
//                    build.append(task.getType());
//                    build.append("|\n");
//                    //if tasks.txt is empty. dont add contents
//                    if (tfm.readTasks() == null) {
//                        newTasks.add(build.toString());
//                    } else {
//                        //if tasks.txt has content, add to array and then add new task
//                        newTasks.addAll(tfm.readTasks());
//                        newTasks.add(build.toString());
//                    }
//                    //finally write to tasks.txt
//                    tfm.writeTasks(newTasks);
//                    dbm.add(task);
//                    System.out.println("Work Task Added");
//                }
//
//                if (taskType.equalsIgnoreCase("Home")) {
//                    StringBuilder build = new StringBuilder();
//                    ArrayList<String> newTasks = new ArrayList<>();
//                    build.append(task.getTaskName());
//                    build.append("|  ");
//                    build.append(task.getDate());
//                    build.append("  |");
//                    build.append(Boolean.toString(task.isCompleted()));
//                    build.append("|");
//                    build.append(task.getType());
//                    build.append("|\n");
//                    //same logic as "Work" case
//                    if (tfm.readTasks() == null) {
//                        newTasks.add(build.toString());
//                    } else {
//                        newTasks.addAll(tfm.readTasks());
//                        newTasks.add(build.toString());
//                    }
//                    dbm.add(task);
//                    tfm.writeTasks(newTasks);
//                    System.out.println("Home Task Added.");
//                }
//            } else {
//                System.out.println("Invalid task type! Task creation cancelled.");
//            }
//        }
        return null;
    }
}
