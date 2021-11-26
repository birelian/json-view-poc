package net.birelian.jsonviewpoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.birelian.jsonviewpoc.model.HeroDto;
import net.birelian.jsonviewpoc.model.views.Views;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JsonViewPocApplicationTest {

    public static final String NAME_FIELD = "name";
    public static final String SURNAME_FIELD = "surname";
    public static final String NICKNAME_FIELD = "nickname";
    public static final String POWER_FIELD = "power";

    private final ObjectMapper objectMapper;

    @Autowired
    JsonViewPocApplicationTest(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Test
    @SuppressWarnings("unchecked")
    void serializeWithoutViewShouldIncludeAllFields() throws JsonProcessingException {

        // Given
        final HeroDto heroDto = this.getHero();

        // When
        final String serializedHero = this.objectMapper.writeValueAsString(heroDto);
        final Map<String, String> serializedProperties = this.objectMapper.readValue(serializedHero, Map.class);

        // Then
        assertEquals(4, serializedProperties.size());
        assertTrue(serializedProperties.containsKey(NAME_FIELD));
        assertTrue(serializedProperties.containsKey(SURNAME_FIELD));
        assertTrue(serializedProperties.containsKey(NICKNAME_FIELD));
        assertTrue(serializedProperties.containsKey(POWER_FIELD));

    }

    @Test
    @SuppressWarnings("unchecked")
    void serializeWithPublicViewShouldIncludeRestrictedFields() throws JsonProcessingException {

        // Given
        final HeroDto heroDto = this.getHero();

        // When
        final String serializedHero = this.objectMapper.writerWithView(Views.Public.class).writeValueAsString(heroDto);
        final Map<String, String> serializedProperties = this.objectMapper.readValue(serializedHero, Map.class);

        // Then
        assertEquals(2, serializedProperties.size());
        assertTrue(serializedProperties.containsKey(NICKNAME_FIELD));
        assertTrue(serializedProperties.containsKey(POWER_FIELD));

    }

    @Test
    @SuppressWarnings("unchecked")
    void serializeWithRestrictedViewShouldIncludeRestrictedFields() throws JsonProcessingException {

        // Given
        final HeroDto heroDto = this.getHero();

        // When
        final String serializedHero = this.objectMapper.writerWithView(Views.Restricted.class).writeValueAsString(heroDto);
        final Map<String, String> serializedProperties = this.objectMapper.readValue(serializedHero, Map.class);

        // Then
        assertEquals(4, serializedProperties.size());
        assertTrue(serializedProperties.containsKey(NAME_FIELD));
        assertTrue(serializedProperties.containsKey(SURNAME_FIELD));
        assertTrue(serializedProperties.containsKey(NICKNAME_FIELD));
        assertTrue(serializedProperties.containsKey(POWER_FIELD));

    }

    private HeroDto getHero() {

        return HeroDto.builder()
                .name("Solid")
                .surname("Snake")
                .nickname("snake")
                .power("CQC")
                .build();
    }

}
