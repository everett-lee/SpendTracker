package Inputter;
import java.util.Scanner;

public class Inputter {
    private Scanner inputter;

    public Inputter() {
        inputter = new Scanner(System.in);
    }

    public String getStringInput() {
        String input = inputter.nextLine();
        return input;
    }

    public float getFloatInput() {
        float input = Float.parseFloat(inputter.nextLine());
        return input;
    }

    public int getIntInput() {
        int input = Integer.parseInt(inputter.nextLine());
        return input;
    }

}
