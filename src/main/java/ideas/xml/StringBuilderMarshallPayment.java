package ideas.xml;

import static ideas.xml.dsl.XMLEncoder.encode;

public class StringBuilderMarshallPayment implements MarshallPayment {
    @Override
    public String marshall(PaymentValue paymentValue) {
        StringBuilder marshallResult = new StringBuilder();

        marshallResult
                .append("<payment>")
                .append("<creator application-name=")
                .append("\"")
                .append(paymentValue.creatorApplicationName)
                .append("\"")
                .append(" client-id=")
                .append("\"")
                .append(paymentValue.creatorClientID)
                .append("\"")
                .append(" correlation-id=")
                .append("\"")
                .append(paymentValue.correlationID)
                .append("\"")
                .append("/>")
                .append("<reference>").append(paymentValue.reference).append("</reference>")
                .append("<when").append(" year=").append("\"").append(paymentValue.transactionDate.substring(0, 4)).append("\"")
                .append(" month=").append("\"").append(paymentValue.transactionDate.substring(4, 6)).append("\"")
                .append(" day=").append("\"").append(paymentValue.transactionDate.substring(6, 8)).append("\"")
                .append(" hour=").append("\"").append(paymentValue.transactionTime.substring(0, 2)).append("\"")
                .append(" minute=").append("\"").append(paymentValue.transactionTime.substring(2, 4)).append("\"")
                .append(" second=").append("\"").append(paymentValue.transactionTime.substring(4, 6)).append("\"")
                .append("/>")
                .append("<action-date").append(" year=").append("\"").append(paymentValue.valueDate.substring(0, 4)).append("\"")
                .append(" month=").append("\"").append(paymentValue.valueDate.substring(4, 6)).append("\"")
                .append(" day=").append("\"").append(paymentValue.valueDate.substring(6, 8)).append("\"")
                .append("/>")
                .append("<currency>").append(paymentValue.currency).append("</currency>")
                .append("<amount>").append(paymentValue.amount).append("</amount>")
                .append("<debit-leg").append(" application-name=").append("\"").append(paymentValue.debitApplication).append("\"");
        if (paymentValue.debitSortCode != null) {
            marshallResult.append(" sort-code=").append("\"").append(paymentValue.debitSortCode).append("\"");
        }
        if (paymentValue.debitAccountNumber != null) {
            marshallResult.append(" account=").append("\"").append(paymentValue.debitAccountNumber).append("\"");
        }
        if (paymentValue.debitAccountName != null) {
            marshallResult.append(" name=").append("\"").append(encode(paymentValue.debitAccountName)).append("\"");
        }
        if (paymentValue.debitPaymentReference != null) {
            marshallResult.append(" reference=").append("\"").append(encode(paymentValue.debitPaymentReference)).append("\"");
        }
        if (paymentValue.debitLedgerAccountNumber != null) {
            marshallResult.append(" ledger-account-number=").append("\"").append(paymentValue.debitLedgerAccountNumber).append("\"");
        }
        marshallResult
                .append("/>")
                .append("<credit-leg")
                .append(" application-name=")
                .append("\"")
                .append(paymentValue.creditApplication)
                .append("\"");
        if (paymentValue.creditSortCode != null) {
            marshallResult.append(" sort-code=").append("\"").append(paymentValue.creditSortCode).append("\"");
        }
        if (paymentValue.creditAccountNumber != null) {
            marshallResult.append(" account=").append("\"").append(paymentValue.creditAccountNumber).append("\"");
        }
        if (paymentValue.creditAccountName != null) {
            marshallResult.append(" name=").append("\"").append(encode(paymentValue.creditAccountName)).append("\"");
        }
        if (paymentValue.creditPaymentReference != null) {
            marshallResult.append(" reference=").append("\"").append(encode(paymentValue.creditPaymentReference)).append("\"");
        }
        if (paymentValue.creditLedgeAccountNumber != null) {
            marshallResult.append(" ledger-account-number=").append("\"").append(paymentValue.creditLedgeAccountNumber).append("\"");
        }
        marshallResult
                .append("/>")
                .append("</payment>");

        return marshallResult.toString();
    }
}
