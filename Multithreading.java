
import java.util.LinkedList;

public class Multithreading {
    LinkedList<Integer> list = new LinkedList<>();

    public void produce() throws Exception {
        int value = 0;
        while (true) {
            synchronized (this) {

                while (list.size() > 0) {
                    wait();
                }
                System.out.println("Produced = " + value);
                list.add(value);
                value++;
                notify();
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws Exception {
        while(true)
        {
            synchronized (this) {
                while(list.size() == 0)
                {
                    wait();
                }

                int val = list.removeFirst();
                System.out.println("Consumed = "+val);
                notify();
                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Multithreading pc = new Multithreading();

        Thread t1 = new Thread(new Runnable() {
           public void run()
           {
                try {
                    pc.produce();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
           } 
        });

        Thread t2 = new Thread(new Runnable() {
            public void run()
            {
                try {
                    pc.consume();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
