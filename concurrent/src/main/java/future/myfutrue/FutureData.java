package future.myfutrue;

public class FutureData extends Data {



    private boolean FLAG = false;

    private RealData realData;

    public synchronized void setRealData(RealData realData){

        if(FLAG){
            return;
        }
        this.realData = realData;
        FLAG = true;
        notify();
    }

    @Override
    public synchronized String getRequest() {

        while (!FLAG){

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        return this.realData.getRequest();
    }
}
