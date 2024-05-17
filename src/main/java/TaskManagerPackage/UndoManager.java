/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 *
 * @author Caleb
 */
public class UndoManager {

    //Deque to store tasks.txt after every operation.
    //Using Deque incase a 'Redo' Function is implemented, as can be removed
    //from both ends of the queue.
    private final Deque<String> tasksFileHistory;
    private String tasksFileContent;
    TaskFileManager tfm = new TaskFileManager();

    public UndoManager() {
        tasksFileHistory = new ArrayDeque();
        tasksFileContent = "";
    }

    public boolean commandPush() {
        ArrayList<String> taskList = tfm.readTasks();
        // Convert the ArrayList to a single string and add to Deque
        StringBuilder builder = new StringBuilder();
        if (taskList != null) {
            for (String task : taskList) {
                builder.append(task).append("\n");
            }
            tasksFileContent = builder.toString();
            tasksFileHistory.push(tasksFileContent);
        } else if (taskList == null) {
            //if taskList is empty. store previous state as empty.
            tasksFileContent = "";
            tasksFileHistory.push(tasksFileContent);
        }
        return true;
    }

    public boolean commandPop() {
        if (tasksFileHistory.peek() == null) {
            System.out.println("Nothing to undo");
            return false;
        }
        String poppedContent = tasksFileHistory.pop();
        ArrayList<String> taskList = new ArrayList<>();
        taskList.add(poppedContent);

        // Write the popped content to the tasks.txt file
        tfm.writeTasks(taskList);

        return true;
    }

}
