package ideas.xml.dsl;

public class XMLAttributesBuilder {
    private XMLAttributes xmlAttributes = new XMLAttributes();

    public XMLAttributes build() {
        return xmlAttributes;
    }

    public XMLAttributesBuilder add(String key, String value) {
        xmlAttributes.add(new XMLAttribute(key, value));
        return this;
    }

    public XMLAttributesBuilder addIf(boolean guard, String key, String value) {
        if (guard) {
            add(key, value);
        }
        return this;
    }

    public XMLAttributesBuilder addIfNotNull(String name, String value) {
        return addIf(value != null, name, value);
    }
}
