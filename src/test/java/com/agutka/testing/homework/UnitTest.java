package com.agutka.testing.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit unit;

    @BeforeEach
    void setUp() {
        unit = new Unit(new Coordinates(0,0),100,100);
    }

    @Test
    void throwExceptionIfNotEnoughFuelForMove() {
        //then
        assertThrows(IllegalStateException.class, () -> unit.move(50,51));
    }

    @Test
    void unitShouldNotMoveWhenMaxFuelAre0() {
        //given
        Unit unit1 = new Unit(new Coordinates(0,0),0,10);

        //then
        assertThrows(IllegalStateException.class, () -> unit1.move(0,1));
    }

    @Test
    void shouldSubtractFuelAfterMove() {
        //given
        int fuel = unit.getFuel();

        //when
        unit.move(10,10);
        int fuelAfterMove = unit.getFuel();

        //then
        assertEquals(fuel, fuelAfterMove + 20);
        //or easier without "given" part
        assertThat(unit.getFuel(), is(80));
    }

    @Test
    void movedUnitShouldReturnNewCoordinates() {
        //when
        Coordinates move = unit.move(2,2);
        unit.move(10,1);

        //then
        assertThat(move, equalTo(new Coordinates(2,2)));
    }

    @Test
    void fuelCantBeBiggerThanMaxCapacityOfFuel() {
        //given
        int fuel = unit.getFuel();

        //when
        unit.tankUp();
        int fuelAfterTankUp = unit.getFuel();

        //then
        assertEquals(fuel, fuelAfterTankUp);
    }

    @Test
    void tankUpShouldAddingFuel() {
        //given
        int fuel = unit.getFuel();

        //when
        unit.move(25,25);
        unit.tankUp();
        int fuelAfterTankUp = unit.getFuel();

        //then
        assertNotEquals(fuel,fuelAfterTankUp);
    }

    @Test
    void shouldThrowExceptionWhenLoadToMuchCargo() {
        //then
        assertThrows(IllegalStateException.class, () -> unit.loadCargo(new Cargo("Shoes", 101)));
    }

    @Test
    void shouldAddingWeightAfterLoadingCargo() {
        //when
        unit.loadCargo(new Cargo("Shoes", 10));
        //then
        assertEquals(10, unit.getLoad());
    }

    @Test
    void unloadCargoShouldSubtractWeight() {
        //given
        Cargo cargo = new Cargo("Car", 10);
        Cargo cargo1 = new Cargo("Belts", 5);

        //when
        unit.loadCargo(cargo);
        unit.loadCargo(cargo1);
        unit.unloadCargo(cargo);

        //then
        assertEquals(5, unit.getLoad());
    }

    @Test
    void whenUnloadAllCargoWeightShouldBe0() {
        //given
        Cargo cargo = new Cargo("Car", 10);
        Cargo cargo1 = new Cargo("Belts", 5);

        //when
        unit.loadCargo(cargo);
        unit.loadCargo(cargo1);
        unit.unloadAllCargo();

        //then
        assertEquals(0, unit.getLoad());
    }
}