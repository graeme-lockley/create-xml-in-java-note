package ideas.xml.dsl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XMLElement implements XMLBody {
    private final String name;
    private final XMLAttributes attributes;
    private final List<XMLBody> bodyElements;

    public XMLElement(String name, XMLAttributes attributes, List<XMLBody> bodyElements) {
        this.name = name;
        this.attributes = attributes;
        this.bodyElements = bodyElements;
    }

    public XMLElement(String name, XMLAttributesBuilder attributesBuilder) {
        this.name = name;
        this.attributes = attributesBuilder.build();
        this.bodyElements = new ArrayList<>();
    }

    public XMLElement(String name, XMLBodyBuilder xmlBodyBuilder) {
        this.name = name;
        this.attributes = new XMLAttributes();
        this.bodyElements = xmlBodyBuilder.build();
    }

    public XMLElement(String name, XMLAttributesBuilder attributesBuilder, XMLBodyBuilder bodyBuilder) {
        this.name = name;
        this.attributes = attributesBuilder.build();
        this.bodyElements = bodyBuilder.build();
    }

    public String name() {
        return name;
    }

    public Collection<XMLBody> bodyElements() {
        return bodyElements;
    }

    public Collection<XMLAttribute> attributes() {
        return attributes.attributes();
    }

    public void visit(XMLVisitor visitor) {
        visitor.visit(this);
    }
}
