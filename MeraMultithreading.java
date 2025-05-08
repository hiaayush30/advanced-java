import java.util.LinkedList;

public class MeraMultithreading {
    LinkedList<Integer> li = new LinkedList<>();

    public synchronized void produce() { // Synchronize the entire method
        try {
            while (li.size() > 0) {
                wait();
            }
            int value = (int) (Math.random() * 1000);
            li.add(value);
            System.out.println("added value:" + value);
            notify();
            Thread.sleep(500);
        } catch (InterruptedException e) { // Catch specific InterruptedException
            System.out.println("Producer Interrupted: " + e);
            Thread.currentThread().interrupt();
        }
    }

    public synchronized void consume() { // Synchronize the entire method
        try {
            while (li.size() == 0) {
                wait();
            }
            System.out.println("consumed value:" + li.getFirst());
            li.removeFirst();
            notify();
            Thread.sleep(500);
        } catch (InterruptedException e) { // Catch specific InterruptedException
            System.out.println("Consumer Interrupted: " + e);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        MeraMultithreading mt = new MeraMultithreading();

        class ProducerThread extends Thread {
            MeraMultithreading mainClass;

            public ProducerThread(MeraMultithreading mainClass) {
                this.mainClass = mainClass;
            }

            @Override
            public void run() {
                while (true) {
                    mainClass.produce(); // Call the synchronized method
                }
            }
        }

        class ConsumerThread extends Thread {
            MeraMultithreading mainClass;

            public ConsumerThread(MeraMultithreading mainClass) {
                this.mainClass = mainClass;
            }

            @Override
            public void run() {
                while (true) {
                    mainClass.consume(); // Call the synchronized method
                }
            }
        }
        try {
            ProducerThread pt = new ProducerThread(mt); // Pass the mt instance
            ConsumerThread ct = new ConsumerThread(mt); // Pass the mt instance
            pt.start();
            ct.start();

            // Remove join() calls for continuous execution in this example
            // pt.join();
            // ct.join();
        } catch (Exception e) {
            System.out.println("Error in main: " + e);
        }
    }
}