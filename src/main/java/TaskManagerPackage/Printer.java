/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

/**
 *
 * @author Caleb
 */
public interface Printer {

    // Abstract method to print a single task display
    public abstract void printDisplay();

    // Abstract method to print all tasks display
    public abstract int printDisplayAll();

    // Abstract method to print all commands
    public abstract void printCommands();

    // Abstract method to print all tasks in Database
    public abstract int printAllTasksFromDB();

    // Abstract methods to print a single task in Database
    public abstract void printTaskFromDB(int taskID);

    public abstract void printTaskFromDB();

}
