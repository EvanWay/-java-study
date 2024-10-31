package com.evan.study;

import com.evan.study.java8.LambdaDemo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@SpringBootTest
public class StudyJava8ApplicationTests {

    /**
     * 允许在接口中有默认方法实现
     * 扩展方法——default关键字，为接口提供非抽象的方法
     */
    public interface Formula {
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }
    @Test
    void contextLoads() {
        // 允许在接口中有默认方法实现，Java 8 允许我们使用default关键字，为接口声明添加非抽象的方法实现
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        double calculate = formula.calculate(100);
        double sqrt = formula.sqrt(16);
        System.out.println(calculate);
        System.out.println(sqrt);
    }

    //@FunctionalInterface 函数式接口 —— 一个所谓的函数式接口必须要有且仅有一个抽象方法声明
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    @Test
    void test() {
        //匿名内部类写法
        Converter<String, Integer> converter = new Converter<String, Integer>() {
            @Override
            public Integer convert(String from) {
                return Integer.valueOf(from);
            }
        };
        Integer converted = converter.convert("123");
        System.out.println(converted);

        //Lambda写法
        Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);
        Integer converted1 = converter1.convert("123");
        System.out.println(converted);
    }

    //Supplier 无参数，有返回值
    @Test
    void test1() {
        String msgA = "Hello ";
        String msgB = "World ";
        System.out.println(
                getString(() -> msgA + msgB)
        );
    }
    private static String getString(Supplier<String> stringSupplier) {
        return stringSupplier.get();
    }

    //Consumer 有参数，无返回值
    @Test
    void test2() {
        consumerString(s -> System.out.println(s));
    }
    private static void consumerString(Consumer<String> function) {
        function.accept("Hello");
    }

    //Predicate 有参数，返回值布尔类型
    @Test
    void test3() {
        method(s -> s.length() > 5);
    }
    private static void method(Predicate<String> predicate) {
        boolean veryLong = predicate.test("HelloWorld");
        System.out.println("字符串很长吗:" + veryLong);
    }

    //Function 有参数，有返回值
    @Test
    void test4() {
        functionMethod(s -> Integer.parseInt(s));
    }
    private static void functionMethod(Function<String, Integer> function) {
        int num = function.apply("10");
        System.out.println(num);
    }


    @FunctionalInterface
    public interface Calculable {
        int calculateInt(int a, int b);
    }
    //方法引用
    public static void display(Calculable calc, int n1, int n2) {
        System.out.println(calc.calculateInt(n1, n2));
    }
    @Test
    void test5() {
        // 方法引用
        int n1 = 10;
        int n2 = 5;
        //静态方法1
        display(new Calculable() {
            @Override
            public int calculateInt(int a, int b) {
                return LambdaDemo.add(a, b);
            }
        }, n1, n2);
        //静态方法2-Lambda写法
        display((a, b) -> LambdaDemo.add(a, b), n1, n2);
        //静态方法3-方法引用写法
        display(LambdaDemo::add, n1, n2);

        //实例方法1
        display(new Calculable() {
            @Override
            public int calculateInt(int a, int b) {
                return new LambdaDemo().sub(a, b);
            }
        }, n1, n2);
        //实例方法2-Lambda写法
        display((a, b) -> new LambdaDemo().sub(a, b), n1, n2);
        //实例方法3-方法引用写法
        display(new LambdaDemo()::sub, n1, n2);
    }

}
