package by.traning.task05.dao.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.dao.QuadrilateralDAO;
import by.traning.task05.dao.exception.DAOException;
import by.traning.task05.util.FrequentlyUsedRegex;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileQuadrilateralDAO implements QuadrilateralDAO {
    private Logger logger = LogManager.getLogger(FileQuadrilateralDAO.class);

    @Override
    public List<String> read(@NonNull String url) throws DAOException {
        logger.debug(String.format("The method is invoked, url = %s", url));
        List<String> point = new ArrayList<>();
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(url))){
            String line = bufferedReader.readLine();
            while (line != null) {
                point.add(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            logger.error("The method is exception. File not found in the specified location");
            throw new DAOException("File not found in the specified location", e);
        } catch (IOException e) {
            logger.error("The method is exception.The file did not read");
            throw new DAOException(e);
        }
        logger.info(String.format("The method worked correctly, point = %s", point));
        return point;
    }

    @Override
    public void save(@NonNull List<Quadrilateral> quadrilateralList, @NonNull String url) throws DAOException {
        logger.debug(String.format("The method is invoked, quadrilateralList = %s, url = %s", quadrilateralList, url));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url))) {
            writer.write(quadrilateralToString(quadrilateralList));
        } catch (IOException e) {
            logger.error("The method is exception. Something went wrong while writing data to the file");
            throw new DAOException("Something went wrong while writing data to the file", e);
        }
        logger.info("The method worked correctly");
    }

    /**
     * Helper method to convert quadrilateralList to a styling string in order to save it in a file
     * @param quadrilateralList to be converted to a string
     * @return string contains styling of how the list of Quadrilateral will looks like in a file
     */
    private String quadrilateralToString(@NonNull List<Quadrilateral> quadrilateralList) {
        logger.debug(String.format("The method is invoked, quadrilateralList = %s", quadrilateralList));
        StringBuilder builder = new StringBuilder();
        for (Quadrilateral q : quadrilateralList) {
            String x1 = String.valueOf(q.getPointA().getCoordinateX());
            String y1 = String.valueOf(q.getPointA().getCoordinateY());

            String x2 = String.valueOf(q.getPointB().getCoordinateX());
            String y2 = String.valueOf(q.getPointB().getCoordinateY());

            String x3 = String.valueOf(q.getPointC().getCoordinateX());
            String y3 = String.valueOf(q.getPointC().getCoordinateY());

            String x4 = String.valueOf(q.getPointD().getCoordinateX());
            String y4 = String.valueOf(q.getPointD().getCoordinateY());

            builder.append(x1).append(FrequentlyUsedRegex.COMMA_SEPARATOR);
            builder.append(y1).append(FrequentlyUsedRegex.SPACE_SEPARATOR);

            builder.append(x2).append(FrequentlyUsedRegex.COMMA_SEPARATOR);
            builder.append(y2).append(FrequentlyUsedRegex.SPACE_SEPARATOR);

            builder.append(x3).append(FrequentlyUsedRegex.COMMA_SEPARATOR);
            builder.append(y3).append(FrequentlyUsedRegex.SPACE_SEPARATOR);

            builder.append(x4).append(FrequentlyUsedRegex.COMMA_SEPARATOR);
            builder.append(y4).append(FrequentlyUsedRegex.SPACE_SEPARATOR);

            builder.append("\n");
        }
        logger.info(String.format("The method worked correctly, builder = %s", builder));
        return builder.toString();
    }
}
