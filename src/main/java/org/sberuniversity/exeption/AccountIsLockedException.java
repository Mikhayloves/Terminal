package org.sberuniversity.exeption;

public class AccountIsLockedException extends Exception{
    private final long remainingTime;

    public AccountIsLockedException(long remainingTime) {
        super("Аккаунт заблокирован. Осталось " + remainingTime + " секунд до снятия блокировки.");
        this.remainingTime = remainingTime;
    }

    public long c() {
        return remainingTime;
    }
}
