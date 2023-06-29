package be.intecbrussel.model;

public class Word {
    private String name;
    private String definition;
    private String keywords;

    public Word(String name, String def, String keywords) {
        this.name = name;
        this.definition = def;
        this.keywords = keywords;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public String getKeywords() {
        return keywords;
    }
}
