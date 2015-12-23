package ideas.xml.dsl;

import java.util.ArrayList;
import java.util.List;

public class XMLBodyBuilder {
    private final List<XMLBody> xmlBodies;

    public XMLBodyBuilder() {
        xmlBodies = new ArrayList<>();
    }

    public List<XMLBody> build() {
        return xmlBodies;
    }

    public XMLBodyBuilder addElement(String name, XMLAttributesBuilder xmlAttributesBuilder) {
        xmlBodies.add(new XMLElement(name, xmlAttributesBuilder));
        return this;
    }

    public XMLBodyBuilder addElement(String name, XMLBodyBuilder xmlBodyBuilder) {
        xmlBodies.add(new XMLElement(name, xmlBodyBuilder));
        return this;
    }

    public XMLBodyBuilder addText(String text) {
        xmlBodies.add(new XMLText(text));
        return this;
    }

    public XMLBodyBuilder addElement(String name, String value) {
        xmlBodies.add(new XMLElement(name, new XMLBodyBuilder().addText(value)));
        return this;
    }

    public XMLBodyBuilder addLiteralIf(boolean condition, String xmlString) {
        if (condition) {
            xmlBodies.add(new XMLBodyLiteral(xmlString));
        }
        return this;
    }

    public XMLBodyBuilder addLiteralIfNotNull(String xmlString) {
        return addLiteralIf(xmlString != null, xmlString);
    }
}
