package org.sberuniversity.terminal;

import org.sberuniversity.terminalserver.TerminalServerImpl;
import org.sberuniversity.exeption.AccountIsLockedException;
import org.sberuniversity.exeption.InvalidAmountException;
import org.sberuniversity.exeption.InvalidPinException;
import org.sberuniversity.exeption.NotEnoughMoneyException;
import org.sberuniversity.pinvalidator.PinValidator;

public class TerminalImpl implements Terminal {
    private final TerminalServerImpl server;
    private final PinValidator pinValidator;
    private boolean isAuthorized;

    public TerminalImpl(TerminalServerImpl server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.isAuthorized = false;
    }

    public void authorize(String pin) throws InvalidPinException {
        pinValidator.validatePin(pin);
        isAuthorized = true;
    }

    @Override
    public void checkBalance() throws AccountIsLockedException {
        ensureAuthorization();
        System.out.println("Ваш баланс: " + server.getBalance() + " руб.");
    }

    @Override
    public void deposit(int amount) throws AccountIsLockedException, InvalidAmountException {
        if (amount % 100 == 0) {
            ensureAuthorization();
            server.depositMoney(amount);
            System.out.println("На счет внесено: " + amount + " руб.");
        } else {
            System.out.println("Данная сумма не внесена на счет");
        }
    }

    @Override
    public void withdraw(int amount) throws InvalidAmountException, NotEnoughMoneyException, AccountIsLockedException {
        ensureAuthorization();
        server.withdrawMoney(amount);
        System.out.println("Со счета снято: " + amount + " руб.");
    }

    private void ensureAuthorization() throws AccountIsLockedException {
        if (!isAuthorized) {
            throw new AccountIsLockedException(0);
        }
    }
}