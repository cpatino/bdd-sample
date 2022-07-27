package com.example.calculator.adapter.outbound.rest;

interface Operation {

    boolean shouldApply(String operator);

    Number apply(String operation);
}
