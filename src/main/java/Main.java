import java.io.File;

public class Main {
    public static String sourceFilePath = "";
    public static File targetFile;
    public static SimpleSearch simpleSearch = new SimpleSearch();
    public static RegexSearch regexSearch = new RegexSearch();
    public static boolean isSearchingFromFile = false;

    // Main method
    public static void main(String[] args) {
        IOSystem.printSeparator();
        System.out.println("Welcome to SuperSearch.");
        while (true) {

            //input file or clipboard?
            String menuChoice = IOSystem.createMenu("search text from", "File", "Clipboard");
            if (menuChoice.equals("1")) {
                sourceFilePath = IOSystem.promptForInput("Please enter source file path:");
                isSearchingFromFile = true;
            }

            //get target file
            String targetFilePath = IOSystem.promptForInput("Please enter desired target file path, or press enter to use test file:");
            if (targetFilePath.isEmpty()) {
                targetFile = new File("test-result.txt");
            } else {
                targetFile = new File(targetFilePath);
            }

            //search type
            menuChoice = IOSystem.createMenu("", "Plain text search", "Pattern based search");
            switch (menuChoice) {

                //simple search
                case "1" -> {
                    String searchTerm = IOSystem.promptForInput("Please enter the word you would like to search for");
                    if (!isSearchingFromFile) {
                        IOSystem.toScreenAndFile(targetFile, simpleSearch.searchFromClipboard(searchTerm));
                    } else {
                        IOSystem.toScreenAndFile(targetFile, simpleSearch.searchFromFile(sourceFilePath, searchTerm));
                    }
                    menuChoice = IOSystem.createMenu("", "Search something else", "Quit");
                    if (menuChoice.equals("2")) {
                        return;
                    }
                }

                //regex search
                case "2" -> {
                    menuChoice = IOSystem.createMenu("", "Choose from a list of patterns", "Create your own pattern");
                    switch (menuChoice) {

                        //choose pattern
                        case "1" -> {
                            String regexPattern = MyRegex.selectPattern();
                            if (!isSearchingFromFile) {
                                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(regexPattern));
                            } else {
                                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromFile(sourceFilePath, regexPattern));
                            }
                            menuChoice = IOSystem.createMenu("", "Search something else", "Quit");
                            if (menuChoice.equals("2")) {
                                return;
                            }
                        }

                        //create pattern
                        case "2" -> {
                            String userRegex = MyRegex.getUserPattern();
                            if (!isSearchingFromFile) {
                                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(userRegex));
                            } else {
                                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromFile(sourceFilePath, userRegex));
                            }
                            menuChoice = IOSystem.createMenu("", "Search something else", "Quit");
                            if (menuChoice.equals("2")) {
                                return;
                            }
                        }

                    }
                }
            }
        }
    }
}
