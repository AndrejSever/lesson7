package ru.geekbrains.Java3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Start(new Tested());
    }

    public static void Start(Tested tested) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method[] declearedMethods = tested.getClass().getDeclaredMethods();
        Map<String, Integer> suiteMap = new HashMap<String, Integer>();
        Map<Integer, List<Method>> testMethods = new TreeMap<Integer, List<Method>>();
        for (Method method : declearedMethods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                Integer count = suiteMap.getOrDefault("BeforeSuite", 0);
                suiteMap.put("BeforeSuite", ++count);
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                Integer count = suiteMap.getOrDefault("AfterSuite", 0);
                suiteMap.put("AfterSuite", ++count);
            }

            if (method.isAnnotationPresent(Test.class)) {
                testMethods.computeIfAbsent(method.getAnnotation(Test.class).priority(),
                        a -> new ArrayList<>()).add(method);
            }
        }
        if (suiteMap.getOrDefault("BeforeSuite",0)!=1) {
            throw new RuntimeException("Метод BeforeSuite должен быть один");
        }
        if (suiteMap.getOrDefault("AfterSuite", 0) != 1) {
            throw new RuntimeException("Методи AfterSuite должен быть один");
        }
        for (Method method : declearedMethods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                method.invoke(tested);
            }
        }
        for (List<Method> methods : testMethods.values()) {
            for (Method method : methods) {
                method.invoke(tested);
            }
        }
        for (Method method : declearedMethods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                method.invoke(tested);
            }
        }
    }
}


