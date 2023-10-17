/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import ma.service.MessageService;

/**
 *
 * @author SIMO LAAOUIBI
 */
public class TestCreateTableMessage {
    public static void main(String[] args) {
        MessageService ms = new MessageService();
        if (ms.createTable()) {
            System.out.println("table created");
        }
    }
}
