package ideas.xml;

import javax.xml.bind.Marshaller;

public class EfficientSchemalessJAXBMarshallPayment extends JAXBMarshallPayment {
    protected static final Marshaller m;

    static {
        m = createMarshaller(generated.Payment.class);
    }

    @Override
    public Marshaller getMarshaller() {
        return m;
    }
}
