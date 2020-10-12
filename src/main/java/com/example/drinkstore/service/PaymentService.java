package com.example.drinkstore.service;

import com.example.drinkstore.dto.ChargeRequest;
import com.stripe.exception.*;
import com.stripe.model.Charge;

public interface PaymentService {
    Charge pay(ChargeRequest chargeRequest) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException;
}
