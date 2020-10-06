package com.example.justjava3;

import org.junit.Test;

import static org.junit.Assert.*;

public class SampleUnitTest {

    @Test
    public void addNumbers() {
        //Create an object of SampleUnit class
        SampleUnit su = new SampleUnit();
        assertEquals(9,su.addNumbers(5,4));
    }
}