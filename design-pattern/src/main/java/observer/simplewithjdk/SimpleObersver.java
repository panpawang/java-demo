package observer.simplewithjdk;

import java.util.Observable;
import java.util.Observer;

public class SimpleObersver implements Observer {

    private int value;

    private Observable observable;

    public SimpleObersver(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }


    public void display(){
        System.out.println("Value : " + value);
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println(arg);

        this.value = (int) arg;

        display();
        if(o instanceof SimpleSubject){
            SimpleSubject simpleSubject = (SimpleSubject) o;

            this.value = simpleSubject.getValue();

            display();
        }


    }
}
