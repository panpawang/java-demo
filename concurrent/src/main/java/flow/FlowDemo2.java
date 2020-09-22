package flow;//package flow;
//import java.util.Arrays;
//import java.util.concurrent.SubmissionPublisher;
//
//public class FlowDemo2
//{
//   public static void main(String[] args)
//   {
//      // Create a publisher.
//
//      SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
//
//      // Create a subscriber and register it with the publisher.
//
//      MySubscriber<String> subscriber = new MySubscriber<>();
//      MySubscriber<String> subscriber2 = new MySubscriber<>();
//
//      TransformProcessor<String,String> toUpperCase = new TransformProcessor<>(String::toUpperCase);
//      TransformProcessor<String,String> toLowverCase = new TransformProcessor<>(String::toLowerCase);
//
//      publisher.subscribe(toUpperCase);
//      publisher.subscribe(toLowverCase);
//
//      toUpperCase.subscribe(subscriber);
//      toLowverCase.subscribe(subscriber2);
//
//
//
//      // Publish several data items and then close the publisher.
//
//      System.out.println("Publishing data items...");
//      String[] items = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
//                         "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
//      Arrays.asList(items).stream().forEach(i ->{
//    	  publisher.submit(i);
//    	  System.out.println(Thread.currentThread().getName()+" publish "+i);
//      });
//      publisher.close();
//
//      try
//      {
//         synchronized("A")
//         {
//            "A".wait();
//         }
//      }
//      catch (InterruptedException ie)
//      {
//      }
//   }
//}
//
