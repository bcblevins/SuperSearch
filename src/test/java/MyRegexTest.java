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
     */
    @Test
    public void regexBuilder_should_return_input_string_if_no_specials_or_escapes(){
        Assert.assertEquals("regexBuilder should return the same string back without spaces if no escape or special characters used",
                "123hello",
                MyRegex.translateUserPattern("1 23 hello"));
    }
    @Test
    public  void regexBuilder_should_convert_digit_to_regex(){
        Assert.assertEquals("regexBuilder should return regex equivalent if passed \"digit\"",
                "\\d\\d\\d", MyRegex.translateUserPattern("digit digit digit"));
        Assert.assertEquals("regexBuilder should return regex equivalent combined with normal characters if passed \"digit\" and normal characters",
                "\\d\\d\\dIdon'tcarelane", MyRegex.translateUserPattern("digit digit digit I don't care lane"));
    }
    @Test
    public void regexBuilder_should_convert_letter_to_regex(){
        Assert.assertEquals("regexBuilder should return regex equivalent if passed \"letter\"",
                "[a-zA-Z][a-zA-Z][a-zA-Z]",
                MyRegex.translateUserPattern("letter letter letter"));
        Assert.assertEquals("regexBuilder should return regex equivalent combined with normal characters if passed \"letter\" and normal characters",
                "[a-zA-Z]a[a-zA-Z]b[a-zA-Z]c", MyRegex.translateUserPattern("letter a letter b letter c"));
    }
    @Test
    public void regexBuilder_should_convert_alphaNum_to_regex(){
        Assert.assertEquals("regexBuilder should return regex equivalent if passed \"alphaNum\"",
                "[a-zA-Z0-9][a-zA-Z0-9][a-zA-Z0-9]", MyRegex.translateUserPattern("alphaNum alphaNum alphaNum"));
        Assert.assertEquals("regexBuilder should return regex equivalent combined with normal characters if passed \"alphaNum\" and normal characters",
                "yourNumber[a-zA-Z0-9][a-zA-Z0-9]", MyRegex.translateUserPattern("yourNumber alphaNum alphaNum"));
    }
    @Test
    public void regexBuilder_should_convert_space_to_regex(){
        Assert.assertEquals("regexBuilder should return regex equivalent if passed \"space\"",
                "   ",  MyRegex.translateUserPattern("space space space"));
        Assert.assertEquals("regexBuilder should return regex equivalent combined with normal characters if passed \"space\" and normal characters",
                "My name", MyRegex.translateUserPattern("My space name"));
    }
    @Test
    public void regexBuilder_should_escape_regex_characters(){
        Assert.assertEquals("regexBuilder should ass \\ to regex characters",
                "\\\\\\.\\[\\]\\{\\}\\(\\)\\<\\>\\*\\+\\-\\=\\!\\?\\^\\$\\|", MyRegex.translateUserPattern("\\ . [ ] { } ( ) < > * + - = ! ? ^ $ |"));
    }

}