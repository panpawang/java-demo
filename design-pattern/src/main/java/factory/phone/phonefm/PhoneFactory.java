package factory.phone.phonefm;

public class PhoneFactory {

    public Phone creatPhone(String phoneName){

        if("HuaWei".equals(phoneName)){
            return new HuaWei();
        }
        else if("Apple".equals(phoneName)){
            return new Iphone();
        }
        return null;
    }
}
