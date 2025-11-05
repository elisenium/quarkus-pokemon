package org.heg.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import org.heg.entity.PokemonEntity;

@ApplicationScoped()
public class PokemonRepository implements PanacheRepository<PokemonEntity> {

  public List<PokemonEntity> listPokemonNameLike(String searchParam) {
    String likeParam = "%" + searchParam + "%";
    return list("pokemonName like ?1", likeParam);
  }

  public List<Long> findIds() {
    return listAll().stream().map(PokemonEntity::getId).toList();
  }

}
