import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Scanner;

final public class CommandHandler implements Listener {
    private ArrayList<Task> tasks = new ArrayList<>();
    private static CommandHandler instance = null;
    private static boolean entered = false;
    private CommandHandler() {}
    public static CommandHandler get() {
        if(instance == null) instance = new CommandHandler();
        var result = (instance != null && entered? null: instance );
        entered = true;
        return result;
    }
    private void run(String command) {
        var desc = "";
        var name = "";
        var scan = new Scanner(System.in);
        switch (command) {
            case "task_add":
                System.out.print("Enter a name for your task: ");
                name = scan.nextLine();
                System.out.print("Describe your task: \n\t");
                desc = scan.nextLine();
                this.tasks.add(new Task(name,desc));
                System.out.println("Task \'"+name+"\' was successfully added!");
                break;
            case "task_complete":
                System.out.print("Enter for task to complete: ");
                name = scan.nextLine();
                for(var e : tasks) {
                    if(e.name() == name) tasks.remove(e);
                }
                System.out.println("Task was completed!");
                break;
            case "task_show":
                for(var e : tasks) {
                    System.out.println(e.name() + ": "+e.description());
                }
                break;
            case "authors":
                System.out.println(ProgramManager.LICENSE_SHORT);
                break;
            case "license":
                System.out.println(ProgramManager.LICENSE);
            case "help":

                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command: "+ command);
                break;
        }
    }

    @Override
    public void listen(Object o) {
        run((String)o);
    }
}
