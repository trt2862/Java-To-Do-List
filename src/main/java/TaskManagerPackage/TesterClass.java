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
        Task t1 = new WorkTask();
        t1.setCompleted(true);
        t1.setTaskName("task 1");
        Task t2 = new WorkTask();
        t2.setCompleted(false);
        t2.setTaskName("task 2");
        Task t3 = new HomeTask();
        t3.setCompleted(true);
        t3.setTaskName("task 3");

        dbm.add(t1);
        dbm.add(t2);
        dbm.add(t3);

        tp.printAllTasksFromDB();
//        tp.printTaskFromDB(3);
//        dbm.remove(1);
//        tp.printAllTasksFromDB();
        dbm.removeAll();
        tp.printAllTasksFromDB();
        System.out.println(dbm.isEmpty());
    }

}
