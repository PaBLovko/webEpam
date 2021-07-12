//package by.traning.task05.service.repository;
//
//import by.traning.task05.bean.Quadrilateral;
//import lombok.NonNull;
//
//import java.util.function.BiPredicate;
//import java.util.function.Predicate;
//
//public enum SpecificationPredicate implements BiPredicate<Quadrilateral, Quadrilateral> {
//    COORDINATE_X {
//        @Override
//        public boolean test(@NonNull Quadrilateral quadrilateral, @NonNull Quadrilateral quadrilateral2) {
//            return false;
//        }
//    },
//    COORDINATE_Y {
//        @Override
//        public boolean test(@NonNull Quadrilateral quadrilateral, @NonNull Quadrilateral quadrilateral2) {
//            return false;
//        }
//    },
//    ID {
//        @Override
//        public boolean test(@NonNull Quadrilateral quadrilateral, @NonNull Quadrilateral quadrilateral2) {
//            return quadrilateral.equals(quadrilateral2.getQuadrilateralId());
//        }
//    },
//    POINT {
//        @Override
//        public boolean test(@NonNull Quadrilateral quadrilateral, @NonNull Quadrilateral quadrilateral2) {
//            return false;
//        }
//    };
//}
