package ideas.xml.dsl;

public class XMLBodyLiteral implements XMLBody {
    private final String xmlString;

    public XMLBodyLiteral(String xmlString) {
        this.xmlString = xmlString;
    }

    public String xmlString() {
        return xmlString;
    }

    public void visit(XMLVisitor visitor) {
        visitor.visit(this);
    }
}
