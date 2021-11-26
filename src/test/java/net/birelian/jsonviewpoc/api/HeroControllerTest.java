package net.birelian.jsonviewpoc.api;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class HeroControllerTest {

    public static final String HERO_NAME = "Solid";
    public static final String HERO_SURNAME = "Snake";
    public static final String HERO_NICKNAME = "snake";
    public static final String HERO_POWER = "CQC";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String SURNAME_ATTRIBUTE = "surname";
    public static final String NICKNAME_ATTRIBUTE = "nickname";
    public static final String POWER_ATTRIBUTE = "power";

    @Autowired
    public HeroControllerTest(final WebApplicationContext webApplicationContext) {

        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    void findWithoutViewShouldReturnAllAttributes() {

        // @formatter:off

        RestAssuredMockMvc
                .given()

                .when()
                    .get("/no-view")

                .then()
                    .log().ifValidationFails()
                    .statusCode(OK.value())

                    // Serialized fields
                    .body(NAME_ATTRIBUTE, hasItem(HERO_NAME))
                    .body(SURNAME_ATTRIBUTE, hasItem(HERO_SURNAME))
                    .body(NICKNAME_ATTRIBUTE, hasItem(HERO_NICKNAME))
                    .body(POWER_ATTRIBUTE, hasItem(HERO_POWER));

        // @formatter:on
    }

    @Test
    void findWithPublicViewShouldReturnPublicAttributes() {

        // @formatter:off

        RestAssuredMockMvc
                .given()

                .when()
                    .get("/public-view")

                .then()
                    .log().ifValidationFails()
                    .statusCode(OK.value())

                    // Serialized fields
                    .body(NICKNAME_ATTRIBUTE, hasItem(HERO_NICKNAME))
                    .body(POWER_ATTRIBUTE, hasItem(HERO_POWER))

                    // Not serialized fields
                    .body(NAME_ATTRIBUTE, not(hasItem(HERO_NAME)))
                    .body(SURNAME_ATTRIBUTE, not(hasItem(HERO_SURNAME)));

        // @formatter:on
    }

    @Test
    void findWithRestrictedViewShouldReturnAllAttributes() {

        // @formatter:off

        RestAssuredMockMvc
                .given()

                .when()
                    .get("/restricted-view")

                .then()
                    .log().ifValidationFails()
                    .statusCode(OK.value())
                    .body(POWER_ATTRIBUTE, hasItem(HERO_POWER))

                    // Serialized fields
                    .body(NAME_ATTRIBUTE, hasItem(HERO_NAME))
                    .body(SURNAME_ATTRIBUTE, hasItem(HERO_SURNAME))
                    .body(NICKNAME_ATTRIBUTE, hasItem(HERO_NICKNAME))
                    .body(POWER_ATTRIBUTE, hasItem(HERO_POWER));

        // @formatter:on
    }

}