import java.util.*;

public class IOSystem {
    private static Scanner userInput = new Scanner(System.in);

    //----------------------
    // Major Methods
    //----------------------
    public static String createMenu(String commonWord, String... args) {
        //Display options
        System.out.println("Would you like to " + commonWord + ":");
        Set<String> options = new HashSet<>();
        int choiceNumber = 0;
        for (int i = 0 ; i < args.length ; i++) {
            choiceNumber = i + 1;
            options.add(String.valueOf(choiceNumber));
            System.out.println(choiceNumber + ". " + args[i]);
        }

        //Validate choice and re-prompt if necessary
        String choice = userInput.nextLine();
        while (true) {
            if (options.contains(choice)) {
                break;
            } else {
                System.out.println("That is not a valid option. Please select an option between 1 and " + choiceNumber);
                choice = userInput.nextLine();
            }
        }

        return choice;
    }
    //----------------------
    // Small Methods
    //----------------------
    public static String promptForInput(String prompt) {
        System.out.println(prompt);
        return userInput.nextLine();
    }
}
