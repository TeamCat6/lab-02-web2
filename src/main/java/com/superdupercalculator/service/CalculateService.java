package com.superdupercalculator.service;

import com.superdupercalculator.model.Function;

public class CalculateService {

    private final Result25Service result25Service = new Result25Service();
    private final Result26Service result26Service = new Result26Service();
    private final Result27Service result27Service = new Result27Service();

    public String getResult(Function function, double a, double b, double c, double d) {
        switch (function) {
            case CALCULATION_25:
                return result25Service.getResult(a, b, c, d);
            case CALCULATION_26:
                return result26Service.getResult(a, b, c, d);
            case CALCULATION_27:
                return result27Service.getResult(a, b, c, d);
        }
        return "";
    }
}
