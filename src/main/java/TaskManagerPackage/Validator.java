/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

import javax.swing.JOptionPane;

/**
 *
 * @author Caleb
 */
public abstract class Validator {

    /**
     *
     * @param name
     * @param max
     * @param min
     * @return
     */
    public int validateTaskName(String name, int max, int min) {
        return -1;
    }

    public boolean validateAlphabetical(String input) {

        return false;

    }

    public boolean validateNumber(String input) {

        return false;

    }

    public String validateTaskExists(String userInput, int totalTasks) {
        return "";
    }

    public boolean isEmpty() {
        return false;
    }
}
