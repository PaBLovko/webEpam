package by.traning.task01.controller;

import by.traning.task01.bean.Cube;
import by.traning.task01.bean.FileForData;
import by.traning.task01.service.Calculation;
import by.traning.task01.service.FileForDataLogic;
import java.util.Scanner;

/**
 * Class for working with the user
 */
public class  Runner {

    /**
     * The constant containing the output of the result
     */
    private static final String RESULT = "Result = %s%n";

    /**
     * The constant containing the information about file
     */
    private static final String CHECK = "Check the file";

    /**
     * The constant containing the path
     */
    private static final String PATH_DATA = "src/main/resources/file.txt";

    /**
     * The method for displaying information about completed tasks
     */
    private static void showMenu(){
        System.out.println("""
                    Menu
                click on the specified number to start the task
                1 - Task 2 : Find the value of the function: c = 3 + a
                2 - Task 10 : Calculate the value of the expression using the formula
                                c = (sin x + cos y) / (cos x - sin y) * tg xy
                3 - Task 18 : Find the area of the face, the total surface area, and the volume of this cube
                4 - Task 26 : Find the area of a triangle whose two sides are equal to a and b
                                also the angle between these sides is y
                5 - Task 34 : Convert the amount of information in bytes to larger units of information.
                6 - Exit
                """);
    }

    /**
     * The method for displaying information about operating modes
     */
    private static void showMode(){
        System.out.println("""
                    Select the input mode
                1 - Console
                2 - file
                """);
    }

    /**
     * The method for calling tasks via the console
     * @param choice The task selected
     * @return The state responsible for exiting the application
     */
    private static boolean consoleMode(int choice, int mode){
        Scanner console = new Scanner(System.in);
        FileForDataLogic fileForDataLogic = new FileForDataLogic();
        FileForData fileForData = new FileForData(PATH_DATA);
        fileForDataLogic.createFile(fileForData);
        switch (choice) {
            case 1 -> {
                System.out.println("Input the value a");
                int a;
                if (mode==1){
                    a = console.nextInt();
                } else if (mode==2){
                    a = Integer.parseInt(fileForDataLogic.readFileToString(fileForData));
                } else {
                    System.out.println(CHECK);
                    break;
                }
                int result = new Calculation().foldWithThree(a);
                System.out.printf(RESULT,result);
            }
            case 2 -> {
                System.out.println("Input the value x and y");
                double x;
                double y;
                if (mode==1){
                    x = console.nextDouble();
                    y = console.nextDouble();
                } else if (mode==2){
                    x = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
                    y = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
                } else {
                    System.out.println(CHECK);
                    break;
                }
                double result = new Calculation().trigonometricFormula(x,y);
                System.out.printf(RESULT,result);
            }
            case 3 -> {
                System.out.println("Input the length of the edge of the cube");
                double cubeEdgeLength;
                if (mode==1){
                    cubeEdgeLength = console.nextDouble();
                } else if (mode==2){
                    cubeEdgeLength = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
                } else {
                    System.out.println(CHECK);
                    break;
                }
                Cube result = new Calculation().calculatingCubeParameters(cubeEdgeLength);
                System.out.printf(RESULT,result);
            }
            case 4 -> {
                System.out.println("Input the sides of the triangle a and b then the angle y between them");
                double a;
                double b;
                double y;
                if (mode==1){
                    a = console.nextDouble();
                    b = console.nextDouble();
                    y = console.nextDouble();
                } else if (mode==2){
                    a = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
                    b = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
                    y = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
                } else {
                    System.out.println(CHECK);
                    break;
                }
                double result = new Calculation().getAreaTriangle(a,b,y);
                System.out.printf(RESULT,result);
            }
            case 5 -> {
                System.out.println("Input the bytes");
                double bytes;
                if (mode==1){
                    bytes = console.nextDouble();
                } else if (mode==2){
                    bytes = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
                } else {
                    System.out.println(CHECK);
                    break;
                }
                double result = new Calculation().bytesToKilobytes(bytes);
                System.out.printf(RESULT,result);
            }
            case 6 -> {
                System.out.println("Goodbye");
                return false;
            }
            default -> System.out.println("You did not select the provided operating mode");
        }
        return true;
    }

    public static void main(String[] args) {
        boolean isCycleWorks = true;
        while (isCycleWorks){
            Scanner input = new Scanner(System.in);
            showMode();
            int mode = input.nextInt();
            showMenu();
            int choice = input.nextInt();
            isCycleWorks = consoleMode(choice, mode);
        }
    }
}
