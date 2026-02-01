package local.kata.simplepokerhands.tuples;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record Quadruple<A, B, C, D>(A first, B second, C third, D fourth) implements Serializable {
    @NotNull
    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ", " + fourth + ")";
    }

    public List<Object> toList() {
        return List.of(first, second, third, fourth);
    }
}
