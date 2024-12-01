package org.sberuniversity.terminal;

import org.sberuniversity.exeption.AccountIsLockedException;
import org.sberuniversity.exeption.InvalidAmountException;
import org.sberuniversity.exeption.NotEnoughMoneyException;

public interface Terminal {
        void checkBalance() throws AccountIsLockedException;

        void deposit(int amount) throws AccountIsLockedException, InvalidAmountException;

        void withdraw(int amount) throws AccountIsLockedException, NotEnoughMoneyException, InvalidAmountException;
}


