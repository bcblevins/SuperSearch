import java.io.File;

public class Main {
    public static File sourceFile;
    public static File targetFile;
    public static void main(String[] args) {
        String searchType = "";
        System.out.println("Welcome to Extractor.");

        // input file or clipboard?
        String sourceChoice = IOSystem.createMenu("search text from", "File", "Clipboard");
        if (sourceChoice.equals("1")) {
            String sourceFilePath = IOSystem.promptForInput("Please enter source file path:");
            sourceFile = new File(sourceFilePath);
        }

        // output file or clipboard?
        String targetChoice = IOSystem.createMenu("output to", "File", "Screen only");
        if (targetChoice.equals("1")) {
            String targetFilePath = IOSystem.promptForInput("Please enter desired target file path:");
            targetFile = new File(targetFilePath);
        }

        // search for phone numbers, emails, or prices?
        IOSystem.createMenu("search for", "All phone numbers", "All email addresses", "All prices", "Specific text", "Create your own search pattern");

        // do the thing
    }
}
