package org.heg.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pokemon")
public class PokemonEntity {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "pokemon_name")
  private String pokemonName;

  @Column(name = "life_points")
  private Integer lifePoints;

  @Column(name = "resume")
  private String resume;

  @Column(name = "image_url")
  private String imageUrl;

  @ManyToOne
  @JoinColumn(name = "typeId", referencedColumnName = "id")
  private TypeEntity typeId;

  @OneToMany(mappedBy = "pokemonId", cascade = CascadeType.ALL)
  private List<AttackEntity> attackList;

  public List<AttackEntity> getAttackList() {
    return attackList;
  }

  public void setAttackList(List<AttackEntity> attackList) {
    this.attackList = attackList;
  }

  public Long getId() {
    return id;
  }

  public String getPokemonName() {
    return pokemonName;
  }

  public void setPokemonName(String pokemonName) {
    this.pokemonName = pokemonName;
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

  public void setId(Long id) {
    this.id = id;
  }

  public void setResume(String resume) {
    this.resume = resume;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public TypeEntity getTypeId() {
    return typeId;
  }

  public void setTypeId(TypeEntity typeId) {
    this.typeId = typeId;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PokemonEntity that = (PokemonEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(pokemonName,
        that.pokemonName) && Objects.equals(lifePoints, that.lifePoints)
        && Objects.equals(resume, that.resume) && Objects.equals(imageUrl,
        that.imageUrl) && Objects.equals(typeId, that.typeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pokemonName, lifePoints, resume, imageUrl, typeId);
  }
}
