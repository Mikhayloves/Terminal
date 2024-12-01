package org.sberuniversity;


import org.sberuniversity.exeption.AccountIsLockedException;
import org.sberuniversity.exeption.InvalidAmountException;
import org.sberuniversity.exeption.InvalidPinException;
import org.sberuniversity.exeption.NotEnoughMoneyException;
import org.sberuniversity.pinvalidator.PinValidator;
import org.sberuniversity.terminal.TerminalImpl;
import org.sberuniversity.terminalserver.TerminalServerImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TerminalServerImpl terminalServer = new TerminalServerImpl(10_000);
        PinValidator pinValidator = new PinValidator();
        TerminalImpl terminal = new TerminalImpl(terminalServer, pinValidator);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Введите PIN: ");
                    String pin = scanner.nextLine();
                    try {
                        terminal.authorize(pin);
                        System.out.println("Авторизация прошла успешно!");
                        terminal.checkBalance();
                    } catch (InvalidPinException | AccountIsLockedException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    System.out.print("Введите сумму для пополнения: ");
                    try {
                        int depositAmount = Integer.parseInt(scanner.nextLine());
                        terminal.deposit(depositAmount);
                    } catch (AccountIsLockedException | InvalidAmountException | NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.print("Введите сумму для снятия: ");
                    try {
                        int withdrawAmount = Integer.parseInt(scanner.nextLine());
                        terminal.withdraw(withdrawAmount);
                    } catch (AccountIsLockedException | InvalidAmountException | NotEnoughMoneyException |
                             NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Ошибка: выберите корректное действие.");
            }
        }
    }
    public static void menu() {
        System.out.println("Добро пожаловать!\nВыберите что хотите сделать:\n1. Узнать баланс\n2. Пололожить средства\n3. Снять средства\n4. Выход");
    }
}