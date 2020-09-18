package factory.phone.phonefm;

public class FactoryTest {

    public static void main(String[] args) {

        PhoneFactory phoneFactory = new PhoneFactory();

        Phone huaWei = phoneFactory.creatPhone("HuaWei");

        Phone apple = phoneFactory.creatPhone("Apple");

        System.out.println(huaWei.brand());
        System.out.println(apple.brand());
    }
}
