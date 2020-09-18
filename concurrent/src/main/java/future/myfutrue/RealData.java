package future.myfutrue;


public class RealData extends Data {

    private String result;

    public RealData(String data){

        System.out.println("正在进行进行耗时操作");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("操作执行完毕");
        this.result = data;

    }

    @Override
    public String getRequest() {
        return this.result;
    }
}
