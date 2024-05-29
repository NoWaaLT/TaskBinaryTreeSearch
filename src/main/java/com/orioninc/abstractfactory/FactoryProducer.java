package com.orioninc.abstractfactory;

public class FactoryProducer {
    public static AbstractFactory getFactory() {
        return new SearchFactory();
    }
}
