import java.io.File;
import java.util.Map;

public class Main {
    //TODO: create unit tests, fix name regex and check the rest
    public static String sourceFilePath = "";
    public static File targetFile;
    public static SimpleSearch simpleSearch = new SimpleSearch();
    public static RegexSearch regexSearch = new RegexSearch();
    public static boolean isSearchingFromFile = false;
    public static void main(String[] args) {
        System.out.println("Welcome to Extractor.");

        //input file or clipboard?
        String menuChoice = IOSystem.createMenu("search text from", "File", "Clipboard", "Test");
        if (menuChoice.equals("1")) {
            sourceFilePath = IOSystem.promptForInput("Please enter source file path:");
            isSearchingFromFile = true;
        }

        ////////////////////////////
        if (menuChoice.equals("3")) {
            System.out.println("nothing to test");
            return;
        }
        ////////////////////////////

        String targetFilePath = IOSystem.promptForInput("Please enter desired target file path:");
        targetFile = new File(targetFilePath);

        //search type
        menuChoice = IOSystem.createMenu("", "Plain text search", "Pattern based search");

        //simple search
        if (menuChoice.equals("1")) {
            String searchTerm = IOSystem.promptForInput("Please enter the word you would like to search for");
            if (!isSearchingFromFile) {
                IOSystem.toScreenAndFile(targetFile, simpleSearch.searchFromClipboard(searchTerm));
            } else {
                IOSystem.toScreenAndFile(targetFile, simpleSearch.searchFromFile(sourceFilePath, searchTerm));
            }
        //regex search
        } else if (menuChoice.equals("2")) {
            menuChoice = IOSystem.createMenu("", "Choose from a list of patterns", "Create your own pattern");
            //regex search options
            switch (menuChoice) {
                case "1" -> {
                    String regexPattern = MyRegex.selectPattern();
                    if (!isSearchingFromFile) {
                        IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(regexPattern));
                    } else {
                        IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromFile(sourceFilePath, regexPattern));
                    }
                }
                case "2" -> {
                    String userRegex = MyRegex.regexBuilder();
                    if (!isSearchingFromFile) {
                        IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(userRegex));
                    } else {
                        IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromFile(sourceFilePath, userRegex));
                    }
                }

            }
        }
    }
}
