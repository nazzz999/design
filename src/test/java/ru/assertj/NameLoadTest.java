package ru.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {

    @Test
    void whenGetMapMissingThrows() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap).isInstanceOf(IllegalStateException.class)
                .hasMessageMatching("collection contains no data");
    }

    @Test
    void whenParseMissingThrows() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("Names array is empty");
    }

    @Test
    void whenNameStartsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=I nastya")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("this name: =I nastya does not contain a key");
    }

    @Test
    void whenNameNotContainsEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Nastya Nazarycheva")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("this name: Nastya Nazarycheva does not contain the symbol '='");
    }

    @Test
    void whenNameEndsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("test=")).isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("this name: test= does not contain a value");
    }
}