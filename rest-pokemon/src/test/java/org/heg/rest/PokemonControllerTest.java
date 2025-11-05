package org.heg.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PokemonControllerTest {

  // test du path "/pokemon/{id}" du controller
  void shouldFindPokemon() {
    given().when().
        get("/pokemon/1").
        then().
        statusCode(200).
        body("id", equalTo(1),
            "name", equalTo("VOLTALI"));
  }

  // test du path "/pokemon/{id}" du controller avec exception
  void shouldReturnNotFound() {

  }

  // test du path "/list" du controller
  void shouldListAllPokemon() {

  }

  // test du path "/show" du controller
  void shouldGiveFighters() {

  }

  // test du path "/count" du controller
  void shouldCountPokemon() {

  }

}