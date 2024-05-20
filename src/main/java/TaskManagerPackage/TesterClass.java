/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

/**
 *
 * @author Caleb
 */
public class TesterClass {

    public static void main(String[] args) {
        TaskPrinter tp = new TaskPrinter();
        DBManager dbm = new DBManager();
        dbm.initialiseDB();
        tp.printAllTasksFromDB();
        tp.printTaskFromDB(1);
    }

}
