/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Equipment;
import utils.GetInput;

public class EquipmentList {

    public void add(ArrayList<Equipment> listEquipment) {
        String id;
        int index;
        do {
            id = GetInput.regexString("Enter ID Equipment(Exx): ", "^[E|e]\\d{2}$");
            index = indexOf(listEquipment, id);
            if (index != -1) {
                System.out.println("ID duplicated.Input again!");
            }
        } while (index != -1);
        String name = GetInput.getStringNotBlank("Enter Name: ");
        String type = GetInput.getStringNotBlank("Enter Type: ");
        int quantity = GetInput.getInt("Enter Quantity: ", 1);
        String condition = GetInput.getString("Enter Condition: ");
        listEquipment.add(new Equipment(id, name, type, quantity, condition));
        System.out.println("Equipment's information has been added.Success.");
    }

    public int indexOf(ArrayList<Equipment> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void sortAndDisplay(ArrayList<Equipment> listEquipment) {
        if (listEquipment.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            Collections.sort(listEquipment, new Comparator<Equipment>() {
                @Override
                public int compare(Equipment o1, Equipment o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            System.out.println("ID   NAME           TYPE           QUANTITY       CONDITION");
            for (int i = 0; i < listEquipment.size(); i++) {
                System.out.printf("%-5s%-15s%-15s%-15d%-15s\n", listEquipment.get(i).getId(), listEquipment.get(i).getName(),
                        listEquipment.get(i).getType(), listEquipment.get(i).getQuantity(), listEquipment.get(i).getCondition());
            }
        }
    }

    public void update(ArrayList<Equipment> listEquipment) {
        String id = GetInput.regexString("Enter ID Equipment(Exx): ", "^[E|e]\\d{2}$");
        int index = indexOf(listEquipment, id);
        if (index == -1) {
            System.out.println("Equipment does not exist.");
        } else {
            String newName = GetInput.getString("Enter New Name: ");
            String newType = GetInput.getString("Enter New Type: ");
            String newQuantity = GetInput.getString("Enter New Quantity: ");
            String newCondition = GetInput.getString("Enter New Condition: ");
            if (!newName.isEmpty()) {
                listEquipment.get(index).setName(newName);
            }
            if (!newType.isEmpty()) {
                listEquipment.get(index).setType(newType);
            }
            int newQuantityEq = 0;
            if (!newQuantity.isEmpty()) {
                boolean check;
                do {
                    check = true;
                    try {
                        newQuantityEq = Integer.parseInt(newQuantity);
                    } catch (Exception e) {
                        System.out.println("Just number.");
                        check = false;
                    }
                    if (newQuantityEq < 1 || check == false) {
                        System.out.println("Just number and more than 0.");
                    }
                } while (newQuantityEq < 1 || check == false);
                listEquipment.get(index).setQuantity(newQuantityEq);
            }
            if (!newCondition.isEmpty()) {
                listEquipment.get(index).setCondition(newCondition);
            }
            System.out.println("Equipment's information has been updated.Success.");
        }
    }

    public void delete(ArrayList<Equipment> listEquipment) {
        String id = GetInput.regexString("Enter ID Equipment(Exx): ", "^[E|e]\\d{2}$");
        boolean check = false;
        for (int i = 0; i < listEquipment.size(); i++) {
            if (listEquipment.get(i).getId().equalsIgnoreCase(id)) {
                String confirm = GetInput.regexString("Are you sure remove(y/n): ", "^[Y|y|N|n]$");
                if (confirm.equalsIgnoreCase("y")) {
                    listEquipment.remove(i);
                    check = true;
                    System.out.println("Equipment's information has been deleted.Success.");
                    break;
                } else {
                    System.out.println("You cancel.Fail.");
                }
            }
        }
        if (check == false) {
            System.out.println("Equipment does not exist!");
        }
    }
}
