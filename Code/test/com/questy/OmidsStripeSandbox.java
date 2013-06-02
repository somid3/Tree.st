package com.questy;

import com.questy.utils.Vars;
import com.stripe.Stripe;
import com.stripe.model.Customer;

import java.util.HashMap;
import java.util.Map;

public class OmidsStripeSandbox {


    public static void main(String[] args) throws Exception {

        Stripe.apiKey = Vars.stripeSecretKey;

        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description", "Customer for test@example.com");
        customerParams.put("card", "tok_1vSqxp1jgiWfSb"); // obtained with Stripe.js

        System.out.println(
                Customer.create(customerParams)
        );
    }

}