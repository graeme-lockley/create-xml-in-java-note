package ideas.xml;

public class PaymentValue {
    public final String creatorApplicationName;
    public final String reference;
    public final String creatorClientID;
    public final String valueDate;
    public final String currency;
    public final String amount;
    public final String debitApplication;
    public final String debitSortCode;
    public final String debitAccountNumber;
    public final String debitAccountName;
    public final String debitPaymentReference;
    public final String creditApplication;
    public final String creditSortCode;
    public final String creditAccountNumber;
    public final String creditAccountName;
    public final String creditPaymentReference;
    public final String transactionDate;
    public final String transactionTime;
    public final String debitLedgerAccountNumber;
    public final String creditLedgeAccountNumber;
    public final String channelUsed;

    public PaymentValue(
            String creatorApplicationName,
            String reference,
            String creatorClientID,
            String valueDate,
            String currency,
            String amount,
            String debitApplication,
            String debitSortCode,
            String debitAccountNumber,
            String debitAccountName,
            String debitPaymentReference,
            String creditApplication,
            String creditSortCode,
            String creditAccountNumber,
            String creditAccountName,
            String creditPaymentReference,
            String transactionDate,
            String transactionTime,
            String debitLedgerAccountNumber,
            String creditLedgeAccountNumber,
            String channelUsed) {
        this.creatorApplicationName = creatorApplicationName;
        this.reference = reference;
        this.creatorClientID = creatorClientID;
        this.valueDate = valueDate;
        this.currency = currency;
        this.amount = amount;
        this.debitApplication = debitApplication;
        this.debitSortCode = debitSortCode;
        this.debitAccountNumber = debitAccountNumber;
        this.debitAccountName = debitAccountName;
        this.debitPaymentReference = debitPaymentReference;
        this.creditApplication = creditApplication;
        this.creditSortCode = creditSortCode;
        this.creditAccountNumber = creditAccountNumber;
        this.creditAccountName = creditAccountName;
        this.creditPaymentReference = creditPaymentReference;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
        this.debitLedgerAccountNumber = debitLedgerAccountNumber;
        this.creditLedgeAccountNumber = creditLedgeAccountNumber;
        this.channelUsed = channelUsed;
    }


    public String asString(MarshallPayment marshaller) {
        return marshaller.marshall(this);
    }
}
