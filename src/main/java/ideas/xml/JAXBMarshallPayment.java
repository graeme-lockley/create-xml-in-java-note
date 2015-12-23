package ideas.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class JAXBMarshallPayment implements MarshallPayment {
    @Override
    public String marshall(PaymentValue paymentValueValue) {
        generated.CreatorType createApplication = new generated.CreatorType();
        createApplication.setApplicationName(paymentValueValue.creatorApplicationName);
        createApplication.setClientId(paymentValueValue.creatorClientID);
        createApplication.setChannelId(paymentValueValue.channelUsed);

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

        generated.Payment payment = new generated.Payment();
        payment.setCreator(createApplication);
        payment.setReference(paymentValueValue.reference);
        payment.setWhen(when);
        payment.setActionDate(actionDate);
        payment.setCurrency(paymentValueValue.currency);
        payment.setAmount(paymentValueValue.amount);
        payment.setDebitLeg(debitLeg);
        payment.setCreditLeg(creditLeg);

        try {
            JAXBContext context = JAXBContext.newInstance(generated.Payment.class);
            Marshaller m = context.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            m.marshal(payment, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}