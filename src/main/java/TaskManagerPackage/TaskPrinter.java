/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caleb
 */
public class TaskPrinter implements Printer {

    Scanner scan = new Scanner(System.in);
    Validator iv = new InputValidator();
    TaskFileManager tfm = new TaskFileManager();
    DBManager dbm = new DBManager();

    public TaskPrinter() {

    }

    @Override
    public void printCommands() {
        System.out.println("Create - Creates new Task - SUPPORTS DB");
        System.out.println("Update - Update Created Tasks");
        System.out.println("Undo - Undo Previous Operation");
        System.out.println("Remove Task - Removes Task Entirely - SUPPORTS DB");
        System.out.println("Remove All - Removes ALL TASKS - SUPPORTS DB");
        System.out.println("Display All - Displays All Tasks - SUPPORTS DB");
        System.out.println("Display Task - Displays Task Selected by User - SUPPORTS DB");
        System.out.println("Exit - Cancel/Exit Session");
        System.out.println("? - Display All Commands.");
    }

    @Override
    public void printDisplay() {
//
//        if (iv.isEmpty() == true) {
//            System.out.println("No Tasks to Display!"); // checks if tasks.txt is empty, by checking if first line is null.
//            return;
//        }
//        System.out.println("Select the Task Number (e.g. 1,2,3..) to Display: ");
//        String userInput = scan.nextLine().trim();
//        userInput = iv.validateTaskExists(userInput, tfm.readTasks().size());
//
//        while (!iv.validateNumber(userInput) || Integer.parseInt(userInput) < 1 || Integer.parseInt(userInput) > getTasksLength()) {
//            if (userInput.equalsIgnoreCase("exit")) {
//                return;
//            }
//            System.out.println("Incorrect Input.");
//            System.out.println("Please Select a Task Number: ");
//            userInput = scan.nextLine().trim();
//        }
//        int lineNumberToDisplay = Integer.parseInt(userInput);//input validated by inputValidator Class above.
//
//        String task;
//        task = tfm.readTasks(lineNumberToDisplay);
//        if (task == null) {
//            System.out.println("Task Not Found! Double Check Task Exists or Check Task Number.");
//            return;
//        }
//
//        //prints top bar
//        printTop();
//
//        //prints task selected by user
//        String[] parts = task.split("\\|");
//        System.out.print("\nTask " + lineNumberToDisplay + ": ");
//
//        //prints spaces after task name to ensure nice formatting
//        int maxLength = tc.getNameLength();
//        int nameLength = parts[0].length();
//        String spaces = printSpaces(maxLength, nameLength);
//        System.out.print(parts[0] + spaces + "|"); // Task Name
//        System.out.print(parts[1] + "| "); // Date Created
//        if (parts[2].equalsIgnoreCase("true")) { // Task Status
//            System.out.print("Complete ");
//        } else {
//            System.out.print("Pending  ");
//        }
//        System.out.print("| " + parts[3].trim() + " |"); // Task type
//        printBottom();
    }

    @Override
    public void printTaskFromDB(int taskId) {
        try {
            ResultSet set = dbm.getTask(taskId);
            formatTasks(set);
        } catch (SQLException ex) {
            Logger.getLogger(TaskPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void printTaskFromDB() {
        if (dbm.isEmpty()) {
            System.out.println("No Tasks to Display!"); // checks if tasks.txt is empty, by checking if first line is null.
            return;
        }
        printAllTasksFromDB();
        System.out.println("Select the Task ID (e.g. 1,22,33..) to Display: ");
        String userInput = scan.nextLine().trim();
//        userInput = iv.validateTaskExists(userInput, tfm.readTasks().size());

        while (!iv.validateNumber(userInput) || Integer.parseInt(userInput) < 1 || !dbm.exists(Integer.parseInt(userInput))) {
            if (userInput.equalsIgnoreCase("exit")) {
                return;
            }
            System.out.println("Incorrect Input.");
            System.out.println("Please Select a Task Number: ");
            userInput = scan.nextLine().trim();
        }
        int taskId = Integer.parseInt(userInput);
        if (!dbm.exists(taskId)) {
            System.out.println("Task Not Found! Double Check Task Exists or Check Task Number.");
            return;
        }
        printTaskFromDB(taskId);
    }

    @Override
    public int printAllTasksFromDB() {
        int rowCount = 0;
        try {
            ResultSet resultSet = dbm.getAllTasks();
            rowCount = formatTasks(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(TaskPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    private int formatTasks(ResultSet resultSet) throws SQLException {
        //retrieve column count from metaData
        java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        int rowCount = 0;

        //calculate width of columns from metadata.
        //create array of column widths using column name initially
        int[] columnWidths = new int[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnWidths[i - 1] = metaData.getColumnName(i).length();
        }

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                //retrieves length of data in column
                int length = resultSet.getString(i).length();
                //if length is greater than current max length,
                //update the column width.
                if (length > columnWidths[i - 1]) {
                    columnWidths[i - 1] = length;
                }
            }
        }

        //print column names
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(padRight(metaData.getColumnName(i), columnWidths[i - 1]) + "\t");
        }
        System.out.println();

        //reset the cursor to the beginning
        resultSet.beforeFirst();
        rowCount++;

        //print each row of the ResultSet
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(padRight(resultSet.getString(i), columnWidths[i - 1]) + "\t");
            }
            System.out.println();
        }
        return rowCount;
    }

    //helper for formatTasks method above
    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }

    @Override
    public int printDisplayAll() {
//        int lineCount = 1;
//        System.out.println("Displaying Tasks...");
//        ArrayList<String> taskList;
//        taskList = tfm.readTasks();
//        if (taskList == null) {
//            printTop();
//            printBottom();
//            return -1;
//        }
//        printTop();
//        for (String task : taskList) {
//            String[] parts = task.split("\\|");
//            System.out.print("\nTask " + lineCount + ": ");
//
//            int maxLength = tc.getNameLength();
//            int nameLength = parts[0].length();
//            String spaces = printSpaces(maxLength, nameLength);
//
//            System.out.print(parts[0] + spaces + "|"); // Task name
//            System.out.print(parts[1] + "| "); // date created
//            if (parts[2].equalsIgnoreCase("true")) { // completed status
//                System.out.print("Complete ");
//            } else {
//                System.out.print("Pending  ");
//            }
//            System.out.print("| " + parts[3].trim() + " |"); // Task type
//
//            lineCount++;
//        }
//
//        printBottom();
//        System.out.println("Total Tasks: " + (lineCount - 1));
//        return lineCount - 1; // returns total number of tasks
        return -1;
    }

    //Formatting Methods
//    public void printTop() {
//        int topBarLength = tc.getNameLength() + 8;
//        for (int i = 0; i <= (topBarLength - 11) / 2; i++) {
//            System.out.print("*");
//        }
//        System.out.print("<Task Name>");
//        for (int i = 0; i <= (topBarLength - 11) / 2; i++) {
//            System.out.print("*");
//        }
//        System.out.print("*<Date Created>*<Complete>*<Type>*\n");
//    }
//
//    public void printBottom() {
//        int bottomBarLength = tc.getNameLength() + 43;
//        System.out.println("\n");
//        for (int i = 0; i < bottomBarLength; i++) {
//            System.out.print("*");
//        }
//        System.out.println("");
//    }
//
//    public String printSpaces(int maxLength, int nameLength) {
//        String spaces = "";
//        for (int i = 0; i < ((maxLength - nameLength) + 1); i++) {
//            spaces += " ";
//        }
//        return spaces;
//    }
//
//    public int getTasksLength() {
//        ArrayList<String> taskList = tfm.readTasks();
//        if (taskList != null) {
//            return taskList.size(); // Return the size of the taskList ArrayList
//        } else {
//            return 0; // Return 0 if the taskList is null (no tasks)
//        }
//    }
}
