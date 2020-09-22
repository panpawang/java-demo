package flow;//package flow;
//
//import java.util.concurrent.Flow.Processor;
//import java.util.concurrent.Flow.Subscription;
//import java.util.concurrent.SubmissionPublisher;
//import java.util.function.Function;
//
//public class TransformProcessor<T, R> extends SubmissionPublisher<R> implements Processor<T, R> {
//    private Function<? super T, ? extends R> function;
//    private Subscription subscription;
//
//    public TransformProcessor(Function<? super T, ? extends R> function) {
//        super();
//        this.function = function;
//    }
//
//    @Override
//    public void onSubscribe(Subscription subscription) {
//        this.subscription = subscription;
//        subscription.request(1);
//    }
//
//    @Override
//    public void onNext(T item) {
//        submit(function.apply(item));
//        subscription.request(1);
//    }
//
//    @Override
//    public void onError(Throwable throwable) {
//        throwable.printStackTrace();
//    }
//
//    @Override
//    public void onComplete() {
//        close();
//    }
//
//}
