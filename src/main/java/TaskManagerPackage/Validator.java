/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

/**
 *
 * @author Caleb
 */
public abstract class Validator {

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
