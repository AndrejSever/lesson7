package ru.geekbrains.Java3.lesson7;

class Tested {
    @BeforeSuite
    void beforeSuite() {
        System.out.println("beforeSuite method");
    }


    @Test
    void test() {
        System.out.println("test method");
    }

    @Test
    void test0() {
        System.out.println("test0 method");
    }

    @Test(priority = 1)
    void test1() {
        System.out.println("test1 method, priority = 1");
    }

    @Test(priority = 3)
    void test2() {
        System.out.println("test2 method, priority = 3");
    }

    @Test(priority = 3)
    void test2_1() {
        System.out.println("test2_1 method, priority = 3");
    }

    @Test(priority = 6)
    void test3() {
        System.out.println("test3 method, priority = 6");
    }

    @Test(priority = 10)
    void test10() {
        System.out.println("test4 method, priority = 10");
    }

//    void testEmpty() {
//        System.out.println("testEmpty method");
//    }

    @AfterSuite
    void afterSuite() {
        System.out.println("afterSuite method");
    }


}
