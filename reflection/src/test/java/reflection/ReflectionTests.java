package reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ReflectionTests {

    private static final String CLASS_NAME = "reflection.Human";

    @Test
    void shouldReturnFields() {
        // when
        var fields = Reflection.getFieldsBy(CLASS_NAME)
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());

        //then
        assertThat(fields).contains("name", "weight", "age", "height");
    }

    @Test
    void shouldReturnInterfaces() {
        // when
        var annotations = Reflection.getInterfacesBy(CLASS_NAME);

        //then
        assertThat(annotations).contains(Ageable.class);
    }

    @Test
    void shouldReturnConstructors() {
        // when
        var constructors = Reflection.getConstructorsBy(CLASS_NAME);

        //then
        assertThat(constructors.size()).isEqualTo(1);
    }
}
