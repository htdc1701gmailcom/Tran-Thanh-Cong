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
public class TransferMoney implements Runnable {
    private final Bank bank;
    private final int fromAcc;
    private final double maxAmount;
    private final int delay = 1000;

    public TransferMoney(Bank bank, int fromAcc, double maxAmount) {
        this.bank = bank;
        this.fromAcc = fromAcc;
        this.maxAmount = maxAmount;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int toAcc;
                do {
                    toAcc = (int) (Math.random() * bank.size());
                } while (toAcc == fromAcc);
                double amount = Math.random() * maxAmount;
                bank.transfer(fromAcc, toAcc, amount);
                Thread.sleep((int) (Math.random() * delay));
            }
        } catch (InterruptedException e) {
            System.out.println("Giao dịch chuyển tiền từ account " + fromAcc + " sang account " + " bị gián đoạn");
        }
    }
}
