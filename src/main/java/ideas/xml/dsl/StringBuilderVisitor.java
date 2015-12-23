package ideas.xml.dsl;

import javax.xml.parsers.ParserConfigurationException;

public class StringBuilderVisitor implements XMLVisitor {
    private final StringBuilder result = new StringBuilder();

    public StringBuilderVisitor() throws ParserConfigurationException {
    }

    public void visit(XMLElement xmlElement) {
        result.append("<").append(xmlElement.name());

        for (XMLAttribute attr : xmlElement.attributes()) {
            result.append(" ").append(attr.name()).append("='").append(XMLEncoder.encodeAllExceptApostrophe(attr.value())).append("'");
        }

        if (xmlElement.bodyElements().isEmpty()) {
            result.append("/>");
        } else {
            result.append(">");
            for (XMLBody body : xmlElement.bodyElements()) {
                body.visit(this);
            }
            result.append("</").append(xmlElement.name()).append(">");
        }
    }

    public void visit(XMLText xmlText) {
        result.append(XMLEncoder.encode(xmlText.text()));
    }

    public void visit(XMLBodyLiteral xmlBodyLiteral) {
        result.append(xmlBodyLiteral.xmlString());
    }

    public String asString() {
        return result.toString();
    }

}
