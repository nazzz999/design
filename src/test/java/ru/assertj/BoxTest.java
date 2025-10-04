package ru.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void whenGetAreaMissing50_26() {
        Box box = new Box(0, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(50.26d, (withPrecision(0.006d)))
                .isPositive()
                .isNotZero();
    }

    @Test
    void whenGetAreaMissingZero() {
        Box box = new Box(2, 6);
        double area = box.getArea();
        assertThat(area).isEqualTo(0.0)
                .isZero();
    }

    @Test
    void whenIsExistMissingTrue() {
        Box box = new Box(4, 1);
        boolean exist = box.isExist();
        assertThat(exist).isTrue();
    }

    @Test
    void whenIsExistMissingFalse() {
        Box box = new Box(7, 3);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void whenGetNumberOfVerticesMissing4() {
        Box box = new Box(4, 2);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isPositive()
                .isEqualTo(4)
                .isLessThan(8);
    }

    @Test
    void whenGetNumberOfVerticesMissingMinus1() {
        Box box = new Box(8, 0);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1)
                .isNegative();
    }

    @Test
    void whenWhatsThisMissingTetrahedron() {
        Box box = new Box(4, 1);
        String type = box.whatsThis();
        assertThat(type).isEqualTo("Tetrahedron")
                .isNotNull()
                .containsIgnoringCase("tetrahedron");
    }

    @Test
    void  whenWhatsThisMissingUnknownObject() {
        Box box = new Box(5, 2);
        String actual = box.whatsThis();
        assertThat(actual).isNotNull()
                .isEqualTo("Unknown object")
                .containsIgnoringCase("unknown");
    }
}
