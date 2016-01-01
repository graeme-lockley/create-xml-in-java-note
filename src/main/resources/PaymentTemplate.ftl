<#escape c as c?xml>
<payment>
    <creator application-name="${payment.creatorApplicationName}" client-id="${payment.creatorClientID}" correlation-id="${payment.correlationID}"/>
    <reference>${payment.reference}</reference>
    <when year="${payment.transactionDate[0..3]}" month="${payment.transactionDate[4..5]}" day="${payment.transactionDate[6..7]}" hour="${payment.transactionTime[0..1]}" minute="${payment.transactionTime[2..3]}" second="${payment.transactionTime[4..5]}"/>
    <action-date year="${payment.valueDate[0..3]}" month="${payment.valueDate[4..5]}" day="${payment.valueDate[6..7]}"/>
    <currency>${payment.currency}</currency>
    <amount>${payment.amount}</amount>
    <debit-leg
        application-name="${payment.debitApplication}"
<#if payment.debitSortCode??>
        sort-code="${payment.debitSortCode}"
</#if>
<#if payment.debitAccountNumber??>
        account="${payment.debitAccountNumber}"
</#if>
<#if payment.debitAccountName??>
        name="${payment.debitAccountName}"
</#if>
<#if payment.debitPaymentReference??>
        reference="${payment.debitPaymentReference}"
</#if>
<#if payment.debitLedgerAccountNumber??>
        ledger-account-number="${payment.debitLedgerAccountNumber}"
</#if>
    />
    <credit-leg
        application-name="${payment.creditApplication}"
<#if payment.creditSortCode??>
        sort-code="${payment.creditSortCode}"
</#if>
<#if payment.creditAccountNumber??>
        account="${payment.creditAccountNumber}"
</#if>
<#if payment.creditAccountName??>
        name="${payment.creditAccountName}"
</#if>
<#if payment.creditPaymentReference??>
        reference="${payment.creditPaymentReference}"
</#if>
<#if payment.creditLedgerAccountNumber??>
        ledger-account-number="${payment.creditLedgerAccountNumber}"
</#if>
    />
</payment>
</#escape>