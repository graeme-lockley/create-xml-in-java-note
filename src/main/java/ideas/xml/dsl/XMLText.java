package ideas.xml.dsl;

public class XMLText implements XMLBody {
    private final String text;

    public XMLText(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }

    public void visit(XMLVisitor visitor) {
        visitor.visit(this);
    }
}
