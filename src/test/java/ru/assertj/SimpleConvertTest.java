package ru.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three", "four");
        assertThat(list).hasSize(4)
                .contains("two")
                .contains("one", Index.atIndex(0))
                .containsExactly("one", "two", "three", "four")
                .startsWith("one", "two");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("cat", "cat", "dog", "mouse");
        assertThat(set).hasSize(3)
                .contains("cat")
                .containsExactlyInAnyOrder("dog", "cat", "mouse")
                .containsOnly("dog", "cat", "mouse", "cat");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "second")
                .containsValues(1, 2)
                .doesNotContainKey("six")
                .containsEntry("five", 4);
    }
}
