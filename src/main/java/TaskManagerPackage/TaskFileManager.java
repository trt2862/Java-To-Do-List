/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Caleb
 */
public class TaskFileManager {

    public TaskFileManager() {

    }

    //gets all items in tasks.txt
    public ArrayList<String> readTasks() {
        ArrayList<String> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) { // Skip empty lines
                    tasks.add(line);
                }
            }
            reader.close();
        } catch (IOException ex) {
            System.err.println("Error reading tasks from file: " + ex.getMessage());
        }

        if (tasks.isEmpty()) {
            return null; // Return null if the file is empty or contains only empty lines
        }
        return tasks;
    }

    public String readTasks(int lineNumber) {
        int current = 1;
        String task = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) { // Skip empty lines
                    if (current == lineNumber) {
                        task = line;
                        break; // Exit the loop once the desired line is found
                    }
                    current++;
                }
            }
            reader.close();
//            if (current == 1) { // Check if no non-empty lines were read
//                return null; // Return null if the file is empty or contains only empty lines
//            }
        } catch (IOException ex) {
            System.err.println("Error reading tasks from file: " + ex.getMessage());
        }
        return task;
    }

    //gets all tasks in tasks.txt except line number int except
    public ArrayList<String> readTasksExcept(int except) {
        ArrayList<String> tasks = new ArrayList<>();
        int lineNumber = 1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineNumber != except) {
                    tasks.add(line);
                }
                lineNumber++;
            }
            reader.close();
        } catch (IOException ex) {
            System.err.println("Error reading tasks from file: " + ex.getMessage());
        }
        return tasks;
    }

    //overwrites tasks.txt with tasks Array
    public void writeTasks(ArrayList<String> tasks) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Error writing tasks to file: " + ex.getMessage());
        }
    }
}
