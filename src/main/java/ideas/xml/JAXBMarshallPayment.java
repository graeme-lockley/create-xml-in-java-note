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

    protected Payment getPayment(PaymentValue paymentValue) {
        generated.CreatorType createApplication = new generated.CreatorType();
        createApplication.setApplicationName(paymentValue.creatorApplicationName);
        createApplication.setClientId(paymentValue.creatorClientID);
        createApplication.setCorrelationId(paymentValue.correlationID);

        generated.TimestampType when = new generated.TimestampType();
        when.setYear(paymentValue.transactionDate.substring(0, 4));
        when.setMonth(paymentValue.transactionDate.substring(4, 6));
        when.setDay(paymentValue.transactionDate.substring(6, 8));
        when.setHour(paymentValue.transactionTime.substring(0, 2));
        when.setMinute(paymentValue.transactionTime.substring(2, 4));
        when.setSecond(paymentValue.transactionTime.substring(4, 6));

        generated.ActionDateType actionDate = new generated.ActionDateType();
        actionDate.setYear(paymentValue.valueDate.substring(0, 4));
        actionDate.setMonth(paymentValue.valueDate.substring(4, 6));
        actionDate.setDay(paymentValue.valueDate.substring(6, 8));

        generated.TransactionLegType debitLeg = new generated.TransactionLegType();
        debitLeg.setApplicationName(paymentValue.debitApplication);
        debitLeg.setSortCode(paymentValue.debitSortCode);
        debitLeg.setAccount(paymentValue.debitAccountNumber);
        debitLeg.setName(paymentValue.debitAccountName);
        debitLeg.setReference(paymentValue.debitPaymentReference);
        debitLeg.setLedgerAccountNumber(paymentValue.debitLedgerAccountNumber);

        generated.TransactionLegType creditLeg = new generated.TransactionLegType();
        creditLeg.setApplicationName(paymentValue.creditApplication);
        creditLeg.setSortCode(paymentValue.creditSortCode);
        creditLeg.setAccount(paymentValue.creditAccountNumber);
        creditLeg.setName(paymentValue.creditAccountName);
        creditLeg.setReference(paymentValue.creditPaymentReference);
        creditLeg.setLedgerAccountNumber(paymentValue.creditLedgeAccountNumber);

        Payment payment = new Payment();
        payment.setCreator(createApplication);
        payment.setReference(paymentValue.reference);
        payment.setWhen(when);
        payment.setActionDate(actionDate);
        payment.setCurrency(paymentValue.currency);
        payment.setAmount(paymentValue.amount);
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
