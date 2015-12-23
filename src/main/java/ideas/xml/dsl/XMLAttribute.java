package ideas.xml.dsl;

public class XMLAttribute {
    private final String name;
    private final String value;

    public XMLAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public String value() {
        return value;
    }
}
