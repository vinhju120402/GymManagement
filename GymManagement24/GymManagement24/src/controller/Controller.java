/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import model.Equipment;
import model.GymClass;
import model.Member;

public class Controller {

    private ArrayList<Member> listMember = new ArrayList();
    private ArrayList<Equipment> listEquipment = new ArrayList();
    private ArrayList<GymClass> listClass = new ArrayList();
    private MemberList ml;
    private EquipmentList el;
    private GymManagement gm;

    public Controller() {
        this.ml = new MemberList();
        this.el = new EquipmentList();
        this.gm = new GymManagement();
    }

    public void addNewMember() {
        ml.add(listMember);
    }

    public void sortAndDisplayMember() {
        ml.sortAndDisplay(listMember);
    }

    public void viewAndUpdateMember() {
        ml.update(listMember);
    }

    public void deleteAMember() {
        ml.delete(listMember);
    }

    /////////////
    public void addNewEquipment() {
        el.add(listEquipment);
    }

    public void sortAndDisplayEquipment() {
        el.sortAndDisplay(listEquipment);
    }

    public void updateEquipment() {
        el.update(listEquipment);
    }

    public void removeEquipment() {
        el.delete(listEquipment);
    }
    ////////////

    public void addNewClass() {
        gm.add(listClass, listMember, listEquipment);
    }

    public void updateClass() {
        gm.update(listClass, listMember, listEquipment);
    }

    public void removeClass() {
        gm.delete(listClass);
    }

    public void loadDataFromFile() {
        boolean check = true;
        try {
            FileInputStream fis = new FileInputStream("member.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listMember = (ArrayList<Member>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        try {
            FileInputStream fis = new FileInputStream("equipment.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listEquipment = (ArrayList<Equipment>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        try {
            FileInputStream fis = new FileInputStream("gymclass.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listClass = (ArrayList<GymClass>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            check = false;
        }
        if (check) {
            System.out.println("Load data from file succes.");
        } else {
            System.out.println("Load data from file fail.");
        }
    }

    public void saveDataToFile() {
        boolean check = true;
        try {
            FileOutputStream fos = new FileOutputStream("member.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listMember);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save data to member.dat fail.");
            check = false;
        }
        try {
            FileOutputStream fos = new FileOutputStream("equipment.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listEquipment);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save data to equipment.dat fail.");
            check = false;
        }
        try {
            FileOutputStream fos = new FileOutputStream("gymclass.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listClass);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println("Save data to gymclass.dat fail.");
            check = false;
        }
        if (check) {
            System.out.println("Save data to file succes.");
        } else {
            System.out.println("Save data to file fail.");
        }
    }
}
