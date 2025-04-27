package com.moran.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    OPEN(1),
    CLOSE(0);

    private final int value;
}
