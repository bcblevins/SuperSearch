import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSearch extends Search{
//    private boolean isSorted = false;

//    public void setSorted(boolean sorted) {
//        isSorted = sorted;
//    }

    public String searchFromFile(String filePath, String searchTerm) {
        File fileToSearch = new File(filePath);
        StringBuilder fileString = new StringBuilder();
        List<String> resultStringList = new ArrayList<>();
        String resultString = "";

        try (Scanner userInput = new Scanner(fileToSearch)) {
            while (userInput.hasNextLine()) {
                String currentLine = userInput.nextLine();
                fileString.append(currentLine).append("\n");
            }
            Pattern searchPattern = Pattern.compile(searchTerm); //the .compile() method takes a string and returns a regex pattern that can be used for searching
            Matcher matcher = searchPattern.matcher(fileString); //the .matcher() method takes a string and returns a match object, this contains information about any matches found by regex pattern

            while (matcher.find()) { //.find() will start from the beginning of the source and find the first match of the given pattern. Each subsequent call to this method will start where the last match left off. returns true if a match is found.
                resultStringList.add(matcher.group() + "\n"); //.group() will return a string containing the last match found by .find()
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        resultString = String.join("", resultStringList);
        return resultString;
    }

    public String searchFromClipboard(String searchTerm) {
        List<String> resultStringList = new ArrayList<>();
        Pattern searchPattern = Pattern.compile(searchTerm);
        Matcher matcher = searchPattern.matcher(IOSystem.getTextFromClipboard());
        String resultString = "";

        while (matcher.find()) {
            resultStringList.add(matcher.group() + "\n");
        }

        resultString = String.join("", resultStringList);
        return resultString;
    }

    //-----------------
    // Helper Methods
    //-----------------
//    public List<String> sortResultAlphabetical(List<String> resultStringList) {
//        int stopAtOneFewerEachLoop = resultStringList.size();
//
//        for (int iSuper = 0; iSuper < resultStringList.size(); iSuper++) {
//            for (int i = 0; i < stopAtOneFewerEachLoop-1; i++) {
//                String currentIndex = resultStringList.get(i);
//                String nextIndex = resultStringList.get(i + 1);
//
//                if (currentIndex.compareTo(nextIndex) > 0) {
//                    resultStringList.set(i, nextIndex);
//                    resultStringList.set(i + 1, currentIndex);
//                }
//            }
//            stopAtOneFewerEachLoop--;
//        }
//        return resultStringList;
//    }

//    public List<String> sortResultPrices(List<String> resultStringList) {
//        int stopAtOneFewerEachLoop = resultStringList.size();
//
//        for (int iSuper = 0; iSuper < resultStringList.size(); iSuper++) {
//            for (int i = 0; i < stopAtOneFewerEachLoop-1; i++) {
//                String currentIndex = resultStringList.get(i).substring(1); //removes $ symbol
//                String nextIndex = resultStringList.get(i + 1).substring(1); //removes $ symbol
//
//                if (Double.parseDouble(currentIndex) > Double.parseDouble(nextIndex)) {
//                    resultStringList.set(i, "$" + nextIndex);
//                    resultStringList.set(i + 1, "$" + currentIndex);
//                }
//            }
//            stopAtOneFewerEachLoop--;
//        }
//        return resultStringList;
//    }





}
