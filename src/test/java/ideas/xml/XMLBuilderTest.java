package ideas.xml;

import org.junit.Test;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;

import static org.junit.Assert.assertFalse;

public class XMLBuilderTest {
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
    public void dom_dsl_marshall_should_equal_the_jaxb_marshall() throws Exception {
        final Diff diff = DiffBuilder
                .compare(Input.fromString(PAYMENT_VALUE.asString(new DOMDSLMarshallPayment())))
                .withTest(Input.fromString(PAYMENT_VALUE.asString(new JAXBMarshallPayment())))
                .build();

        assertFalse(diff.hasDifferences());
    }

    @Test
    public void dom_dsl_marshall_should_equal_the_string_builder_marshall() throws Exception {
        final Diff diff = DiffBuilder
                .compare(Input.fromString(PAYMENT_VALUE.asString(new DOMDSLMarshallPayment())))
                .withTest(Input.fromString(PAYMENT_VALUE.asString(new StringBuilderMarshallPayment())))
                .build();

        assertFalse(diff.hasDifferences());
    }

    @Test
    public void dom_dsl_marshall_should_equal_the_string_builder__dsl_marshall() throws Exception {
        final Diff diff = DiffBuilder
                .compare(Input.fromString(PAYMENT_VALUE.asString(new DOMDSLMarshallPayment())))
                .withTest(Input.fromString(PAYMENT_VALUE.asString(new StringBuilderDSLMarshallPayment())))
                .build();

        assertFalse(diff.hasDifferences());
    }
}