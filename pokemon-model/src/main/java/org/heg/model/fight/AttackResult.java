package org.heg.model.fight;

import org.heg.model.pokemon.Attack;
import org.heg.model.pokemon.Pokemon;

public class AttackResult {

    private Pokemon pokemonAttacker;

    private Pokemon pokemon;

    private Attack attack;

    private String attackResume;

    public AttackResult() {}

    public AttackResult(Pokemon pokemonAttacker, Pokemon pokemon, Attack attack, String attackResume) {
        this.pokemonAttacker = pokemonAttacker;
        this.pokemon = pokemon;
        this.attack = attack;
        this.attackResume = attackResume;
    }

    public Pokemon getPokemonAttacker() {
        return pokemonAttacker;
    }

    public void setPokemonAttacker(Pokemon pokemonAttacker) {
        this.pokemonAttacker = pokemonAttacker;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public String getAttackResume() {
        return attackResume;
    }

    public void setAttackResume(String attackResume) {
        this.attackResume = attackResume;
    }

    @Override
    public String toString() {
        return "AttackResult{" +
                "pokemonAttacker=" + pokemonAttacker +
                ", pokemon=" + pokemon +
                ", attack=" + attack +
                ", attackResume='" + attackResume + '\'' +
                '}';
    }
}
