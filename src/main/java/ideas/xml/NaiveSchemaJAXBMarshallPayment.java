package ideas.xml;

import generated.Payment;

import javax.xml.bind.Marshaller;

public class NaiveSchemaJAXBMarshallPayment extends JAXBMarshallPayment {
    public NaiveSchemaJAXBMarshallPayment() {
    }

    @Override
    public Marshaller getMarshaller() {
        Marshaller marshaller = createMarshaller(Payment.class);
        attachSchema(marshaller, "target/classes/Payment.xsd");
        return marshaller;
    }
}
