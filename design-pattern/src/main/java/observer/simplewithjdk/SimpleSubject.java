package observer.simplewithjdk;

import java.util.Observable;

public class SimpleSubject extends Observable {

    private int value = 0;

    public void setValue(int value){
        this.value = value;
        setChanged();
        notifyObservers(value);
    }

    public int getValue(){
        return this.value;
    }
}
