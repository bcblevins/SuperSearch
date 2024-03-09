import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimpleSearch extends Search{

    public String searchFromFile(String filePath, String searchTerm) {
        File fileToSearch = new File(filePath);
        StringBuilder resultString = new StringBuilder();

        try (Scanner userInput = new Scanner(fileToSearch)) {
            int lineCount = 0;
            while (userInput.hasNextLine()) {
                lineCount++;
                String currentLine = userInput.nextLine();
                if (currentLine.toLowerCase().contains(searchTerm.toLowerCase())) {
                    resultString.append(lineCount).append(". ").append(currentLine).append("\n");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Source file not found.");
        }

        return resultString.toString();
    }

    public String searchFromClipboard(String searchTerm){
        String caseSensitive = IOSystem.createMenu("search", "Case sensitive", "Case insensitive");

        StringBuilder result = new StringBuilder();
        String[] clipboardLines = IOSystem.getTextFromClipboard().split("\n");

        for (int i = 0; i < clipboardLines.length; i++) {
            String currentLine = clipboardLines[i];

            if (caseSensitive.equals("1")) {
                if (currentLine.contains(searchTerm)) {
                    result.append((i + 1)).append(". ").append(clipboardLines[i]).append("\n");
                }
            } else {
                if (currentLine.toLowerCase().contains(searchTerm.toLowerCase())) {
                    result.append((i + 1)).append(". ").append(clipboardLines[i]).append("\n");
                }
            }
        }

        return result.toString();
    }

}
