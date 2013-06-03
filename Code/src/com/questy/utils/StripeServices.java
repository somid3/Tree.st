package com.questy.utils;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class StripeServices {

    static { Stripe.apiKey = Vars.stripeSecretKey; }

    public static String createCustomer (
        String cardToken) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("card", cardToken);
        Customer customer = Customer.create(customerParams);

        return customer.getId();
    }

}
