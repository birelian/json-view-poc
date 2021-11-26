package net.birelian.jsonviewpoc.service;

import net.birelian.jsonviewpoc.model.HeroDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroService {

    public List<HeroDto> findAll() {

        // Emulate a repository
        return List.of(
                HeroDto.builder()
                        .name("Solid")
                        .surname("Snake")
                        .nickname("snake")
                        .power("CQC")
                        .build()
        );
    }
}
