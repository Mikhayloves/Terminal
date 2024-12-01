package org.sberuniversity.terminalserver;

public class TerminalServerImpl implements TerminalServer {
    private int balance;

    public TerminalServerImpl(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }
    @Override
    public void depositMoney(int amount) {
        if (amount % 100 != 0){ 
            System.out.println("Ошибка: сумма должна быть кратна 100.");
            return;
        }

        balance += amount;
        System.out.println("Вы успешно пополнили счет на " + amount + " руб. Текущий баланс: " + balance + " руб.");
    }
    @Override
    public void withdrawMoney(int amount) {
        if (amount % 100 != 0) {
            System.out.println("Ошибка: сумма должна быть кратна 100.");
            return;
        }
        if (amount > balance) {
            System.out.println("Ошибка: недостаточно средств.");
            return;
        }
        balance -= amount;
        System.out.println("Вы успешно сняли " + amount + " руб. Текущий баланс: " + balance + " руб.");
    }
}