package future.myfutrue;

public class FutureClient {

    public Data submit(String requestData){

        FutureData futureData = new FutureData();

        new Thread(()->{
            RealData realData = new RealData(requestData);

            futureData.setRealData(realData);
        }).start();

        return futureData;

    }

    public static void main(String[] args) {
        FutureClient futureClient = new FutureClient();

        Data submit = futureClient.submit("123");

        System.out.println("主线程开始");

        String request = submit.getRequest();
        System.out.println(request);
    }
}
