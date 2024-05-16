/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Member;
import utils.GetInput;

public class MemberList {
    
    public void add(ArrayList<Member> listMember) {
        String id;
        int index;
        do {
            id = GetInput.regexString("Enter ID Member(Mxx): ", "^[M|m]\\d{2}$");
            index = indexOf(listMember, id);
            if (index != -1) {
                System.out.println("ID duplicated.Input again!");
            }
        } while (index != -1);
        String name = GetInput.getStringNotBlank("Enter Name: ");
        String address = GetInput.getStringNotBlank("Enter Address: ");
        String contract = GetInput.regexString("Enter Phone(0xx..): ", "^[0]\\d{9}$");
        String memberShip = GetInput.getStringNotBlank("Enter MemberShip: ");
        listMember.add(new Member(id, name, address, contract, memberShip));
        System.out.println("Member's information has been added.Success.");
    }

    public void sortAndDisplay(ArrayList<Member> listMember) {
        if (listMember.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            Collections.sort(listMember, new Comparator<Member>() {
                @Override
                public int compare(Member o1, Member o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            System.out.println("ID   NAME           ADDRESS             CONTRACT       MEMBERSHIP");
            for (int i = 0; i < listMember.size(); i++) {
                System.out.printf("%-5s%-15s%-20s%-15s%-15s\n", listMember.get(i).getId(), listMember.get(i).getName(),
                        listMember.get(i).getAddress(), listMember.get(i).getContract(), listMember.get(i).getMembershipType());
            }
        }
    }

    public void update(ArrayList<Member> listMember) {
        boolean conti = false;
        do {
            String id = GetInput.regexString("Enter ID Member(Mxx): ", "^[M|m]\\d{2}$");
            int index = indexOf(listMember, id);
            if (index == -1) {
                System.out.println("Member does not exist.");
            } else {
                String newName = GetInput.getString("Enter New Name: ");
                if (!newName.isEmpty()) {
                    listMember.get(index).setName(newName);
                }

                String newAddress = GetInput.getString("Enter New Address: ");
                if (!newAddress.isEmpty()) {
                    listMember.get(index).setAddress(newAddress);
                }

                String newContract = GetInput.getString("Enter New Contract: ");
                if (!newContract.isEmpty()) {
                    boolean check = newContract.matches("^[0]\\d{9}$");
                    if (check == false) {
                        newContract = GetInput.regexString("Enter Contract(0xx..): ", "^[0]\\d{9}$");
                    }
                    listMember.get(index).setContract(newContract);
                }

                String newMembership = GetInput.getString("Enter New Membership: ");
                if (!newMembership.isEmpty()) {
                    listMember.get(index).setMembershipType(newMembership);
                }
                System.out.println("Event's information has been updated.Success.");
                conti = GetInput.yesOrNo("Do you want to update continue(y/n): ");
            }
        } while (conti == true);
        System.out.println("Member's information has been updated.Success.");
    }

    public void delete(ArrayList<Member> listMember) {
        boolean check = false;
        String id = GetInput.regexString("Enter ID Member(Mxx): ", "^[M|m]\\d{2}$");
        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).getId().equalsIgnoreCase(id)) {
                String confirm = GetInput.regexString("Are you sure remove(y/n): ", "^[Y|y|N|n]$");
                if (confirm.equalsIgnoreCase("y")) {
                    listMember.remove(i);
                    check = true;
                    System.out.println("Member's information has been deleted.Success.");
                    break;
                } else {
                    System.out.println("You cancel.Fail.");
                }
            }
        }
        if(check == false){
            System.out.println("Member does not exist!");
        }
    }

    public int indexOf(ArrayList<Member> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

}
