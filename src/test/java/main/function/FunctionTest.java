package main.function;

import main.integralCalculator.IntegralCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;

class FunctionTest {
    IntegralCalculator integralCalculator;
    Function function;
    double EPS = 1e-6;

    @BeforeEach
    void setUp() {
        function = new Function();
    }

    @ParameterizedTest
    @CsvSource({"0.0, 1.0",
            "1.0, 0.135335",
            "2.0, 0.018315",
            "3.0, 0.002478",
            "10.0, 2.06115e-9"})
    void functionTest(double x, double expected) {
        double result = function.applyAsDouble(x);
        assertThat(result).isCloseTo(expected, offset(EPS));
    }

    @ParameterizedTest
    @CsvSource({"0.0, 1.0, 100, 0.428023",
            "0.0, 2.0, 200, 0.485950",
            "1.0, 3.0, 500, 0.066162",
            "0.0, 0.0, 100, 0.0",
            "5.0, 3.0, 300, -0.001224"})
    void applyAsDoubleTest(double a, double b, int n, double expected) {
        integralCalculator = new IntegralCalculator(a, b, n, function);
        double result = integralCalculator.calculate();
        assertThat(result).isCloseTo(expected, offset(EPS));
    }
}
