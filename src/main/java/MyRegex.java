import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyRegex {
    private static final String ESCAPED_CHARACTERS = "\\.[]{}()<>*+-=!?^$|";
    public static Map<String, Map<String, Map<String, String>>> createRegexPatternMap(){
        //create RegexPatterns and put into list
        List<RegexPattern> regexPatternList = new ArrayList<>();
        File myRegex = new File("./src/main/resources/my-regex.dat");
        try (Scanner dataInput = new Scanner(myRegex)) {
            while (dataInput.hasNextLine()) {
                String[] currentLineArray = dataInput.nextLine().split("\\|");
                if (currentLineArray.length >= 4) {
                    regexPatternList.add(new RegexPattern(currentLineArray[0], currentLineArray[1], currentLineArray[2], currentLineArray[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Regex file not found");
        }
        /*
        The following was a total mind-bender to figure out. I am explaining it here mostly for
        my own benefit, so I can remember what I did. What we need to create is a Map, which contains keys representing
        the major categories of the regex patterns. The values are Maps, which contain keys representing the
        subcategories. The values of this map are maps, which contain keys that match each regex pattern's name, and
        the values are the actual regex patterns. Represented more clearly (hopefully):
        Master Map<Category, Category Map>
        Category Map<Subcategory, Subcategory Map>
        Subcategory Map<Name, Pattern>

        To actually create and fill these maps, we need to start with the master Map that will exist outside the
        RegexPattern loop. It is easiest to understand if we work from inside->out (unfortunately I don't think it can
        be coded this way).

        We have a name and pattern. This needs to be added to its subcategory map if it exists, and if not, we need to
        create one for it. This map then needs to be added to a category map if it exists, and if not, we need to create
        one for it. This then needs to be added to the master map.

        For coding, we have to work backwards. First we get the RegexPattern category and add it to the master map if it
        doesn't exist yet. Then we create a variable for this category map, so we can add things to it. Then we get the
        subcategory and add it to the category map if it doesn't exist yet. We create a variable for this map so we can
        add things to it. Then we add the RegexPattern's name and pattern to the subcategory map. Once we are done, we
        should have a map of maps of maps!
         */

        //create and fill map of maps of maps
        Map<String, Map<String, Map<String, String>>> masterRegexMap = new HashMap<>();
        for (RegexPattern regexPattern : regexPatternList) {
            //Add the category map if it doesn't already exist, then create variable for adding things
            masterRegexMap.putIfAbsent(regexPattern.getCategory(), new HashMap<>());
            Map<String, Map<String, String>> categoryMap = masterRegexMap.get(regexPattern.getCategory());

            //Add subcategory map if it doesn't already exist, then create variable for adding things
            categoryMap.putIfAbsent(regexPattern.getSubcategory(), new HashMap<>());
            Map<String, String> subcategoryMap = categoryMap.get(regexPattern.getSubcategory());

            //Add name and pattern to subcategory map
            subcategoryMap.put(regexPattern.getName(), regexPattern.getPattern());
        }

        return masterRegexMap;
    }

    public static String regexBuilder() {
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
        return translateUserPattern(userPattern);
    }
    public static String translateUserPattern(String userPattern) {
        String[] userRawPattern = userPattern.split(" ");
        StringBuilder userRegex = new StringBuilder();
        for (String character : userRawPattern) {
            if (character.equals("digit")) {
                character = "\\d";
            } else if (character.equalsIgnoreCase("letter")) {
                character = "[a-zA-Z]";
            } else if (character.equalsIgnoreCase("alphaNum")) {
                character = "[a-zA-Z0-9]";
            } else if (character.equalsIgnoreCase("space")) {
                character = " ";
            } else if (ESCAPED_CHARACTERS.contains(character)) {
                character = "\\" + character;
            }
            userRegex.append(character);
        }
        return userRegex.toString();
    }

    public static String selectPattern(){

        Map<String, Map<String, Map<String, String>>> regexPatternMap = MyRegex.createRegexPatternMap();
        Map<String, Map<String, String>> categoryMap;
        Map<String, String> subcategoryMap;

        String categorySelection;
        String subcategorySelection;
        String patternNameSelection;
        String regexPattern;

        //Display categories and select
        IOSystem.printSeparator();
        for (Map.Entry<String, Map<String, Map<String, String>>> category : regexPatternMap.entrySet()) {
            System.out.println(category.getKey());
        }
        while (true) {
            categorySelection = IOSystem.promptForInput("Please enter one of the above categories:");
            if (regexPatternMap.containsKey(categorySelection)) {
                categoryMap = regexPatternMap.get(categorySelection);
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }

        //Display subcategories and select
        IOSystem.printSeparator();
        for (Map.Entry<String, Map<String, String>> subcategory : categoryMap.entrySet()) {
            System.out.println(subcategory.getKey());
        }
        while (true) {
            subcategorySelection = IOSystem.promptForInput("Please enter one of the above subcategories:");
            if (categoryMap.containsKey(subcategorySelection)) {
                subcategoryMap = categoryMap.get(subcategorySelection);
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }

        //Display pattern names and select
        IOSystem.printSeparator();
        for (Map.Entry<String, String> patternName : subcategoryMap.entrySet()) {
            System.out.println(patternName.getKey());
        }
        while (true) {
            patternNameSelection = IOSystem.promptForInput("Please select one of the above patterns:");
            if (subcategoryMap.containsKey(patternNameSelection)) {
                regexPattern = subcategoryMap.get(patternNameSelection);
                break;
            } else {
                System.out.println("Invalid selection.");
            }
        }
        return regexPattern;
    }

    //All regular expressions stored in my-regex.dat were created by me and explained here. Some simple ones or variants not explained.

//
//    General Regex characters:
//    //d = digit "1234567890"
//    //s = white space, can be space, tab, or newline
//    ? = zero or one of the pattern before it.
//    | = OR - will match if string has pattern before OR after pipe character
//    {} = how many times something happens. "\\d{3}" means 3 digits or "\\d\\d\\d"
//    () = used for grouping
//    [] = defines a character set. ex [\\da-z] will match any number or lowercase letter
//

    //For phone numbers
//    public static String phoneRegex = "" +
//            "(" +                //begin area code group
//            "\\(\\d{3}\\)\\s" +  //first area code pattern ex. "(123) "
//            "|" +                //OR
//            "\\d{3}-" +          //second area code pattern ex "123-"
//            ")?" +               //end area code group, make whole group optional
//            "\\d{3}-\\d{4}";     //rest of phone number ex. "123-4567"
//
//    //For email
//    public static String emailRegex = "" +
//            "[" +      //BEGIN custom character class
//            "a-z" +    //any lowercase letter
//            "A-Z" +    //any uppercase letter
//            "0-9" +    //any digit
//            "_.-" +    //underscores, periods, dashes
//            "]+" +     //END character class, previous class can occur 1-infinite times
//            "@" +      //just an @ symbol
//            "[a-zA-Z0-9_.-]+"; //same character class as before @, 1-infinite times
//
//    //For prices
//    public static String priceRegex = "" +
//            "\\$" +        //dollar Sign
//            "(" +          //start leading numbers group
//            "\\d{1,3}" +   //between 1 and 3 digits,
//            ",?" +         //0 or 1 comma
//            ")+" +         //end group, can occur 1-infinite times
//            "\\." +        //decimal
//            "\\d{2}";      //2 digits (cents)
//    public static String addressRegex = "" +
//            "\\d{1,15}\\s" +                 //address number
//            "([a-zA-Z]+\\.?\\s?){1,4},\\s" +  //street name, accounts for multiple words and things like "st.", "ct.", etc.
//            "([a-zA-Z]+\\s?){1,4},\\s" +     //city name, accounts for multiple words
//            "([a-zA-Z]+\\s?){1,4}\\s" +      //State name, accounts for multiple words
//            "\\d{5}";                        //5 digit zip code
//    public static String nameRegex = "" +
//            "[A-Z]" +          //capital first letter of name
//            "[a-z]+" +         //remaining letters of first name lowercase
//            "\\s" +            //space
//            "[A-Z][a-z]+" +    //last name, same format as first
//            "(-[A-Z][a-z]+)?"; //optional hyphenated last name addition
}
