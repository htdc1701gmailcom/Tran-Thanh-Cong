/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchbank;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 *
 * @author admin
 */
public class Bank {
    private double[] accounts;

    public Bank(int n, double initBalance) {
        accounts = new double[n];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initBalance;
        }
    }

    public int size() {
        return accounts.length;
    }

    public synchronized double getTotalBalance() {
        double total = 0;
        for (double account : accounts) {
            total += account;
        }
        return total;
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException {
        try {
        while (accounts[from] < amount) {
            System.out.println(Thread.currentThread().getName() + " đợi đủ tiền");
            wait();
            System.out.println(Thread.currentThread().getName() + " tiếp tục giao dịch");
        }
        accounts[from] -= amount;
        accounts[to] += amount;
        System.out.println("Chuyển " + amount + " từ account " + from + " sang account " + to);
        System.out.println("Tổng tiền của các account: " + getTotalBalance());
        notifyAll();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}