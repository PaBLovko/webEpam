package by.traning.task06.option1.bean;

import by.traning.task06.option1.dao.DataFileReader;
import by.traning.task06.option1.service.validator.MatrixValidator;
import by.traning.task06.option1.service.exception.ServiceException;
import by.traning.task06.option1.dao.DAOException;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

@NoArgsConstructor
public class Matrix {
    private static Logger LOGGER = LogManager.getLogger(Matrix.class);
    private MatrixCell[][] data;
    private static Matrix instance;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static Matrix getInstance(){
        if (!isCreated.get()) {
            reentrantLock.lock();
            try {
                if (instance == null) {
                    instance = initializeMatrix();
                    isCreated.set(true);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
        return instance;
    }

    public Optional<MatrixCell> getUnchangedDiagonalElement() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < data.length; i++) {
                if (!data[i][i].isChanged()) {
                    data[i][i].setChanged(true);
                    LOGGER.info("Cell at [" + i + "]" + "[" + i + "] was changed.");
                    return Optional.of(data[i][i]);
                }
            }
            return Optional.empty();
        } finally {
            reentrantLock.unlock();
        }
    }

    private static Matrix initializeMatrix() throws ServiceException {
        MatrixParser matrixParser = new MatrixParser();
        DataFileReader dataFileReader = new DataFileReader();
        MatrixValidator matrixValidator = new MatrixValidator();
        String matrixDataFilepath = "data/matrix1.txt";
        List<String> matrixDigitsList = new ArrayList<>();
        try {
            matrixDigitsList = dataFileReader.read(matrixDataFilepath);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (!matrixValidator.validate(matrixDigitsList)) {
            LOGGER.fatal("Invalid matrix data at file " + matrixDataFilepath);
            throw new ServiceException();
        }
        Matrix matrix = new Matrix();
        matrix.data = matrixParser.parseMatrix(matrixDigitsList);
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MatrixCell[] datum : data) {
            for (int j = 0; j < data[0].length; j++) {
                stringBuilder.append(datum[j].getCellValue()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
