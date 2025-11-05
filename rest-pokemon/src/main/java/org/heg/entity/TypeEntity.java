package org.heg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import org.heg.model.pokemon.PokemonType;

@Entity
@Table(name = "type")
public class TypeEntity {
  @Id
  @GeneratedValue
  private Long id;

  @Enumerated(EnumType.STRING)
  private PokemonType type;

  private String color;

  public PokemonType getType() {
    return type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setType(PokemonType type) {
    this.type = type;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TypeEntity that = (TypeEntity) o;
    return Objects.equals(id, that.id) && type == that.type && Objects.equals(
        color, that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, color);
  }
}
