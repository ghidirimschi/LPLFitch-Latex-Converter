package formula;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


import java.util.ArrayList;
import java.util.stream.Collectors;


public final class PredicateSymbol implements AtomicSentence {
    private final String name;
    private final ArrayList<Argument> arguments;
    private final int hash;

    public PredicateSymbol(String name, ArrayList<Argument> arguments) {
        this.name = name;
        this.arguments = arguments == null ? null : new ArrayList<>(arguments);
        hash = new HashCodeBuilder(4817, 19213).
                append(this.name).
                append(this.arguments).
                toHashCode();
    }

    @Override
    public String toString() {
        return name + ((arguments != null) ? ("(" + arguments.stream().limit(arguments.size() - 1).map(s -> s + ", ").collect(Collectors.joining()) + arguments.get(arguments.size() - 1) + ")") : "");
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PredicateSymbol)) return false;
        if (obj == this) return true;

        PredicateSymbol toCheck = (PredicateSymbol) obj;
        return new EqualsBuilder().
                append(this.name, toCheck.name).
                append(this.arguments, toCheck.arguments).
                isEquals();
    }

    @Override
    public boolean isEqualWithReplacement(Sentence other, Argument argument, Argument newArgument) {
        if (!(other instanceof PredicateSymbol)) return false;
        if (other == this) return true;
        PredicateSymbol otherPredicateSymbol = (PredicateSymbol) other;

        if (!this.name.equals(otherPredicateSymbol.name)) {
            return false;
        }

        for (int idx = 0; idx < arguments.size(); ++idx) {
            if (!(arguments.get(idx).isEqualWithReplacement(otherPredicateSymbol.arguments.get(idx), argument, newArgument))) {
                return false;
            }
        }
        return true;
    }
}
