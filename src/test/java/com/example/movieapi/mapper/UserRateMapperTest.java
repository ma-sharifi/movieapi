package com.example.movieapi.mapper;

import com.example.movieapi.entity.UserMovieId;
import com.example.movieapi.entity.UserRate;
import com.example.movieapi.service.dto.UserRateDto;
import com.example.movieapi.service.mapper.UserRateMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Mahdi Sharifi
 * @since 10/5/22
 */
class UserRateMapperTest {

    UserRateMapper userRateMapper = Mappers.getMapper(UserRateMapper.class);

    @Test
    void givenEntityToDto_whenMaps_thenCorrect() {
        UserRate entity = new UserRate();
        entity.setTitle("Black Swan");
        entity.setId(new UserMovieId("tt1375666", "mahdi"));
        UserRateDto dto = userRateMapper.toDto(entity);

        assertEquals(entity.getId().getUsername(), dto.getUsername());
        assertEquals(entity.getId().getImdbId(), dto.getImdbId());
        assertEquals(entity.getTitle(), dto.getTitle());
    }

    @Test
    void givenDtoToEntity_whenMaps_thenCorrect() {
        UserRateDto dto = UserRateDto.builder()
                .title("Black Swan")
                .username("mahdi")
                .imdbId("tt1375666")
                .build();
        UserRate entity = userRateMapper.toEntity(dto);

        assertEquals(dto.getUsername(), entity.getId().getUsername());
        assertEquals(dto.getImdbId(), entity.getId().getImdbId());
        assertEquals(dto.getTitle(), entity.getTitle());
    }

}
