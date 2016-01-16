package ideas.xml;

import generated.Payment;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.StringWriter;

public abstract class JAXBMarshallPayment implements MarshallPayment {
    public JAXBMarshallPayment() {
    }

    @Override
    public final String marshall(PaymentValue paymentValueValue) {
        generated.Payment payment = getPayment(paymentValueValue);

        try {
            StringWriter stringWriter = new StringWriter();
            getMarshaller().marshal(payment, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    public abstract Marshaller getMarshaller();

    protected Payment getPayment(PaymentValue paymentValueValue) {
        generated.CreatorType createApplication = new generated.CreatorType();
        createApplication.setApplicationName(paymentValueValue.creatorApplicationName);
        createApplication.setClientId(paymentValueValue.creatorClientID);
        createApplication.setCorrelationId(paymentValueValue.correlationID);

        generated.TimestampType when = new generated.TimestampType();
        when.setYear(paymentValueValue.transactionDate.substring(0, 4));
        when.setMonth(paymentValueValue.transactionDate.substring(4, 6));
        when.setDay(paymentValueValue.transactionDate.substring(6, 8));
        when.setHour(paymentValueValue.transactionTime.substring(0, 2));
        when.setMinute(paymentValueValue.transactionTime.substring(2, 4));
        when.setSecond(paymentValueValue.transactionTime.substring(4, 6));

        generated.ActionDateType actionDate = new generated.ActionDateType();
        actionDate.setYear(paymentValueValue.valueDate.substring(0, 4));
        actionDate.setMonth(paymentValueValue.valueDate.substring(4, 6));
        actionDate.setDay(paymentValueValue.valueDate.substring(6, 8));

        generated.TransactionLegType debitLeg = new generated.TransactionLegType();
        debitLeg.setApplicationName(paymentValueValue.debitApplication);
        debitLeg.setSortCode(paymentValueValue.debitSortCode);
        debitLeg.setAccount(paymentValueValue.debitAccountNumber);
        debitLeg.setName(paymentValueValue.debitAccountName);
        debitLeg.setReference(paymentValueValue.debitPaymentReference);
        debitLeg.setLedgerAccountNumber(paymentValueValue.debitLedgerAccountNumber);

        generated.TransactionLegType creditLeg = new generated.TransactionLegType();
        creditLeg.setApplicationName(paymentValueValue.creditApplication);
        creditLeg.setSortCode(paymentValueValue.creditSortCode);
        creditLeg.setAccount(paymentValueValue.creditAccountNumber);
        creditLeg.setName(paymentValueValue.creditAccountName);
        creditLeg.setReference(paymentValueValue.creditPaymentReference);
        creditLeg.setLedgerAccountNumber(paymentValueValue.creditLedgeAccountNumber);

        Payment payment = new Payment();
        payment.setCreator(createApplication);
        payment.setReference(paymentValueValue.reference);
        payment.setWhen(when);
        payment.setActionDate(actionDate);
        payment.setCurrency(paymentValueValue.currency);
        payment.setAmount(paymentValueValue.amount);
        payment.setDebitLeg(debitLeg);
        payment.setCreditLeg(creditLeg);

        return payment;
    }

    public static Marshaller createMarshaller(Class classToBind) {
        try {
            JAXBContext context = JAXBContext.newInstance(classToBind);

            Marshaller marshall = context.createMarshaller();
            marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            marshall.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            return marshall;
        } catch (JAXBException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void attachSchema(Marshaller marshaller, String schemaName) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(schemaName));
            marshaller.setSchema(schema);
        } catch (SAXException ex) {
            throw new RuntimeException(ex);
        }
    }
}
