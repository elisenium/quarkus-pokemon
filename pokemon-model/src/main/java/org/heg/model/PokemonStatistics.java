package org.heg.model;

public class PokemonStatistics {

    public PokemonStatistics(final Long count) {
        this.count = count;
    }

    private Long count;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
