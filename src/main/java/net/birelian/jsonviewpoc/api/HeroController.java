package net.birelian.jsonviewpoc.api;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import net.birelian.jsonviewpoc.model.HeroDto;
import net.birelian.jsonviewpoc.model.views.Views;
import net.birelian.jsonviewpoc.service.HeroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @GetMapping("no-view")
    public List<HeroDto> findAll() {
        return heroService.findAll();
    }

    @GetMapping("public-view")
    @JsonView(Views.Public.class)
    public List<HeroDto> withPublicView() {
        return heroService.findAll();
    }

    @GetMapping("restricted-view")
    @JsonView(Views.Restricted.class)
    public List<HeroDto> withRestrictedView() {
        return heroService.findAll();
    }

}
