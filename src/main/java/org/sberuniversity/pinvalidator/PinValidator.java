package org.sberuniversity.pinvalidator;

import org.sberuniversity.exeption.InvalidPinException;

public class PinValidator {
    private static final String VALIDE_PIN_CODE = "2234";
    private static final int LENGTH_PIN = 4;
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_ACCOUNT = 10000;
    private int attempts = 0; //
    private long lockTimeUntil = 0;

    public boolean validatePin(String pin) throws InvalidPinException {
        if (System.currentTimeMillis() < lockTimeUntil) {
            long remainingTime = (lockTimeUntil - System.currentTimeMillis()) / 1000;
            throw new InvalidPinException("Ошибка: аккаунт заблокирован. Подождите " + remainingTime + " секунд.");
        }
        if (pin.length() != LENGTH_PIN) {
            throw new InvalidPinException("Ошибка: PIN-код должен содержать ровно 4 цифры.");
        }
        if (!pin.matches("\\d+")) {
            throw new InvalidPinException("Ошибка: PIN-код должен содержать только цифры.");
        }
        if (pin.equals(VALIDE_PIN_CODE)) {
            System.out.println("PIN введен верно.");
            attempts = 0;
            return true;
        }
        attempts++;
        if (attempts >= MAX_ATTEMPTS) {
            lockTimeUntil = System.currentTimeMillis() + LOCK_ACCOUNT;
            attempts = 0;
            throw new InvalidPinException("Ошибка: неверный PIN. Аккаунт заблокирован на 10 секунд.");
        }
        throw new InvalidPinException("Ошибка: неверный PIN. Осталось попыток: " + (MAX_ATTEMPTS - attempts));
    }
}