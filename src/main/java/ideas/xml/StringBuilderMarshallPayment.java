package ideas.xml;

import static ideas.xml.dsl.XMLEncoder.encode;

public class StringBuilderMarshallPayment implements MarshallPayment {
    @Override
    public String marshall(PaymentValue paymentValueValue) {
        StringBuilder marshallResult = new StringBuilder();

        marshallResult
                .append("<payment>")
                .append("<creator application-name=")
                .append("\"")
                .append(paymentValueValue.creatorApplicationName)
                .append("\"")
                .append(" client-id=")
                .append("\"")
                .append(paymentValueValue.creatorClientID)
                .append("\"")
                .append(" correlation-id=")
                .append("\"")
                .append(paymentValueValue.correlationID)
                .append("\"")
                .append("/>")
                .append("<reference>").append(paymentValueValue.reference).append("</reference>")
                .append("<when").append(" year=").append("\"").append(paymentValueValue.transactionDate.substring(0, 4)).append("\"")
                .append(" month=").append("\"").append(paymentValueValue.transactionDate.substring(4, 6)).append("\"")
                .append(" day=").append("\"").append(paymentValueValue.transactionDate.substring(6, 8)).append("\"")
                .append(" hour=").append("\"").append(paymentValueValue.transactionTime.substring(0, 2)).append("\"")
                .append(" minute=").append("\"").append(paymentValueValue.transactionTime.substring(2, 4)).append("\"")
                .append(" second=").append("\"").append(paymentValueValue.transactionTime.substring(4, 6)).append("\"")
                .append("/>")
                .append("<action-date").append(" year=").append("\"").append(paymentValueValue.valueDate.substring(0, 4)).append("\"")
                .append(" month=").append("\"").append(paymentValueValue.valueDate.substring(4, 6)).append("\"")
                .append(" day=").append("\"").append(paymentValueValue.valueDate.substring(6, 8)).append("\"")
                .append("/>")
                .append("<currency>").append(paymentValueValue.currency).append("</currency>")
                .append("<amount>").append(paymentValueValue.amount).append("</amount>")
                .append("<debit-leg").append(" application-name=").append("\"").append(paymentValueValue.debitApplication).append("\"");
        if (paymentValueValue.debitSortCode != null) {
            marshallResult.append(" sort-code=").append("\"").append(paymentValueValue.debitSortCode).append("\"");
        }
        if (paymentValueValue.debitAccountNumber != null) {
            marshallResult.append(" account=").append("\"").append(paymentValueValue.debitAccountNumber).append("\"");
        }
        if (paymentValueValue.debitAccountName != null) {
            marshallResult.append(" name=").append("\"").append(encode(paymentValueValue.debitAccountName)).append("\"");
        }
        if (paymentValueValue.debitPaymentReference != null) {
            marshallResult.append(" reference=").append("\"").append(encode(paymentValueValue.debitPaymentReference)).append("\"");
        }
        if (paymentValueValue.debitLedgerAccountNumber != null) {
            marshallResult.append(" ledger-account-number=").append("\"").append(paymentValueValue.debitLedgerAccountNumber).append("\"");
        }
        marshallResult
                .append("/>")
                .append("<credit-leg")
                .append(" application-name=")
                .append("\"")
                .append(paymentValueValue.creditApplication)
                .append("\"");
        if (paymentValueValue.creditSortCode != null) {
            marshallResult.append(" sort-code=").append("\"").append(paymentValueValue.creditSortCode).append("\"");
        }
        if (paymentValueValue.creditAccountNumber != null) {
            marshallResult.append(" account=").append("\"").append(paymentValueValue.creditAccountNumber).append("\"");
        }
        if (paymentValueValue.creditAccountName != null) {
            marshallResult.append(" name=").append("\"").append(encode(paymentValueValue.creditAccountName)).append("\"");
        }
        if (paymentValueValue.creditPaymentReference != null) {
            marshallResult.append(" reference=").append("\"").append(encode(paymentValueValue.creditPaymentReference)).append("\"");
        }
        if (paymentValueValue.creditLedgeAccountNumber != null) {
            marshallResult.append(" ledger-account-number=").append("\"").append(paymentValueValue.creditLedgeAccountNumber).append("\"");
        }
        marshallResult
                .append("/>")
                .append("</payment>");

        return marshallResult.toString();
    }
}
