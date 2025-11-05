package org.heg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "attack")
public class AttackEntity {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column
  private String attackName;

  @Column
  private Integer attackPoint;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pokemonId", referencedColumnName = "id")
  private PokemonEntity pokemonId;

  public String getName() {
    return attackName;
  }

  public void setAttackName(String attackName) {
    this.attackName = attackName;
  }

  public PokemonEntity getPokemonId() {
    return pokemonId;
  }

  public void setPokemonId(PokemonEntity pokemonId) {
    this.pokemonId = pokemonId;
  }

  public Integer getPoint() {
    return attackPoint;
  }

  public void setAttackPoint(Integer attackPoint) {
    this.attackPoint = attackPoint;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AttackEntity that = (AttackEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(attackName, that.attackName)
        && Objects.equals(attackPoint, that.attackPoint) && Objects.equals(pokemonId,
        that.pokemonId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, attackName, attackPoint, pokemonId);
  }
}
