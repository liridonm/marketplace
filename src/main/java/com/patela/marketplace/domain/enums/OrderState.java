package com.patela.marketplace.domain.enums;

public enum OrderState {
    DRAFT, DONE, CONFIRMED, DELIVERED, PAID, DELETED

    //DRAFT - when order is a cart process.
    //DONE - When order has been finished checkout process
    //CONFIRMED - When admin confirm order.
    //DELIVERED - When order has been arrived to client
    //PAID - When client paid invoice.
    //DELETED - When order has been deleted by admin.
}
