package by.traning.task06.option3.service.parser;

import by.traning.task06.option3.service.exception.ServiceException;
import by.traning.task06.option3.service.validator.ArrayValidator;
import by.traning.task06.option3.service.validator.NumberThreadsValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LineParser {
    private static Logger log = LogManager.getLogger(LineParser.class);

    public int[][] returnTwoDimArray(List<String> lines) throws ServiceException {
        if (lines.isEmpty()) {
            log.error("List lines from file is empty");
            throw new ServiceException("List is empty.");
        }
        int length = lines.size() - 1;
        int[][] array = new int[length][length];
        for (int i = 0; i < length; i++) {
            array[i] = returnArray(lines.get(i));
        }
        if (checkLength(array) && checkDiagonal(array)) {
            return array;
        } else {
            log.error("Array is wrong.");
            throw new ServiceException("Array is wrong.");
        }
    }

    private int[] returnArray(String line) throws ServiceException {
        ArrayValidator validator = new ArrayValidator();
        int[] array;
        if (validator.isLineValid(line)) {
            log.info("Line from file is valid.");
            String[] parts = line.split("\\s+");
            int length = parts.length;
            array = new int[length];
            for (int i = 0; i < length; i++) {
                array[i] = Integer.parseInt(parts[i]);
            }
        } else {
            log.error("Array is wrong.");
            throw new ServiceException("Array is wrong.");
        }
        return array;
    }

    private boolean checkDiagonal(int[][] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i][i] != 0) {
                log.error("Diagonal elements are not zero");
                return false;
            }
        }
        return true;
    }

    private boolean checkLength(int[][] array) throws ServiceException {
        if (array.length != array[array.length - 1].length) {
            log.error("Array is not square.");
            throw new ServiceException("Array is wrong.");
        }
        return true;
    }

    public int returnThreadCount(List<String> lines) throws ServiceException {
        NumberThreadsValidator validator = new NumberThreadsValidator();
        if (lines.isEmpty()) {
            log.error("List lines from file is empty.");
            throw new ServiceException("List is empty.");
        }
        String lastLine = lines.get(lines.size() - 1);
        if (validator.isLineValid(lastLine)) {
            log.info("Count of threads from file is valid.");
            return Integer.parseInt(lastLine);
        } else {
            log.error("Wrong count of threads.");
            throw new ServiceException("Wrong count of threads.");
        }
    }
}
