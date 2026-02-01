package local.kata.simplepokerhands.tuples;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public record Pair<A, B>(A first, B second) implements Serializable {
    @NotNull
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public List<Object> toList() {
        return List.of(first, second);
    }
}
