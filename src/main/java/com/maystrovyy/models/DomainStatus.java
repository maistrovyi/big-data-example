package com.maystrovyy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  DomainStatus {

    UNKNOWN("unknown"), AVAILABLE("available"), NOT_AVAILABLE("not_available");

    private String status;

}