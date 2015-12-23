package ideas.xml.dsl;

public class XMLBuilder {
    public static XMLElement document(String name, XMLAttributesBuilder attributesBuilder, XMLBodyBuilder bodyBuilder) {
        return new XMLElement(name, attributesBuilder, bodyBuilder);
    }

    public static XMLElement document(String name, XMLBodyBuilder bodyBuilder) {
        return document(name, new XMLAttributesBuilder(), bodyBuilder);
    }

    public static XMLAttributesBuilder attributes() {
        return new XMLAttributesBuilder();
    }

    public static XMLBodyBuilder body() {
        return new XMLBodyBuilder();
    }

    public static XMLAttribute attribute(String name, String value) {
        return new XMLAttribute(name, value);
    }

    public static XMLText text(String text) {
        return new XMLText(text);
    }
}
