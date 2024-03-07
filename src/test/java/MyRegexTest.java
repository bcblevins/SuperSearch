import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyRegexTest {
    /*
    --no specials or escapes
    digits
    letters
    alphaNum
    space
    escapes
     */
    //--------------------
    // regexBuilder Tests
    //--------------------
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
    //--------------------------------
    // createRegexPatternMap tests
    //--------------------------------
    /*
        - top level map contains all categories
        - category maps contain all subcategories
        - subcategory maps contain all patterns
    */
    private Map<String, Map<String, Map<String, String>>> mainRegexMap;
    private List<String> categoriesInFile;

    @Before
    public void create_regex_Map_for_testing(){
        mainRegexMap = MyRegex.createRegexPatternMap();
        // read regex file and add categories to list
        categoriesInFile = new ArrayList<>();
        File myRegex = new File("./src/main/resources/my-regex.dat");
        try (Scanner dataInput = new Scanner(myRegex)) {
            while (dataInput.hasNextLine()) {
                String currentLine = dataInput.nextLine();
                if (!currentLine.isEmpty()) {
                    String[] currentLineArray = currentLine.split("\\|");
                    String category = currentLineArray[0];
                    if (!categoriesInFile.contains(category)) {
                        categoriesInFile.add(category);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("my-regex.dat file not found");
        }


    }

    @Test
    public void main_regex_map_should_contain_all_categories() {
        for (String category : categoriesInFile) {
            Assert.assertTrue("mainRegexMap should contain all categories in my-regex.dat", mainRegexMap.containsKey(category));
        }
    }

    @Test
    public void regexMap_should_not_contain_extra_category_keys() {
        Assert.assertEquals("mainRegexMap should not contain extra keys", categoriesInFile.size(), mainRegexMap.size());
    }

    // I really tried to think of ways to test the nested maps but I had a lot of trouble and needed to move on :(

}