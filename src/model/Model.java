package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Model {
    private static ModularCounter red = new ModularCounter(0, 256);
    private static ModularCounter green = new ModularCounter(0, 256);
    private static ModularCounter blue = new ModularCounter(0, 256);

    public static void main(String[] args)
    {
        int i;
        boolean start = true;
        Scanner sc = new Scanner(System.in);

        while (start)
        {
            System.out.println(" 1: See current HexCode \n 2: Increase or decrease a color by 10 \n 3: set a fixed value for a color \n 4: Exit");
            i = sc.nextInt();
            switch (i){
                case 1:
                    System.out.println(getHex());
                    break;
                case 2:
                    System.out.println("Which color would you like to change? (red, green or blue) ");
                    String color = sc.next();
                    System.out.println("Would you like to increase or decrese the number? (+ or -) ");
                    String sign =sc.next();

                    if (color.contains("red"))
                    {
                        changeColorViaRelativeValue(ColorCode.RED, sign);
                    }
                    else if (color.contains("green"))
                    {
                        changeColorViaRelativeValue(ColorCode.GREEN, sign);
                    }
                    else if (color.contains("blue"))
                    {
                        changeColorViaRelativeValue(ColorCode.BLUE, sign);
                    }
                    System.out.println("HexCode: " + getHex());
                    break;
                case 3:
                    System.out.println("Enter 3 Numbers between 0-255, if you don't want to change anything for a color type -1 \n");

                    System.out.println("red:");
                    int red = sc.nextInt();
                    if (red >= 0)
                        changeColorViaAbsoluteValue(ColorCode.RED, red);

                    System.out.println("green: ");
                    int green = sc.nextInt();
                    if (green >= 0)
                        changeColorViaAbsoluteValue(ColorCode.GREEN, green);

                    System.out.println("blue: ");
                    int blue = sc.nextInt();
                    if (blue >= 0)
                        changeColorViaAbsoluteValue(ColorCode.BLUE, blue);

                    System.out.println("HexCode: " + getHex());
                    break;
                default:
                    start = false;
            }
        }
    }
    public  static void changeColorViaAbsoluteValue(ColorCode cc, int value) {
        switch (cc) {
            case RED:
                red.reset();
                red.inc(value);
                break;
            case BLUE:
                blue.reset();
                blue.inc(value);
                break;
            case GREEN:
                green.reset();
                green.inc(value);
                break;
        }
    }
    public static void changeColorViaRelativeValue(ColorCode cc, String operator) {
        if (operator.contains("+")) {
            switch (cc) {
                case RED:
                    red.inc(10);
                    break;
                case BLUE:
                    blue.inc(10);
                    break;
                case GREEN:
                    green.inc(10);
                    break;
            }
        }
        else{
            switch (cc){
                case RED:
                    red.dec(10);
                    break;
                case BLUE:
                    blue.dec(10);
                    break;
                case GREEN:
                    green.dec(10);
                    break;
            }
        }
    }

    public static int getRed()
    {
        return red.getValue();
    }
    public static int getBlue()
    {
        return blue.getValue();
    }
    public static int getGreen()
    {
        return green.getValue();
    }

    public static String getHex()
    {
        String s = "#";
        String RV = Integer.toHexString(getRed());
        String GV = Integer.toHexString(getGreen());
        String BV = Integer.toHexString(getBlue());

        if (RV.length() == 1)
        {
            RV = "0" + RV;
        }
        if (GV.length() == 1)
        {
            GV = "0" + GV;
        }
        if (BV.length() == 1)
        {
            BV = "0" + BV;
        }

        s += RV;
        s += GV;
        s += BV;

        return s;
    }

    public static void loadFromFile()
    {
        try
        {
            FileReader fr = new FileReader("color.txt");
            BufferedReader br = new BufferedReader(fr);

            if(br.readLine().equals("Color File Format 1.0"))
            {
                changeColorViaAbsoluteValue(ColorCode.RED, Integer.parseInt(br.readLine()));
                changeColorViaAbsoluteValue(ColorCode.GREEN, Integer.parseInt(br.readLine()));
                changeColorViaAbsoluteValue(ColorCode.BLUE, Integer.parseInt(br.readLine()));
            }
            br.close();
        }
        catch (Exception ex)
        {
            System.out.printf("Error: %s%n",ex.getMessage());
        }
    }

    public static void saveToFile()
    {
        try
        {
            FileWriter fw = new FileWriter("color.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(System.lineSeparator());
            bw.write(String.valueOf(getRed()));
            bw.write(System.lineSeparator());
            bw.write(String.valueOf(getGreen()));
            bw.write(System.lineSeparator());
            bw.write(String.valueOf(getBlue()));

            bw.close();
        }
        catch (Exception ex)
        {
            System.out.printf("Error: %s%n",ex.getMessage());
        }
    }
}