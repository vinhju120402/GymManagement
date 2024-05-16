/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GetInput {

    public static Scanner sc = new Scanner(System.in);
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static int getInt(String input, int min) {
        int n;
        while (true) {
            try {
                System.out.print(input);
                n = Integer.parseInt(sc.nextLine());
                if (n < min) {
                    System.out.println("Number more than " + min);
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println("Just interger.Input again!");
            }
        }
    }

    public static int getInt(String input, int min, int max) {
        int n;
        while (true) {
            try {
                System.out.print(input);
                n = Integer.parseInt(sc.nextLine());
                if (n < min || n > max) {
                    System.out.println("The number must be greater than" + min + " and less than " + max);
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println("Just interger.Input again!");
            }
        }
    }

    public static String getString(String input) {
        String tmp;
        System.out.print(input);
        return tmp = sc.nextLine();
    }

    public static String getStringNotBlank(String input) {
        String tmp;
        while (true) {
            System.out.print(input);
            tmp = sc.nextLine().trim();
            if (tmp.length() == 0) {
                System.out.println("Not blank.Input again!");
            } else {
                return tmp;
            }
        }
    }

    public static String regexString(String input, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(input);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || match == false) {
                System.out.println("Wrong.Input again!");
            } else {
                return id;
            }
        }
    }

    public static boolean yesOrNo(String input) {
        String confirm = regexString(input, "^[Y|y|N|n]$");
        return confirm.equalsIgnoreCase("y");
    }

    public static Date getDate(String input) {
        String data;
        while (true) {
            System.out.print(input);
            data = sc.nextLine();
            try {
                sdf.setLenient(false);
                return sdf.parse(data);
            } catch (ParseException e) {
                System.out.println("Wrong format.Input again!");
            }
        }
    }

}
