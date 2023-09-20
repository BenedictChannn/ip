package Ken;

import Commands.Command;
import Commands.Goodbye;
import Exceptions.KenException;
import Exceptions.KenParsingException;

import java.util.Scanner;

public class Ken {
    private static final TaskList list = new TaskList();


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ParseCommands parser = new ParseCommands();

        Ui.greetUser();
        try {
            FileHandler.readFromFile(list);
            Command startCommand = parser.parse("list");
            startCommand.run(list);
        } catch (Exception e) {
            Ui.printTexts(new String[] {
                    e.getMessage()
            });
        }

        Command command = null;
        String input;
        while (!(command instanceof Goodbye)) {
            input = scan.nextLine();
            try {
                command = parser.parse(input);
                try {
                    command.run(list);
                } catch (KenParsingException e) {
                    Ui.printTexts(new String[] {
                            e.getMessage()
                    });
                }
            } catch (KenException e) {
                Ui.printTexts(new String[] {
                        e.getMessage()
                });
            }
        }
    }
}
