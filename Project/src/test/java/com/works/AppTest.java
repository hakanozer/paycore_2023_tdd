package com.works;

import org.junit.jupiter.api.*;
import org.springframework.util.Assert;

import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    Random rnd = null;
    public AppTest(){
        //System.out.println("AppTest Call");
    }

    @BeforeAll
    public void beforeAll() {
        rnd = new Random();
        System.out.println("beforeAll Call -1");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach  Call - " + System.currentTimeMillis());
    }

    @Test
    @DisplayName("Sample Test")
    @Order(1)
    public void sampleTest() {
        int end = 5 * 2;
        System.out.println( rnd.hashCode() );
        Assert.isTrue(end == 11, "sampleTest Error");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach Call - " + System.currentTimeMillis());
    }

    @Test
    @Order(2)
    @DisplayName("Sample Test - 1")
    public void sampleTest_1() {
        int end = 5 * 4;
        System.out.println( rnd.hashCode() );
        Assert.isTrue(end == 20, "sampleTest_1 Error");
    }

    @AfterAll
    public void afterAll() {
        System.out.println("afterAll Call -2");
        rnd = null;
    }

}
