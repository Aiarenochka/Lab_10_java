package main.function;

import java.util.function.DoubleUnaryOperator;

public class Function implements DoubleUnaryOperator {
    @Override
    public double applyAsDouble(double x) {
        return Math.exp(-2 * x);
    }
}
