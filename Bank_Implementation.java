class Bank {
    int balance = 1000;

    public synchronized void deposit(int amount) {
        balance = balance + amount;
        System.out.println(Thread.currentThread().getName() + "deposited" + amount);
        System.out.println("Balance = " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance = balance - amount;
            System.out.println(Thread.currentThread().getName() + "withdrawn" + amount);
            System.out.println("Balance = " + balance);
        } else {
            System.out.println("Unable to withdraw");
        }
    }

    public synchronized void totalBalance() {
        System.out.println(Thread.currentThread().getName() + "balance " + balance);
    }
}

class depositThread extends Thread {
    Bank bank;

    public depositThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        for (int i = 0; i <= 5; i++) {
            bank.withdraw(50);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class withdrawThread extends Thread {
    Bank bank;

    public withdrawThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        for (int i = 0; i <= 5; i++) {
            bank.withdraw(100);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class totalBalanceThread extends Thread {
    Bank bank;

    public totalBalanceThread(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        for (int i = 0; i <= 5; i++) {
            bank.totalBalance();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

public class Bank_Implementation {
    public static void main(String[] args) {
        Bank bank = new Bank();

        depositThread d = new depositThread(bank);
        withdrawThread w = new withdrawThread(bank);
        totalBalanceThread t = new totalBalanceThread(bank);

        d.setName("Deposit");
        w.setName("Withdraw");
        t.setName("Total");

        d.start();
        w.start();
        t.start();
    }
}
