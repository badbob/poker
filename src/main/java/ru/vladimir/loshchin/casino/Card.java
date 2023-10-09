package ru.vladimir.loshchin.casino;

import static java.util.Objects.requireNonNull;

public class Card {

    private final Suit suit;
	private final Value value;

	public Card(Suit suit, Value val) {
        this.suit = requireNonNull(suit, "[suit] is required argument");
        this.value = requireNonNull(val, "[val] is required argument");
    }
    
    @Override
    public String toString() {
        return String.format("%s%s", value.code(), suit.code());
    }
}
