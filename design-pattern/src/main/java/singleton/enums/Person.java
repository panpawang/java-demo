package singleton.enums;

public class Person{
    private Person(){}

    public static Person getInstance(){
        return PersonSingleton.INSTANCE.getInstance();
    }

    enum PersonSingleton{
        INSTANCE;
        private Person person;
        private PersonSingleton(){
            this.person = new Person();
        }

        public Person getInstance(){
            return this.person;
        }
    }


    public static void main(String[] args) {

        Person instance1 = Person.getInstance();
        Person instance2 = Person.getInstance();

        System.out.println(instance1 == instance2);

    }

}