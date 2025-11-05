package org.heg.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.heg.entity.AttackEntity;
import org.heg.entity.PokemonEntity;
import org.heg.entity.TypeEntity;
import org.heg.model.exception.PokemonNotFoundException;
import org.heg.model.pokemon.Attack;
import org.heg.model.pokemon.Pokemon;
import org.heg.model.pokemon.PokemonType;
import org.heg.repository.PokemonRepository;

@ApplicationScoped()
public class PokemonService {

  @Inject
  PokemonRepository pokemonRepository;

  private Pokemon toModel(PokemonEntity pokemonEntity) {
    return new Pokemon(pokemonEntity.getId(),
        pokemonEntity.getPokemonName(),
        pokemonEntity.getLifePoints(),
        pokemonEntity.getResume(),
        null,
        pokemonEntity.getImageUrl(),
        pokemonEntity.getTypeId().getType(),
        toAttackList(pokemonEntity.getAttackList()));
  }

  private List<Pokemon> toPokemonList(List<PokemonEntity> pokemonEntityList) {
    List<Pokemon> pokemons = new ArrayList<>();
    for (PokemonEntity pokemonEntity : pokemonEntityList) {
      pokemons.add(toModel(pokemonEntity));
    }
    return pokemons;
  }

  private List<Attack> toAttackList(List<AttackEntity> attackEntities) {
    List<Attack> attacks = new ArrayList<>();
    for (AttackEntity attackEntity : attackEntities) {
      Attack attack = new Attack(attackEntity.getPokemonId().getId(),
          attackEntity.getName(),
          attackEntity.getPoint());
      attacks.add(attack);
    }
    return attacks;
  }

  private List<PokemonType> toPokemonType(List<TypeEntity> typeEntities) {
    List<PokemonType> pokemonTypes = new ArrayList<>();
    for (TypeEntity typeEntity : typeEntities) {
      pokemonTypes.add(typeEntity.getType());
    }
    return pokemonTypes;
  }

  // renvoie un pokemon en donnant son id
  public Pokemon findById(Long id) throws PokemonNotFoundException {
    PokemonEntity pokemonEntity = pokemonRepository.findById(id);
    if (pokemonEntity == null)
      throw new PokemonNotFoundException("Pokemon with id " + id + " not found");

    return toModel(pokemonEntity);
  }

  // renvoie tous les types de pokemons
  public List<PokemonType> listType() {
    return toPokemonType(pokemonRepository.listAll().stream().map(PokemonEntity::getTypeId).toList());
  }

  // renvoie tous les pokemons
  public List<Pokemon> listPokemon() {
    List<PokemonEntity> pokemonEntityList = pokemonRepository.listAll();
    return toPokemonList(pokemonEntityList);
  }

  // recherche les pokemons par id ou par nom
  public List<Pokemon> search(String searchParam) {
    return toPokemonList(pokemonRepository.listPokemonNameLike(searchParam));
  }

  public List<Pokemon> search(Long searchId) {
    return List.of(toModel(pokemonRepository.findById(searchId)));
  }

  // compte le nombre de pokemon
  public Long countPokemon() {
    return pokemonRepository.count();
  }

  //donne une liste de 2 pokemons tirés au hasard
  public List<Pokemon> fighters() {
    List<Long> ids = new ArrayList<>(pokemonRepository.findIds());
    Collections.shuffle(ids);

    return List.of(toModel(pokemonRepository.findById(ids.getFirst())), toModel(
        pokemonRepository.findById(ids.getLast())));
  }
}
