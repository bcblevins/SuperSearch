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
    private static String phoneString = "" +
                    "(" +                //begin area code group
                    "\\(\\d{3}\\)\\s" +  //first area code pattern ex. "(123) "
                    "|" +                //OR
                    "\\d{3}-" +          //second area code pattern ex "123-"
                    ")?" +               //end area code group, make whole group optional
                    "\\d{3}-\\d{4}";     //rest of phone number ex. "123-4567"
    public static Pattern phonePattern = Pattern.compile(phoneString);

    //For email
    private static String emailString = "" +
            "[" +      //BEGIN custom character class
            "a-z" +    //any lowercase letter
            "A-Z" +    //any uppercase letter
            "0-9" +    //any digit
            "_.-" +    //underscores, periods, dashes
            "]+" +     //END character class, previous class can occur 1-infinite times
            "@" +      //just an @ symbol
            "[a-zA-Z0-9_.-]+"; //same character class as before @, 1-infinite times
    public static Pattern emailPattern = Pattern.compile(emailString);

    //For prices
    private static String priceString = "" +
            "\\$" +        //dollar Sign
            "(" +          //start leading numbers group
            "\\d{1,3}" +   //between 1 and 3 digits,
            ",?" +         //0 or 1 comma
            ")+" +         //end group, can occur 1-infinite times
            "\\." +        //decimal
            "\\d{2}";      //2 digits (cents)
    public static Pattern pricePattern = Pattern.compile(priceString);

}
