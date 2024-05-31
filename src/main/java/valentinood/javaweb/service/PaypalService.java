package valentinood.javaweb.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface PaypalService {

    Payment create(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException;

    Payment execute(String paymentId, String payerId) throws PayPalRESTException;
}
