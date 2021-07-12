package by.traning.task05.service.repository;

import by.traning.task05.bean.Quadrilateral;

import java.util.Comparator;

public enum QuadrilateralComparatorClassic implements Comparator<Quadrilateral> {

    ID {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getQuadrilateralId().hashCode() - o2.getQuadrilateralId().hashCode();
        }
    },
    POINT_A_COORDINATE_X {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointA().getCoordinateX() - o2.getPointA().getCoordinateX();
        }
    },
    POINT_A_COORDINATE_Y {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointA().getCoordinateY() - o2.getPointA().getCoordinateY();
        }
    },
    POINT_B_COORDINATE_X {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointB().getCoordinateX() - o2.getPointB().getCoordinateX();
        }
    },
    POINT_B_COORDINATE_Y {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointB().getCoordinateY() - o2.getPointB().getCoordinateY();
        }
    },
    POINT_C_COORDINATE_X {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointC().getCoordinateX() - o2.getPointC().getCoordinateX();
        }
    },
    POINT_C_COORDINATE_Y {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointC().getCoordinateY() - o2.getPointC().getCoordinateY();
        }
    },
    POINT_D_COORDINATE_X {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointD().getCoordinateX() - o2.getPointD().getCoordinateX();
        }
    },
    POINT_D_COORDINATE_Y {
        @Override
        public int compare(Quadrilateral o1, Quadrilateral o2) {
            return o1.getPointD().getCoordinateY() - o2.getPointD().getCoordinateY();
        }
    }

//    ID(Comparator.comparingInt(Quadrilateral::getQuadrilateralId));
//
//    QuadrilateralComparatorFunctional(Comparator<Quadrilateral> comparator) {
//        this.comparator = comparator;
//    }
//    private Comparator<Quadrilateral> comparator;
//    public Comparator<Quadrilateral> getComparator() {
//        return comparator;
//    }
//
//    public enum PointComparatorFunctional {
//        COORDINATE_X(Comparator.comparingInt(Quadrilateral.Point::getCoordinateX)),
//        COORDINATE_Y(Comparator.comparingInt(Quadrilateral.Point::getCoordinateY));
//
//        private Comparator<Quadrilateral.Point> comparator;
//        PointComparatorFunctional(Comparator<Quadrilateral.Point> comparator) {
//            this.comparator = comparator;
//        }
//        public Comparator<Quadrilateral.Point> getComparator() {
//            return comparator;
//        }
//    }
}