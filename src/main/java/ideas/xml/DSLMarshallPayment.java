package ideas.xml;

import ideas.xml.dsl.XMLElement;

import static ideas.xml.dsl.XMLBuilder.*;

public abstract class DSLMarshallPayment implements MarshallPayment {
    protected static XMLElement getXmlElement(PaymentValue value) {
        return document("payment",
                body()
                        .addElement("creator",
                                attributes()
                                        .add("application-name", value.creatorApplicationName)
                                        .add("client-id", value.creatorClientID)
                                        .add("correlation-id", value.correlationID))
                        .addElement("reference", value.reference)
                        .addElement("when",
                                attributes()
                                        .add("year", value.transactionDate.substring(0, 4))
                                        .add("month", value.transactionDate.substring(4, 6))
                                        .add("day", value.transactionDate.substring(6, 8))
                                        .add("hour", value.transactionTime.substring(0, 2))
                                        .add("minute", value.transactionTime.substring(2, 4))
                                        .add("second", value.transactionTime.substring(4, 6)))
                        .addElement("action-date",
                                attributes()
                                        .add("year", value.valueDate.substring(0, 4))
                                        .add("month", value.valueDate.substring(4, 6))
                                        .add("day", value.valueDate.substring(6, 8)))
                        .addElement("currency", value.currency)
                        .addElement("amount", value.amount)
                        .addElement("debit-leg",
                                attributes()
                                        .add("application-name", value.debitApplication)
                                        .addIfNotNull("sort-code", value.debitSortCode)
                                        .addIfNotNull("account", value.debitAccountNumber)
                                        .addIfNotNull("name", value.debitAccountName)
                                        .addIfNotNull("reference", value.debitPaymentReference)
                                        .addIfNotNull("ledger-account-number", value.debitLedgerAccountNumber))
                        .addElement("credit-leg",
                                attributes()
                                        .add("application-name", value.creditApplication)
                                        .addIfNotNull("sort-code", value.creditSortCode)
                                        .addIfNotNull("account", value.creditAccountNumber)
                                        .addIfNotNull("name", value.creditAccountName)
                                        .addIfNotNull("reference", value.creditPaymentReference)
                                        .addIfNotNull("ledger-account-number", value.creditLedgeAccountNumber))
        );
    }
}
