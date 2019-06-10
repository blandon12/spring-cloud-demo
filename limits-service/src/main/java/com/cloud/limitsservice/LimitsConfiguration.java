package com.cloud.limitsservice;

public class LimitsConfiguration {

    private int max;
    private int min;

    protected LimitsConfiguration() {
    }

    public LimitsConfiguration(int max, int min) {
        super();
        this.max = max;
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }
}
