package edu.hw11.task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SumToMultiplyModificatorTest {
    @Test
    public void sumToMultiply() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum")).intercept(MethodDelegation.to(ArithmeticUtilsMultSum.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();
        int actual = ArithmeticUtils.sum(5, 3);
        assertThat(actual).isEqualTo(15);
    }

    public static class ArithmeticUtilsMultSum {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
