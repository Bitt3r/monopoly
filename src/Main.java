import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static boolean running;

    private static final Map<String, Runnable> commands = new HashMap<>() {{
        System.out.println("Enter info for more !");

        put("info", () -> {
            String info = "Commands:" +
                    "\n" + "start -- play the game." +
                    "\n" + "exit     -- quit the program.";
            System.out.println(info);
        });

        put("start", Monopoly::new);
        put("exit", () -> running = false);
    }};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        running = true;

        //Keep interpreting commands until the program is exited.
        while (running) {
            Scanner line = new Scanner(s.nextLine());

            //If there is a command to interpret...
            if (line.hasNext()) {
                String cmd = line.next();

                //If this command has been defined in "commands"...
                if (commands.containsKey(cmd)) {
                    commands.get(cmd).run();
                } else {
                    System.out.println("Command not recognized.");
                }
            }
        }
    }
}