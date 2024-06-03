/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 *
 * @author Caleb & miguel
 */
public class Task {

    private String taskName;
    private String dateCreated;
    private boolean completed;
    private final String taskType;

    public Task() {
        Date currentDate = new Date();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.taskName = "null";
        this.dateCreated = sdf.format(currentDate);
        this.completed = false;
        this.taskType = "null";
    }

    //getters and setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    //implemented by WorkTask and HomeTask
    public String getType() {
        return this.taskType;
    }

    //check if the task is complete.
    public boolean isCompleted() {
        return completed;
    }

}
