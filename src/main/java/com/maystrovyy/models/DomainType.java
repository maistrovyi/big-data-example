package com.maystrovyy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainType {

    DOT_NET(".NET"), DOT_COM(".COM");

    private String domain;

}
