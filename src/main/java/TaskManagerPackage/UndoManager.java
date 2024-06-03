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

    //CONVERTS RESULT-SET TO LIST OF MAPS - ADDS TO DEQUE
    public boolean commandPushDB(ResultSet set) {
        //adds list of maps to deque
        tasksDBHistory.push(resultSetToList(set));
//        System.out.println("Added DB Information to Deque");
        return true;
    }

    public boolean commandPushDB() {
        ResultSet set = dbm.repo.getAllElements();
        commandPushDB(set);
        return true;
    }

    //POPS TOP OF DEQUE STACK
    public List<Map<String, Object>> commandPopDB() {
        if (tasksDBHistory.peek() == null) {
            return null;
        }
        return tasksDBHistory.pop();
    }

    //BUG - DOESNT UPDATE COMPLETED STATUS CORRECTLY.
    //BUG - DOESNT UNDO TASK BEING ADDED
    //CANT BE F*CKED FIXING IT RN
    //CONVERTS A RESULT SET TO A LIST OF MAPS FOR PROCESSING
    public List<Map<String, Object>> resultSetToList(ResultSet rs) {
        //create a new list of maps

        //e.g. map format:
        //[0] TaskID => '1'
        //[1] TaskName => 'name'
        //[2] Complete => 'Y'
        //[3] TaskType => 'home/work'
        //each map will represent a ROW in the table.
        //key = column (id)
        //value = data (1,2,3 etc)
        //we will create a new MAP for each row.
        //all the maps are stored in a LIST, so that the maps can be organised.
        //list of maps to store row data
        List<Map<String, Object>> resultSetList = new ArrayList<>();
        try {

            //get meta data from the RS
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            //iterate over the RS
            while (rs.next()) {
                //create a new MAP to store the row
                Map<String, Object> rowMap = new HashMap<>();

                //iterate over each COLUMN of the ROW
                for (int i = 1; i <= columnCount; i++) {
                    //metaData.getColumnName(i) = rowMap.key (taskID)
                    //rs.getObject(i) = rowMap.value (1,2,3 etc)
                    rowMap.put(metaData.getColumnName(i), rs.getObject(i));
                }
                //add the map to the List of Maps.
                resultSetList.add(rowMap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UndoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return the list of Maps
        return resultSetList;
    }

    // ** OBSELETE METHODS ** //
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

}
