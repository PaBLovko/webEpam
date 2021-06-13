package by.traning.task01.controller;

import by.traning.task01.bean.Cube;
import by.traning.task01.bean.FileForData;
import by.traning.task01.service.Calculation;
import by.traning.task01.dal.FileForDataLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Class for working with the user
 */
public class  Runner {
    private static Logger logger = LogManager.getLogger(Runner.class);

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
                taskOne(mode, console, fileForDataLogic, fileForData);
            }
            case 2 -> {
                System.out.println("Input the value x and y");
                taskTwo(mode, console, fileForDataLogic, fileForData);
            }
            case 3 -> {
                System.out.println("Input the length of the edge of the cube");
                taskThree(mode, console, fileForDataLogic, fileForData);
            }
            case 4 -> {
                System.out.println("Input the sides of the triangle a and b then the angle y between them");
                taskFour(mode, console, fileForDataLogic, fileForData);
            }
            case 5 -> {
                System.out.println("Input the bytes");
                taskFive(mode, console, fileForDataLogic, fileForData);
            }
            case 6 -> {
                System.out.println("Goodbye");
                return false;
            }
            default -> System.out.println("You did not select the provided operating mode");
        }
        return true;
    }

    private static void taskOne(int mode, Scanner console, FileForDataLogic fileForDataLogic, FileForData fileForData) {
        int a = 0;
        if (mode==1){
            a = console.nextInt();
        } else if (mode==2){
            a = Integer.parseInt(fileForDataLogic.readFileToString(fileForData));
        } else {
            System.out.println(CHECK);
        }
        try {
            int result = new Calculation().foldWithThree(a);
            System.out.printf(RESULT,result);
        }catch (ArithmeticException e){
            logger.error(e);
        }
    }

    private static void taskTwo(int mode, Scanner console, FileForDataLogic fileForDataLogic, FileForData fileForData) {
        double x = 0;
        double y = 0;
        if (mode==1){
            x = console.nextDouble();
            y = console.nextDouble();
        } else if (mode==2){
            x = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
            y = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
        } else {
            System.out.println(CHECK);
        }
        try {
            double result = new Calculation().trigonometricFormula(x,y);
            System.out.printf(RESULT,result);
        }catch (IllegalArgumentException e){
            logger.error(e);
        }
    }

    private static void taskThree(int mode, Scanner console, FileForDataLogic fileForDataLogic,
                                  FileForData fileForData) {
        double cubeEdgeLength = 0;
        if (mode==1){
            cubeEdgeLength = console.nextDouble();
        } else if (mode==2){
            cubeEdgeLength = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
        } else {
            System.out.println(CHECK);
        }
        try {
            Cube result = new Calculation().calculatingCubeParameters(cubeEdgeLength);
            System.out.printf(RESULT,result);
        }catch (IllegalArgumentException e){
            logger.error(e);
        }
    }


    private static void taskFour(int mode, Scanner console, FileForDataLogic fileForDataLogic, FileForData fileForData) {
        double a = 0;
        double b = 0;
        double y = 0;
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
        }
        try {
            double result = new Calculation().calculateAreaTriangle(a,b,y);
            System.out.printf(RESULT,result);
        }catch (NumberFormatException e){
            logger.error(e);
        }
    }

    private static void taskFive(int mode, Scanner console, FileForDataLogic fileForDataLogic,
                                 FileForData fileForData){
        double bytes = 0;
        if (mode==1){
            bytes = console.nextDouble();
        } else if (mode==2){
            bytes = Double.parseDouble(fileForDataLogic.readFileToString(fileForData));
        } else {
            System.out.println(CHECK);
        }
        try {
            double result = new Calculation().bytesToKilobytes(bytes);
            System.out.printf(RESULT,result);
        }catch (IllegalArgumentException e){
            logger.error(e);
        }
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
