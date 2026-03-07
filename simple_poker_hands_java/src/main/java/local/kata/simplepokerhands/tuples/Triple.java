package local.kata.simplepokerhands.tuples;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record Triple<A, B, C>(A first, B second, C third) implements Serializable {
    @NotNull
    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

    public List<Object> toList() {
        return List.of(first, second, third);
    }
}
