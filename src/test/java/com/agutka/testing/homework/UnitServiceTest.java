package com.agutka.testing.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UnitServiceTest {

    @Mock
    private UnitRepository unitRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private UnitService unitService;

    @Test
    void addedCargoShouldBeLoadedOnUnit() {
        //given
        Unit unit = new Unit(new Coordinates(0,0), 10, 10);
        Cargo cargo = new Cargo("package", 5);

        given(cargoRepository.findCargoByName("package")).willReturn(Optional.of(cargo));

        //when
        unitService.addCargoByName(unit, "package");

        //then
        then(cargoRepository).should().findCargoByName("package");
        assertThat(unit.getLoad(), is(5));
        assertThat(unit.getCargo().get(0), equalTo(cargo));
    }

    @Test
    void shouldThrowExceptionIfNoCargoIsFoundToAdd() {
        //given
        Unit unit = new Unit(new Coordinates(0,0),10,10);

        given(cargoRepository.findCargoByName("shouldNotFound")).willReturn(Optional.empty());

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.addCargoByName(unit, "shouldNotFound"));
    }

    @Test
    void shouldReturnUnitByCoordinates() {
        //given
        Unit unit = new Unit(new Coordinates(1,1),10,10);

        given(unitRepository.getUnitByCoordinates(new Coordinates(1,1))).willReturn(unit);

        //when
        Unit result = unitService.getUnitOn(new Coordinates(1,1));

        //then
        assertThat(result, sameInstance(unit));
    }

    @Test
    void shouldReturnExceptionWhenUnitNotFound() {
        //given
        given(unitRepository.getUnitByCoordinates(new Coordinates(1,1))).willReturn(null);

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> unitService.getUnitOn(new Coordinates(1,1)));
    }

}