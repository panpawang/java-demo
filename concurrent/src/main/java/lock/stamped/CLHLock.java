package lock.stamped;

import java.util.concurrent.atomic.AtomicReference;

public class CLHLock {
    private final AtomicReference<QNode> tail;  
    private final ThreadLocal<QNode> myPred;  
    private final ThreadLocal<QNode> myNode;  
  
    public CLHLock() {  
        tail = new AtomicReference<QNode>(new QNode());  
        myNode = new ThreadLocal<QNode>() {  
            protected QNode initialValue() {  
                return new QNode();  
            }  
        };  
  
        myPred = new ThreadLocal<QNode>();  
    }  
  
    public void lock() {  
        QNode node = myNode.get();  
        node.locked = true;  
        QNode pred = tail.getAndSet(node);  
        myPred.set(pred);  
        while (pred.locked) {  
        }  
    }  
  
    public void unlock() {  
        QNode node = myNode.get();  
        node.locked = false;  
        myNode.set(myPred.get());  
    }  
  
    private static class QNode {  
        volatile boolean locked;  
    }  
}
