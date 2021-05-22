package by.traning.task01.bean;

import java.util.Objects;

/**
 * This class consist exclusively of linear algorithm methods
 */
public class Cube {
    /**
     * The side of the cube
     */
    private double cubeEdgeLength;
    /**
     * The area of the side
     */
    private double areaSide;
    /**
     * The volume of this cube
     */
    private double cubeVolume;
    /**
     * The area of a total surface
     */
    private double cubeArea;

    /**
     * Allocates a new {@code Cube}
     * @param cubeEdgeLength The side of the cube
     * @param faceArea The area of the side
     * @param cubeVolume The volume of this cube
     * @param cubeArea The area of the total surface
     */
    public Cube(double cubeEdgeLength, double faceArea, double cubeVolume, double cubeArea) {
        this.cubeEdgeLength = cubeEdgeLength;
        this.areaSide = faceArea;
        this.cubeVolume = cubeVolume;
        this.cubeArea = cubeArea;
    }

    /**
     * The method for getting the side of the cube
     * @return The side of the cube
     */
    public double getCubeEdgeLength() {
        return cubeEdgeLength;
    }

    /**
     * The method for setting the side of the cube
     * @param cubeEdgeLength The side of the cube
     */
    public void setCubeEdgeLength(double cubeEdgeLength) {
        this.cubeEdgeLength = cubeEdgeLength;
    }

    /**
     * The method for getting the area of the side
     * @return The area of the side
     */
    public double getAreaSide() {
        return areaSide;
    }

    /**
     * The method for setting the area of the side
     * @param areaSide The area of the side
     */
    public void setAreaSide(double areaSide) {
        this.areaSide = areaSide;
    }

    /**
     * The method for getting the volume of this cube
     * @return The volume of this cube
     */
    public double getCubeVolume() {
        return cubeVolume;
    }

    /**
     * The method for setting the volume of this cube
     * @param cubeVolume The volume of this cube
     */
    public void setCubeVolume(double cubeVolume) {
        this.cubeVolume = cubeVolume;
    }

    /**
     * The method for getting the area of a total surface
     * @return The area of a total surface
     */
    public double getCubeArea() {
        return cubeArea;
    }

    /**
     * The method for setting the area of a total surface
     * @param cubeArea The area of a total surface
     */
    public void setCubeArea(double cubeArea) {
        this.cubeArea = cubeArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return Double.compare(cube.cubeEdgeLength, cubeEdgeLength) == 0 && Double.compare(cube.areaSide, areaSide) == 0 && Double.compare(cube.cubeVolume, cubeVolume) == 0 && Double.compare(cube.cubeArea, cubeArea) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cubeEdgeLength, areaSide, cubeVolume, cubeArea);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "cubeEdgeLength=" + cubeEdgeLength +
                ", areaSide=" + areaSide +
                ", cubeVolume=" + cubeVolume +
                ", cubeArea=" + cubeArea +
                '}';
    }
}
