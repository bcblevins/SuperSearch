import java.util.regex.Pattern;

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

    public static String regexBuilder() {
        System.out.println("""
                A pattern is made of a combination of special characters and/or normal characters.
                "For example, "# digit digit space letter" would match a 5 character string: 
                A "#" sign followed by any 2 numbers, a space, and then any letter. ex: "#42 A", "#09 f" """);
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("""
                Using the follow key, enter a pattern separated by spaces you would like to search for:
                
                digit = any number 0-9
                letter = any letter a-z, case insensitive
                alphaNum = any number 0-9, any letter a-z, case insensitive
                space = empty space
                """);
        String userRawPattern = IOSystem.promptForInput("").replace(" ", "");
        String userRegex = userRawPattern
                .replace("digit", "\\d")
                .replace("letter", "[a-zA-Z]")
                .replace("alphaNum", "[a-zA-Z0-9]")
                .replace("space", " ");

        return userRegex;
    }
}
