/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestPackage;

import java.util.Date;
import ma.beans.Message;
import ma.service.EmployeService;
import ma.service.MessageService;

/**
 *
 * @author SIMO LAAOUIBI
 */
public class TestMessage {

    public static void main(String[] args) {
        EmployeService es = new EmployeService();
        MessageService ms = new MessageService();
        ms.create(new Message("Tp1", "j'ai completé tp1 ", new Date(), es.findById(5), es.findById(10)));
        ms.create(new Message("Tp2", "j'ai pas completé tp2 ", new Date(), es.findById(7), es.findById(12)));
        ms.create(new Message("seance d'aujourdhui", "bonjour monsieur est ce que seance d'aujourdhui se sera programé au 8h:00  ", new Date(), es.findById(11), es.findById(10)));
    int i = 0;
    for(Message m : ms.findAll()){
        if(m.getEmpRecepteur().getId()==10){
            System.out.println("message "+i+":"+"  "+m.getSujet());
        i++;  
        }
          
    }
    }
}
