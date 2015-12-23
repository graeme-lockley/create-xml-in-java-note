package ideas.xml;

import ideas.xml.dsl.DOMVisitor;
import ideas.xml.dsl.XMLElement;
import ideas.xml.dsl.XMLVisitor;

import javax.xml.parsers.ParserConfigurationException;

public class DOMDSLMarshallPayment extends DSLMarshallPayment {
    @Override
    public String marshall(PaymentValue paymentValue) {
        try {
            final XMLElement document = getXmlElement(paymentValue);
            XMLVisitor visitor = new DOMVisitor();
            document.visit(visitor);
            return visitor.asString();
        } catch (ParserConfigurationException e) {
            return null;
        }
    }
}
