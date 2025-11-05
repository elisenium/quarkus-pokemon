package org.heg.model.fight;

import org.heg.model.pokemon.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FightResult {

    private Date date;

    private Pokemon pokemon1;

    private Pokemon pokemon2;

    private final List<AttackResult> attackResultList = new ArrayList<>();

    public FightResult() {}

    public FightResult(Pokemon pokemon1, Pokemon pokemon2, Date date) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.date = date;
    }

    public Pokemon getWinner() {
        return pokemon1.isAlive() ? pokemon1 : pokemon2;
    }

    public Pokemon getLooser() {
        return !pokemon1.isAlive() ? pokemon1 : pokemon2;
    }

    public List<AttackResult> getAttackResultList() {
        return attackResultList;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FightResult{" +
                "date=" + date +
                ", pokemon1=" + pokemon1 +
                ", pokemon2=" + pokemon2 +
                ", attackResultList=" + attackResultList +
                '}';
    }
}
