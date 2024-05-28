package abstractFactory;

public class AbstractFactoryMain {
    public static void main(String[] args){
        AbstractFactory searchFactory = FactoryProducer.getFactory();

//    Search searchFromText = searchFactory.getSearch(".\\src\\main\\resources\\lorem.txt");
//    searchFromText.search();

        Search searchFromWeb = searchFactory.getSearch("https://simplepage.com");
        searchFromWeb.search();
    }
}
