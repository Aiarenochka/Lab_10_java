package main.integralCalculator;

import main.controller.Controller;
import main.function.Function;

public class RunnableIntegralCalculator implements Runnable {
    private final IntegralCalculator integralCalculator;
    private final Controller controller;

    public RunnableIntegralCalculator(double a, double b, int n, Function f, Controller controller) {
        integralCalculator = new IntegralCalculator(a, b, n, f);
        this.controller = controller;
    }

    @Override
    public void run() {
        controller.sendResult(integralCalculator.calculate());
    }
}