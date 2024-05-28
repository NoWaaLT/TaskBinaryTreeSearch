package abstractFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory() {
        return new SearchFactory();
    }
}
