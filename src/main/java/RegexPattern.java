public class RegexPattern {
    private String category;
    private String subcategory;
    private String name;
    private String pattern;

    public RegexPattern(String category, String subcategory, String name, String pattern) {
        this.category = category;
        this.subcategory = subcategory;
        this.name = name;
        this.pattern = pattern;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public String getName() {
        return name;
    }

    public String getPattern() {
        return pattern;
    }
}
