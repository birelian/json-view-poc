package net.birelian.jsonviewpoc.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Value;
import net.birelian.jsonviewpoc.model.views.Views;

@Value
@Builder
@JsonView(Views.Public.class) // All attributes public by default
public class HeroDto {

    String nickname;

    @JsonView(Views.Restricted.class)
    String name;

    @JsonView(Views.Restricted.class)
    String surname;

    String power;

}
