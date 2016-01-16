package ideas.xml;

import generated.Payment;

import javax.xml.bind.Marshaller;

public class NaiveSchemalessJAXBMarshallPayment  extends JAXBMarshallPayment {
    public NaiveSchemalessJAXBMarshallPayment() {
    }

    @Override
    public Marshaller getMarshaller() {
        return createMarshaller(Payment.class);
    }
}