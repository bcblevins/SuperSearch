import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyRegexTest {
    /*
    --no specials or escapes
    digits
    letters
    alphaNum
    space
    escapes
    normal and escapes
    normal and specials
     */
    @Test
    public void regexBuilder_should_return_input_string_if_no_specials_or_escapes(){
        Assert.assertEquals("regexBuilder should return the same string back without spaces if no escape or special characters used",
                "123hello",
                MyRegex.regexBuilder("1 23 hello"));
    }
    @Test
    public  void regexBuilder_should_convert_digit_to_regex(){
        Assert.assertEquals("regexBuilder should return regex equivalent if passed \"digit\"",
                "\\d\\d\\d", MyRegex.regexBuilder("digit digit digit"));
    }
    @Test
    public void regexBuilder_should_convert_letter_to_regex(){
        Assert.assertEquals("regexBuilder should return regex equivalent if passed \"letter\"",
                "[a-zA-Z][a-zA-Z][a-zA-Z]",
                MyRegex.regexBuilder("letter letter letter"));
    }



}