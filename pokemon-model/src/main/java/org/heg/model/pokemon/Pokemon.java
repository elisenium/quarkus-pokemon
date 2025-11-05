package org.heg.model.pokemon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.heg.model.fight.AttackResult;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Pokemon {

    private Long id;

    private String name;

    private Integer lifePoints;

    private String resume;

    private String description;

    private String imageUrl;

    private PokemonType pokemonType;

    private List<Attack> attacks;

    public Pokemon() {}


    public Pokemon(Long id, String name, Integer lifePoints, String resume, String description, String imageUrl, PokemonType pokemonType, List<Attack> attacks) {
        this.id = id;
        this.name = name;
        this.lifePoints = lifePoints;
        this.resume = resume;
        this.description = description;
        this.imageUrl = imageUrl;
        this.pokemonType = pokemonType;
        this.attacks = attacks;
    }

    @JsonIgnore
    public boolean isAlive() {
        return lifePoints > 0;
    }

    public AttackResult attack(Pokemon pokemon) {
        Attack randomAttack = getRandomAttack();
        String attackResume = getName() +"[" + getLifePoints() +"]" + " attack " + pokemon.getName() +"[" + pokemon.getLifePoints() +"]" + " with " + randomAttack.getName() + " (" + randomAttack.getPoint() +")";
        pokemon.lifePoints -= randomAttack.getPoint();
        return new AttackResult(this, pokemon, randomAttack, attackResume);
    }

    private Attack getRandomAttack() {
        return attacks.get(ThreadLocalRandom.current().nextInt(0, attacks.size()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(Integer lifePoints) {
        this.lifePoints = lifePoints;
    }

    public String getResume() {
        return resume;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PokemonType getPokemonType() {
        return pokemonType;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lifePoints=" + lifePoints +
                ", resume='" + resume + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", pokemonType=" + pokemonType +
                ", attacks=" + attacks +
                '}';
    }
}