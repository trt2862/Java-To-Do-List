/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Caleb
 */
public class WorkTask extends Task {

    TaskCreator tc = new TaskCreator();
    private String taskName;
    private String dateCreated;
    private boolean completed;
    private final String taskType = "Work";

    public WorkTask() {
        Date currentDate = new Date();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.taskName = "null";
        this.dateCreated = sdf.format(currentDate);
        this.completed = false;
    }

    public String getType() {
        return this.taskType;
    }
}
