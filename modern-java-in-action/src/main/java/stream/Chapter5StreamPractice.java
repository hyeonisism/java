package stream;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

class Chapter5StreamPractice {

    private Chapter5StreamPractice() {
        throw new IllegalStateException("Utility class");
    }

    static List<Transaction> test1(Collection<? extends Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted((comparingInt(Transaction::getValue)))
                .collect(Collectors.toList());
    }

    static List<String> test2(Collection<? extends Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    static List<Trader> test3(Collection<? extends Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    static List<String> test4(Collection<? extends Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted(CharSequence::compare)
                .collect(Collectors.toList());
    }

    static boolean test5(Collection<? extends Transaction> transactions) {
        return transactions.stream()
                .anyMatch(transaction -> Objects.equals(transaction.getTrader().getCity(), "Milan"));
    }

    static void test6(Collection<? extends Transaction> transactions) {
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    static OptionalInt test7(Collection<? extends Transaction> transactions) {
        return transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
    }

    static OptionalInt test8(Collection<? extends Transaction> transactions) {
        return transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();
    }
}
