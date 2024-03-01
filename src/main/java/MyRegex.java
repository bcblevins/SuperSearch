
/*
    General Regex characters:
    //d = digit "1234567890"
    //s = space " "
    ? = zero or one of the pattern before it.
    | = OR - will match if string has pattern before OR after pipe character
    {#} = how many times something happens. "\\d{3}" means 3 digits or "\\d\\d\\d"
    () = used for grouping
    [] = defines a character set. ex [\\da-z] will match any number or lowercase letter
     */
public class MyRegex {
    private static final String ESCAPED_CHARACTERS = "\\.[]{}()<>*+-=!?^$|";

    //For phone numbers
    public static String phoneRegex = "" +
            "(" +                //begin area code group
            "\\(\\d{3}\\)\\s" +  //first area code pattern ex. "(123) "
            "|" +                //OR
            "\\d{3}-" +          //second area code pattern ex "123-"
            ")?" +               //end area code group, make whole group optional
            "\\d{3}-\\d{4}";     //rest of phone number ex. "123-4567"

    //For email
    public static String emailRegex = "" +
            "[" +      //BEGIN custom character class
            "a-z" +    //any lowercase letter
            "A-Z" +    //any uppercase letter
            "0-9" +    //any digit
            "_.-" +    //underscores, periods, dashes
            "]+" +     //END character class, previous class can occur 1-infinite times
            "@" +      //just an @ symbol
            "[a-zA-Z0-9_.-]+"; //same character class as before @, 1-infinite times

    //For prices
    public static String priceRegex = "" +
            "\\$" +        //dollar Sign
            "(" +          //start leading numbers group
            "\\d{1,3}" +   //between 1 and 3 digits,
            ",?" +         //0 or 1 comma
            ")+" +         //end group, can occur 1-infinite times
            "\\." +        //decimal
            "\\d{2}";      //2 digits (cents)

    public static String regexBuilder(String userPattern) {
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
}
