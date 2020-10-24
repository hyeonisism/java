package stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionStreamProcessorTests {

    private List<Transaction> transactions;

    @BeforeEach
    void setUp() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @DisplayName("2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오")
    @Test
    void test1() {
        // when
        List<Transaction> result = TransactionStreamProcessor.test1(transactions);

        // then
        assertThat(result.stream()
                .map(Transaction::getValue)
                .collect(Collectors.toList()))
                .isSortedAccordingTo(Integer::compare);
    }

    @DisplayName("거래자가 근무하는 모든 도시를 중복 없이 나열하시오")
    @Test
    void test2() {
        // when
        List<String> result = TransactionStreamProcessor.test2(transactions);

        // then
        assertThat(result).containsOnlyOnce(result.toArray(String[]::new));
    }

    @DisplayName("케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정리하시오")
    @Test
    void test3() {
        // when
        List<Trader> result = TransactionStreamProcessor.test3(transactions);

        // then
        assertThat(result.stream()
                .map(Trader::getName)
                .collect(Collectors.toList()))
                .isSortedAccordingTo(CharSequence::compare);

        assertThat(result.stream()
                .map(Trader::getCity)
                .collect(Collectors.toList()))
                .allMatch(city -> city.equals("Cambridge"));
    }

    @DisplayName("모든 거래자의 이름을 알파밧순으로 정렬해서 반환하시오")
    @Test
    void test4() {
        // when
        List<String> result = TransactionStreamProcessor.test4(transactions);

        // then
        assertThat(result).isSortedAccordingTo(CharSequence::compare);
    }

    @DisplayName("밀라노에 거래자가 있는가?")
    @Test
    void test5() {
        assertThat(TransactionStreamProcessor.test5(transactions)).isTrue();
    }

    @DisplayName("케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오")
    @Test
    void test6() {
        // given
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // when
        TransactionStreamProcessor.test6(transactions);

        // then
        assertThat(out.toString())
                .contains(
                        String.valueOf(300),
                        String.valueOf(1000),
                        String.valueOf(400),
                        String.valueOf(950)
                );
    }

    @DisplayName("전체 트랜잭션 중 최댓값은 얼마인가?")
    @Test
    void test7() {
        // when
        OptionalInt result = TransactionStreamProcessor.test7(transactions);

        // then
        assertThat(result).isEqualTo(OptionalInt.of(1000));
    }

    @DisplayName("전체 트랜잭션 중 최솟값 얼마인가?")
    @Test
    void test8() {
        // when
        OptionalInt result = TransactionStreamProcessor.test8(transactions);

        // then
        assertThat(result).isEqualTo(OptionalInt.of(300));
    }
}

