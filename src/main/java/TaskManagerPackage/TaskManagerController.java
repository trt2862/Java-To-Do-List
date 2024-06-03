/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TaskManagerPackage;

/**
 *
 * @author Caleb
 */
public class TaskManagerController {

    Printer tp = new TaskPrinter();
    TaskCreator tc = new TaskCreator();
    UndoManager um = new UndoManager();
    Validator iv = new InputValidator();
    TaskFileManager tfm = new TaskFileManager();
    TaskRemover tr = new TaskRemover(tp, iv, tfm);
    TaskUpdater tu = new TaskUpdater(iv, tp, tfm);
    DBManager dbm = new DBManager();

    public TaskManagerController() {
    }

    public void processCommand(String userInput) {
        userInput = userInput.toLowerCase();
        switch (userInput) {
            //check for commands
            case "":
                break;
            case "display all":
            case "display a":
            case "display al":
                tp.printAllTasksFromDB();
                break;
            case "display task":
            case "display t":
            case "display ta":
            case "display tas":
                tp.printTaskFromDB();
                break;
            case "remove all":
            case "remove a":
            case "remove al":
                um.commandPushDB();
                tr.removeAllFromConsoleDB();
                break;
            case "remove task":
            case "remove t":
            case "remove ta":
            case "remove tas":
                um.commandPushDB();
                tr.removeTaskFromConsoleDB();
                break;
            case "create":
            case "c":
            case "cr":
            case "cre":
            case "crea":
            case "creat":
                um.commandPushDB();
                tc.createTaskConsoleDB();
                break;
            case "?":
                tp.printCommands();
                break;
            case "update":
            case "up":
            case "upd":
            case "upda":
            case "updat":
                tu.updateCompleteStatusDB();
                break;

            case "undo":
            case "und":
            case "un":
//                um.commandPopDB()
//                um.commandPop();
//                dbm.undo();
                break;
            case "u":
                System.out.println("unambiguous command");
                break;
            case "remove":
            case "r":
            case "re":
            case "rem":
            case "remo":
            case "remov":
                System.out.println("unambiguous command");
                break;
            case "display":
            case "d":
            case "di":
            case "dis":
            case "disp":
            case "displ":
            case "displa":
                System.out.println("unambiguous command");
                break;
            case "exit":
                break;
            default:
                System.out.println("Unrecognized Command.");
                break;
        }
    }
}
