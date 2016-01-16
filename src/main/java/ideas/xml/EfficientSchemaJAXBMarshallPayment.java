package ideas.xml;

import javax.xml.bind.Marshaller;

public class EfficientSchemaJAXBMarshallPayment extends JAXBMarshallPayment {
    protected static final Marshaller m;

    static {
        m = createMarshaller(generated.Payment.class);
        attachSchema(m, "target/classes/Payment.xsd");
    }

    @Override
    public Marshaller getMarshaller() {
        return m;
    }
}
