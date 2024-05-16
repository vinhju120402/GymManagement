/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Equipment;
import model.GymClass;
import model.Member;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import utils.GetInput;
import static utils.GetInput.sdf;

public class GymManagement {

    public void add(ArrayList<GymClass> listClass, ArrayList<Member> listMember, ArrayList<Equipment> listEquipment) {
        String id;
        int index;
        do {
            id = GetInput.regexString("Enter ID Class(Cxx): ", "^[C|c]\\d{2}$");
            index = indexOf(listClass, id);
            if (index != -1) {
                System.out.println("ID duplicated.Input again!");
            }
        } while (index != -1);
        String name = GetInput.getString("Enter Name: ");
        Date schedule = GetInput.getDate("Enter Schedule(dd/MM/yyyy): ");
        String capacity = GetInput.getString("Enter Capacity: ");

        System.out.println("ID   NAME           ADDRESS             CONTRACT       MEMBERSHIP");
        for (int i = 0; i < listMember.size(); i++) {
            System.out.printf("%-5s%-15s%-20s%-15s%-15s\n", listMember.get(i).getId(), listMember.get(i).getName(),
                    listMember.get(i).getAddress(), listMember.get(i).getContract(), listMember.get(i).getMembershipType());
        }
        String memberID = GetInput.getStringNotBlank("Enter Member ID: ");
        Member member = getMemberByID(listMember, memberID);

        System.out.println("ID   NAME           TYPE           QUANTITY       CONDITION");
        for (int i = 0; i < listEquipment.size(); i++) {
            System.out.printf("%-5s%-15s%-15s%-15d%-15s\n", listEquipment.get(i).getId(), listEquipment.get(i).getName(),
                    listEquipment.get(i).getType(), listEquipment.get(i).getQuantity(), listEquipment.get(i).getCondition());
        }
        String equipmentID = GetInput.getStringNotBlank("Enter Equipment ID: ");
        Equipment equipment = getEquipmentByID(listEquipment, equipmentID);

        if (member == null || equipment == null) {
            System.out.println("Member or Equipment does not exist!");
        } else {
            listClass.add(new GymClass(id, name, schedule, capacity, member, equipment));
            System.out.println("Gymclass's information has been added.Success.");
        }
    }

    public int indexOf(ArrayList<GymClass> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public Member getMemberByID(ArrayList<Member> listMember, String id) {
        Member m = null;
        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).getId().equalsIgnoreCase(id)) {
                m = listMember.get(i);
            }
        }
        return m;
    }

    public Equipment getEquipmentByID(ArrayList<Equipment> listEquipment, String id) {
        Equipment e = null;
        for (int i = 0; i < listEquipment.size(); i++) {
            if (listEquipment.get(i).getId().equalsIgnoreCase(id)) {
                e = listEquipment.get(i);
            }
        }
        return e;
    }

    public void update(ArrayList<GymClass> listClass, ArrayList<Member> listMember, ArrayList<Equipment> listEquipment) {
        String id = GetInput.regexString("Enter ID Gymclass(Cxx): ", "^[C|c]\\d{2}$");
        int index = indexOf(listClass, id);
        if (index == -1) {
            System.out.println("Gymclass does not exist.");
        } else {
            String newName = GetInput.getString("Enter New Name: ");
            if (!newName.isEmpty()) {
                listClass.get(index).setName(newName);
            }
            String newDate = GetInput.getString("Enter New Schedule(dd/MM/yyyy): ");
            boolean check = true;
            Date newSchedule = null;
            if (!newDate.isEmpty()) {
                try {
                    sdf.setLenient(false);
                    newSchedule = sdf.parse(newDate);
                } catch (ParseException e) {
                    System.out.println("Wrong format.Input again!");
                    check = false;
                }

                if (!check) {
                    newSchedule = GetInput.getDate("Enter New Schedule(dd/MM/yyyy): ");
                }
                listClass.get(index).setSchedule(newSchedule);
            }
            String newCapacity = GetInput.getString("Enter New Capacity: ");
            if (!newCapacity.isEmpty()) {
                listClass.get(index).setCapacity(newCapacity);
            }

            String confirmMember = GetInput.regexString("Do you want to change member(y/n): ", "^[Y|y|N|n]$");
            int choiceMember = 0;
            if (confirmMember.equalsIgnoreCase("y")) {
                System.out.println("ID   NAME           ADDRESS             CONTRACT       MEMBERSHIP");
                for (int i = 0; i < listMember.size(); i++) {
                    System.out.printf("%-5s%-15s%-20s%-15s%-15s\n", listMember.get(i).getId(), listMember.get(i).getName(),
                            listMember.get(i).getAddress(), listMember.get(i).getContract(), listMember.get(i).getMembershipType());
                }
                String memberID = GetInput.getStringNotBlank("Enter Member ID: ");
                Member member = getMemberByID(listMember, memberID);
                listClass.get(index).setMember(member);
            }

            String confirmEquipment = GetInput.regexString("Do you want to change equipment(y/n): ", "^[Y|y|N|n]$");
            int choiceEquipment = 0;
            if (confirmEquipment.equalsIgnoreCase("y")) {
                System.out.println("ID   NAME           TYPE           QUANTITY       CONDITION");
                for (int i = 0; i < listEquipment.size(); i++) {
                    System.out.printf("%-5s%-15s%-15s%-15d%-15s\n", listEquipment.get(i).getId(), listEquipment.get(i).getName(),
                            listEquipment.get(i).getType(), listEquipment.get(i).getQuantity(), listEquipment.get(i).getCondition());
                }
                String equipmentID = GetInput.getStringNotBlank("Enter Equipment ID: ");
                Equipment equipment = getEquipmentByID(listEquipment, equipmentID);
                listClass.get(index).setEquipment(equipment);
            }
            System.out.println("Gymclass's information has been updated.Success.");
        }
    }

    public void delete(ArrayList<GymClass> listClass) {
        String id = GetInput.regexString("Enter ID Gymclass(Cxx): ", "^[C|c]\\d{2}$");
        boolean check = false;
        for (int i = 0; i < listClass.size(); i++) {
            if (listClass.get(i).getId().equalsIgnoreCase(id)) {
                String confirm = GetInput.regexString("Are you sure remove(y/n): ", "^[Y|y|N|n]$");
                if (confirm.equalsIgnoreCase("y")) {
                    listClass.remove(i);
                    System.out.println("Gymclass's information has been deleted.Success.");
                    check = true;
                    break;
                } else {
                    System.out.println("You cancel.Fail.");
                }
            }
        }
        if(!check){
            System.out.println("Gymclass does not exist!");
        }
    }    
}
