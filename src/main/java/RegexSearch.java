import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSearch extends Search{
    public String searchFromFile(String filePath, String searchTerm) {
        File fileToSearch = new File(filePath);
        StringBuilder fileString = new StringBuilder();
        StringBuilder resultString = new StringBuilder();

        try (Scanner userInput = new Scanner(fileToSearch)) {
            while (userInput.hasNextLine()) {
                String currentLine = userInput.nextLine();
                fileString.append(currentLine).append("\n");
            }
            Pattern searchPattern = Pattern.compile(searchTerm);
            Matcher matcher = searchPattern.matcher(fileString);

            while (matcher.find()) {
                resultString.append(matcher.group());
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return resultString.toString();
    }

    public String searchFromClipboard(String searchTerm) {
        StringBuilder result = new StringBuilder();
        Pattern searchPattern = Pattern.compile(searchTerm);
        Matcher matcher = searchPattern.matcher(IOSystem.getTextFromClipboard());

        while (matcher.find()) {
            result.append(matcher.group()).append("\n");
        }

        return result.toString();
    }


}
