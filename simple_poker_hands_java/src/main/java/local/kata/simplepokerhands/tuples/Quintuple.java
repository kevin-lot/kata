package local.kata.simplepokerhands.tuples;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record Quintuple<A, B, C, D, E>(A first, B second, C third, D fourth, E fifth) implements Serializable {
    @NotNull
    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ", " + fifth + ")";
    }

    public List<Object> toList() {
        return List.of(first, second, third, fourth, fifth);
    }
}
