import java.io.File;
import java.util.List;
import java.util.Map;

public class Main {
    //TODO: Create Map-ception
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
            Map<String, Map<String, Map<String, String>>> regexMap = MyRegex.createRegexPatternMap();
            for (Map.Entry<String, Map<String, Map<String, String>>> category : regexMap.entrySet()) {
                for (Map.Entry<String, Map<String, String>> subcategory : category.getValue().entrySet()) {
                    for (Map.Entry<String, String> pattern : subcategory.getValue().entrySet()) {
                        System.out.println(pattern.getKey());
                    }
                }
            }
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
                IOSystem.toScreenAndFile(targetFile, simpleSearch.searchFromFile(sourceFilePath, MyRegex.phoneRegex));
            }
        } else if (menuChoice.equals("2")) {
            menuChoice = IOSystem.createMenu("", "Choose from a list of patterns", "Create your own pattern");
            //regex search options
            switch (menuChoice) {
                case "1" -> {
                    //TODO: add regexMap call

                    if (!isSearchingFromFile) {
                        IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(MyRegex.phoneRegex));
                    } else {
                        IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromFile(sourceFilePath, MyRegex.phoneRegex));
                    }
                }
                case "2" -> {
                    System.out.println("""
                            A pattern is made of a combination of special characters and/or normal characters.
                            For example, "# digit digit space letter" would match a 5 character string:
                            A "#" sign followed by any 2 numbers, a space, and then any letter. ex: "#42 A", "#09 f"
                            """);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("""
                            Using the following key, enter a pattern separated by spaces you would like to search for:
                                            
                            digit = any number 0-9
                            letter = any letter a-z, case insensitive
                            alphaNum = any number 0-9, any letter a-z, case insensitive
                            space = empty space
                            """);
                    String userPattern = IOSystem.promptForInput("");
                    String userRegex = MyRegex.regexBuilder(userPattern);
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
