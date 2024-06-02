/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caleb
 */
public class UndoManager {

    //Deque to store tasks.txt after every operation.
    //Using Deque incase a 'Redo' Function is implemented, as can be removed
    //from both ends of the queue.
    private final Deque<String> tasksFileHistory;
    private final Deque<List<Map<String, Object>>> tasksDBHistory;
    private String tasksFileContent;
    TaskFileManager tfm = new TaskFileManager();
    DBManager dbm = new DBManager();

    public UndoManager() {
        tasksFileHistory = new ArrayDeque();
        tasksDBHistory = new ArrayDeque();
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

    public boolean commandPushDB(ResultSet set) {
//        ResultSet set = dbm.getAllTasks();
        tasksDBHistory.push(resultSetToList(set));
        System.out.println("Added DB Information to Deque");
        return true;
    }

    public List<Map<String, Object>> commandPopDB() {
        if (tasksDBHistory.peek() == null) {
            return null;
        }
        return tasksDBHistory.pop();
    }

    public List<Map<String, Object>> resultSetToList(ResultSet rs) {
        List<Map<String, Object>> resultSetList = new ArrayList<>();
        try {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowMap.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultSetList.add(rowMap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UndoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSetList;
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
