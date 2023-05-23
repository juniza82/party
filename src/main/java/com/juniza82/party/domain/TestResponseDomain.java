package com.juniza82.party.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

@RequiredArgsConstructor
public class TestResponseDomain {

    private final String name;
    private final int amount;

}
