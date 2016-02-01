package ideas.xml.dsl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Optional;

public class DOMVisitor implements XMLVisitor {
    private final Document doc;
    private Optional<Element> currentElement = Optional.empty();
    private static DocumentBuilder docBuilder;
    private static Transformer transformer;

    static {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();

            TransformerFactory tf = TransformerFactory.newInstance();
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        } catch (ParserConfigurationException | TransformerConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }

    public DOMVisitor() throws ParserConfigurationException {
        doc = docBuilder.newDocument();
    }

    public void visit(XMLElement xmlElement) {
        Optional<Element> previous = currentElement;
        Element newElement = doc.createElement(xmlElement.name());

        currentElement.map(x -> (Node) x).orElse(doc).appendChild(newElement);
        currentElement = Optional.of(newElement);

        for (XMLAttribute attr : xmlElement.attributes()) {
            currentElement.get().setAttribute(attr.name(), attr.value());
        }

        for (XMLBody body : xmlElement.bodyElements()) {
            body.visit(this);
        }

        currentElement = previous;
    }

    public void visit(XMLText xmlText) {
        currentElement.orElseThrow(() -> new IllegalArgumentException("Unable to attach a text block at the root of an XML document"));

        final Text textNode = doc.createTextNode(xmlText.text());
        currentElement.get().appendChild(textNode);
    }

    public void visit(XMLBodyLiteral xmlBodyLiteral) {
        currentElement.orElseThrow(() -> new IllegalArgumentException("Unable to attach a text block at the root of an XML document"));

        final Text textNode = doc.createTextNode(xmlBodyLiteral.xmlString());
        currentElement.get().appendChild(textNode);
    }

    public String asString() {
        try {
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (TransformerException ex) {
            throw new RuntimeException(ex);
        }
    }
}
