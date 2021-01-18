package com.patela.marketplace.service;

import com.patela.marketplace.domain.entities.ConfirmationEmail;
import com.patela.marketplace.exception.ServiceException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;

public interface ConfirmationEmailService {

    ConfirmationEmail createConfirmationToken(ConfirmationEmail confirmationToken);

    @Async
    void sendEmail(SimpleMailMessage email);

    ConfirmationEmail readConfirmationToken(String token) throws ServiceException;

    /**
     * Delete token
     *
     * @param confirmationEmail token to be deleted.
     */
    void deleteToken(ConfirmationEmail confirmationEmail);

}
