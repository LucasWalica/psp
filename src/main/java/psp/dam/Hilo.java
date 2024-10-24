package psp.dam;

public class Hilo extends Thread {

    public Hilo(){
        super();
    }
    public Hilo(String name){
        super(name);
    }
    public Hilo(ThreadGroup groupName,String name){
        super(groupName, name);
    }

    @Override
    public void run(){
        for(int i=1;i<=100;i++){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){}
            System.out.println(getName()+ ",mensaje , "+ i);
        }
    }
}