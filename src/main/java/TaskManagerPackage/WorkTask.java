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

    private final String taskType = "Work";

    public WorkTask() {
        Date currentDate = new Date();
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        setTaskName("Null");
        setDateCreated(sdf.format(currentDate));
        setCompleted(false);
    }

    @Override
    public String getType() {
        return this.taskType;
    }
}
