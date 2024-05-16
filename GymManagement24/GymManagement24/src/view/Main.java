/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu("GYM Management.");
        menu.addMenu("             1. Member Management.");
        menu.addMenu("             2. Equipment Management.");
        menu.addMenu("             3. Class Management.");
        menu.addMenu("             4. Data Management.");
        menu.addMenu("             5. Exist.");
        
        Controller controller =new Controller();
        controller.loadDataFromFile(); 
        
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    Menu menuMember = new Menu("Member Management.");
                    menuMember.addMenu("             1. Create a new member.");
                    menuMember.addMenu("             2. Sort and display existing member information.");
                    menuMember.addMenu("             3. View and update existing member information.");
                    menuMember.addMenu("             4. Delete a member.");
                    menuMember.addMenu("             5. Return main menu.");

                    int choiceMember;
                    do {
                        menuMember.printMenu();
                        choiceMember = menuMember.getChoice();
                        switch (choiceMember) {
                            case 1:
                                controller.addNewMember();
                                break;
                            case 2:
                                controller.sortAndDisplayMember();
                                break;
                            case 3:
                                controller.viewAndUpdateMember();
                                break;
                            case 4:
                                controller.deleteAMember();
                                break;
                            case 5:
                                System.out.println("Return main menu..Exist.");
                                break;
                        }
                    } while (choiceMember != 5);
                    break;
                case 2:
                    Menu menuEquipment = new Menu("Equipment Management.");
                    menuEquipment.addMenu("             1. Add new equipment.");
                    menuEquipment.addMenu("             2. Sort and display equipment.");
                    menuEquipment.addMenu("             3. Update and manage equipment.");
                    menuEquipment.addMenu("             4. Remove equipment.");
                    menuEquipment.addMenu("             5. Return main menu.");

                    int choiceEquipment;
                    do {
                        menuEquipment.printMenu();
                        choiceEquipment = menuEquipment.getChoice();
                        switch (choiceEquipment) {
                            case 1:
                                controller.addNewEquipment();
                                break;
                            case 2:
                                controller.sortAndDisplayEquipment();
                                break;
                            case 3:
                                controller.updateEquipment();
                                break;
                            case 4:
                                controller.removeEquipment();
                                break;
                            case 5:
                                System.out.println("Return main menu..Exist.");
                                break;
                        }
                    } while (choiceEquipment != 5);
                    break;
                case 3:
                    Menu menuClass = new Menu("Class Management.");
                    menuClass.addMenu("             1. Add new classes.");
                    menuClass.addMenu("             2. Update and manage class.");
                    menuClass.addMenu("             3. Remove a class.");
                    menuClass.addMenu("             4. Return main menu.");

                    int choiceClass;
                    do {
                        menuClass.printMenu();
                        choiceClass = menuClass.getChoice();
                        switch (choiceClass) {
                            case 1:
                                controller.addNewClass();
                                break;
                            case 2:
                                controller.updateClass();
                                break;
                            case 3:
                                controller.removeClass();
                                break;
                            case 4:
                                System.out.println("Return main menu..Exist.");
                                break;
                        }
                    } while (choiceClass != 4);
                    break;
                case 4:
                    controller.saveDataToFile();
                    break;
                case 5:
                    System.out.println("Exist!");
                    break;
            }
        } while (choice != 5);
    }
}
