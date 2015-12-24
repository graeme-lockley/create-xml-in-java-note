package ideas.xml;

import freemarker.template.TemplateException;

import java.util.HashMap;
import java.util.Map;

public class FreeMarkerMarshallPayment implements MarshallPayment {
    @Override
    public String marshall(PaymentValue paymentValue) {
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("payment", paymentValue);

        try {
            return FreeMarkerUtils.template(dataModel, "PaymentTemplate.ftl");
        } catch (TemplateException ex) {
            throw new RuntimeException(ex);
        }
    }
}
