package observer.simplewithjdk;

public class SimpleTest {

    public static void main(String[] args) {

        SimpleSubject simpleSubject = new SimpleSubject();

        SimpleObersver simpleObersver = new SimpleObersver(simpleSubject);

        simpleSubject.setValue(20);


    }
}
