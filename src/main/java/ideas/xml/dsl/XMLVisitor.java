package ideas.xml.dsl;

public interface XMLVisitor {
    void visit(XMLElement xmlElement);

    void visit(XMLText xmlText);

    void visit(XMLBodyLiteral xmlBodyLiteral);

    String asString();
}
