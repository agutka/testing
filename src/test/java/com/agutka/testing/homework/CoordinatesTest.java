package com.agutka.testing.homework;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void constructorShouldFailIfAnyValueBelow0() {
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(-1,100));
    }

    @Test
    void constructorShouldFailIfAnyValueAbove100() {
        //then
        assertThrows(IllegalArgumentException.class, () -> new Coordinates(0,101));
    }

    @Test
    void copyShouldReturnNewObject() {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates copy = Coordinates.copy(coordinates,0,0);

        //then
        assertThat(coordinates, not(sameInstance(copy)));
        assertThat(coordinates, equalTo(copy));
    }

    @Test
    void copyShouldReturnAddCoordinates() {
        //given
        Coordinates coordinates = new Coordinates(10,10);

        //when
        Coordinates copy = Coordinates.copy(coordinates,5,10);

        //then
        assertThat(copy.getX(), equalTo(15));
        assertThat(copy.getY(), equalTo(20));
    }
}