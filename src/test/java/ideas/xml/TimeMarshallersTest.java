package ideas.xml;

import org.junit.Test;

public class TimeMarshallersTest {
    private static final PaymentValue PAYMENT_VALUE = new PaymentValue(
            "MobileApp",
            "PAY837137",
            "GroupABC",
            "20150102",
            "USD",
            "123.45",
            "CAHOST",
            null,
            "10010012345",
            "Bob Jones",
            "BO123",
            "IBANK",
            "691216",
            "10011234567",
            "Stevie",
            "From Jones",
            "20150101",
            "123267",
            null,
            null,
            "98729872398734");

    @Test
    public void should_time_each_of_the_different_marshaller_implementations() throws Exception {
        new TimeCode("Original") {
            @Override
            public String bodyToTime() throws Exception {
                return PAYMENT_VALUE.asString(new StringBuilderMarshallPayment());
            }
        }.execute().displayTiming();

        new TimeCode("DOMVisitor") {
            @Override
            public String bodyToTime() throws Exception {
                return PAYMENT_VALUE.asString(new DOMDSLMarshallPayment());
            }
        }.execute().displayTiming();

        new TimeCode("StringBuilderVisitor") {
            @Override
            public String bodyToTime() throws Exception {
                return PAYMENT_VALUE.asString(new StringBuilderDSLMarshallPayment());
            }
        }.execute().displayTiming();

        new TimeCode("FreeMarker") {
            @Override
            public String bodyToTime() throws Exception {
                return PAYMENT_VALUE.asString(new FreeMarkerMarshallPayment());
            }
        }.execute().displayTiming();

        new TimeCode("JAXB") {
            @Override
            public String bodyToTime() throws Exception {
                return PAYMENT_VALUE.asString(new JAXBMarshallPayment());
            }
        }.execute().displayTiming();
    }
}