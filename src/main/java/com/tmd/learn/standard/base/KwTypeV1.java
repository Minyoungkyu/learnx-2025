package com.tmd.learn.standard.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum KwTypeV1 {
    ALL("all"),
    TITLE("title"),
    BODY("body"),
    NAME("name");

    private final String value;
}