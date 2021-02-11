package com.patela.marketplace.domain.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.patela.marketplace.domain.entities.BaseEntity;
import com.patela.marketplace.domain.entities.User;
import com.patela.marketplace.domain.enums.OrderState;
import com.patela.marketplace.serializer.MarketPlaceCustomSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestOrderDTO extends BaseEntity<Integer> {

    private String orderName;

    @JsonSerialize(using = MarketPlaceCustomSerializer.class)
    private UserDTO user;
    private BigDecimal amount;
    private BigDecimal amountTax;
    private BigDecimal amountTotal;
    private OrderState state;

    @JsonManagedReference("order-line")
    private List<RequestOrderLineDTO> requestOrderLines;
}
