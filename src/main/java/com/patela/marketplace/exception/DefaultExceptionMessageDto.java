package com.patela.marketplace.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DefaultExceptionMessageDto {
    private String message;
}
