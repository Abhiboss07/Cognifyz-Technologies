import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentBank {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- Starting Unsynchronized Bank Account Simulation ---");
        runSimulation(new UnsynchronizedBankAccount());

        System.out.println("\n\n--- Starting Synchronized Bank Account Simulation ---");
        runSimulation(new SynchronizedBankAccount());
        
        System.out.println("\n\n--- Starting Bank Account Simulation with ReentrantLock ---");
        runSimulation(new LockedBankAccount());
    }

    public static void runSimulation(BankAccount account) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                account.deposit(100);
                account.withdraw(50);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES); 

        System.out.println("Final Balance: " + account.getBalance());
        System.out.println("Expected Balance: 5000");
    }
}

interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}

class UnsynchronizedBankAccount implements BankAccount {
    private double balance = 0;

    @Override
    public void deposit(double amount) {
        System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
        double newBalance = balance + amount;
        try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        balance = newBalance;
    }

    @Override
    public void withdraw(double amount) {
        System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
        double newBalance = balance - amount;
        try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        balance = newBalance;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}

class SynchronizedBankAccount implements BankAccount {
    private double balance = 0;

    @Override
    public synchronized void deposit(double amount) {
        System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
        double newBalance = balance + amount;
        try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        balance = newBalance;
    }

    @Override
    public synchronized void withdraw(double amount) {
        System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
        double newBalance = balance - amount;
        try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        balance = newBalance;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}

class LockedBankAccount implements BankAccount {
    private double balance = 0;
    private final Lock lock = new ReentrantLock();

    @Override
    public void deposit(double amount) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is depositing " + amount);
            double newBalance = balance + amount;
            Thread.sleep(5);
            balance = newBalance;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void withdraw(double amount) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            double newBalance = balance - amount;
            Thread.sleep(5);
            balance = newBalance;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
