package ideas.xml.dsl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class DOMVisitor implements XMLVisitor {
    private final Document doc;
    private Element currentElement = null;

    public DOMVisitor() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        doc = docBuilder.newDocument();
    }

    public void visit(XMLElement xmlElement) {
        Element previous = currentElement;
        Element newElement = doc.createElement(xmlElement.name());

        if (currentElement == null) {
            doc.appendChild(newElement);
        } else {
            currentElement.appendChild(newElement);
        }
        currentElement = newElement;

        for (XMLAttribute attr : xmlElement.attributes()) {
            currentElement.setAttribute(attr.name(), attr.value());
        }

        for (XMLBody body : xmlElement.bodyElements()) {
            body.visit(this);
        }

        currentElement = previous;
    }

    public void visit(XMLText xmlText) {
        if (currentElement == null) {
            throw new IllegalArgumentException("Unable to attach a text block at the root of an XML document");
        } else {
            final Text textNode = doc.createTextNode(xmlText.text());
            currentElement.appendChild(textNode);
        }
    }

    public void visit(XMLBodyLiteral xmlBodyLiteral) {
        if (currentElement == null) {
            throw new IllegalArgumentException("Unable to attach a text block at the root of an XML document");
        } else {
            final Text textNode = doc.createTextNode(xmlBodyLiteral.xmlString());
            currentElement.appendChild(textNode);
        }
    }

    public String asString() {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (TransformerException ex) {
            throw new RuntimeException(ex);
        }
    }
}
