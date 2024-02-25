import java.io.File;

public class Main {
    public static File sourceFile;
    public static File targetFile;
    public static SimpleSearch simpleSearch = new SimpleSearch();
    public static RegexSearch regexSearch = new RegexSearch();
    public static boolean isSearchingFromFile = false;
    public static void main(String[] args) {
        String searchType = "";
        System.out.println("Welcome to Extractor.");

        // input file or clipboard?
        String menuChoice = IOSystem.createMenu("search text from", "File", "Clipboard");
        if (menuChoice.equals("1")) {
            String sourceFilePath = IOSystem.promptForInput("Please enter source file path:");
            sourceFile = new File(sourceFilePath);
            isSearchingFromFile = true;
        }

        String targetFilePath = IOSystem.promptForInput("Please enter desired target file path:");
        targetFile = new File(targetFilePath);

        // search for phone numbers, emails, or prices?
        menuChoice = IOSystem.createMenu("search for", "All phone numbers", "All email addresses", "All prices", "Specific text and return line numbers", "Create your own search pattern");

        if (menuChoice.equals("1")) {
            if (!isSearchingFromFile) {
                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(MyRegex.phoneRegex));
            }

        } else if (menuChoice.equals("2")) {
            if (!isSearchingFromFile) {
                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(MyRegex.emailRegex));
            }

        } else if (menuChoice.equals("3")) {
            if (!isSearchingFromFile) {
                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(MyRegex.priceRegex));
            }

        } else if (menuChoice.equals("4")) {
            String searchTerm = IOSystem.promptForInput("Please enter the word you would like to search for");
            if (!isSearchingFromFile) {
                IOSystem.toScreenAndFile(targetFile, simpleSearch.searchFromClipboard(searchTerm));
            }
        } else if (menuChoice.equals("5")) {
            if (!isSearchingFromFile) {
                IOSystem.toScreenAndFile(targetFile, regexSearch.searchFromClipboard(MyRegex.regexBuilder()));

            }
        }
        // do the thing
    }
}
