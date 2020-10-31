package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class Reflection {

    public static List<Field> getFieldsBy(String className) {
        try {
            return List.of(Class.forName(className).getDeclaredFields());
        } catch (ClassNotFoundException e) {
            throw new ReflectionException("does not exist class name : " + className);
        }
    }

    public static List<Class<?>> getInterfacesBy(String className) {
        try {
            return List.of(Class.forName(className).getInterfaces());
        } catch (ClassNotFoundException e) {
            throw new ReflectionException("does not exist class name : " + className);
        }
    }

    public static List<Constructor<?>> getConstructorsBy(String className) {
        try {
            return List.of(Class.forName(className).getConstructors());
        } catch (ClassNotFoundException e) {
            throw new ReflectionException("does not exist class name : " + className);
        }
    }

}
