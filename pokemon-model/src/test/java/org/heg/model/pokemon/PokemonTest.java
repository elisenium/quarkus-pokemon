package org.heg.model.pokemon;

import org.heg.model.fight.AttackResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PokemonTest {

    @Test
    void attack() {
        Pokemon pokemon1 = getPokemon1();
        Pokemon pokemon2 = getPokemon2();
        AttackResult attackResult = pokemon1.attack(pokemon2);
        Attack attack = attackResult.getAttack();
        Assertions.assertEquals(getPokemon2().getLifePoints() - attack.getPoint(), attackResult.getPokemon().getLifePoints());
        Assertions.assertTrue(attackResult.getPokemonAttacker().isAlive());
        Assertions.assertTrue(attackResult.getPokemon().isAlive());
        System.out.println(attackResult.getAttackResume());
    }

    private Pokemon getPokemon1() {
        List<Attack> attackList = new ArrayList<>();
        attackList.add(new Attack(1L, "Dard-Nuée", 20));
        attackList.add(new Attack(2L, "Neutralivolt", 40));
        return new Pokemon(null, "pok1", 100, "resume", "description", null, PokemonType.EARTH, attackList);
    }

    private Pokemon getPokemon2() {
        List<Attack> attackList = new ArrayList<>();
        attackList.add(new Attack(3L, "Queue de Fer", 30));
        attackList.add(new Attack(4L, "Max Etincelle", 50));
        return new Pokemon(null, "pok2", 120, "resume", "description", null, PokemonType.LIGHTNING, attackList);
    }
}
