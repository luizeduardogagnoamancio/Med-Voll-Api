package med.voll.api.utils;

import java.lang.reflect.Field;
import java.util.Set;

public class Utils {
    public static void copiarNonNullProperties(Object source, Object target, Set<String> ignoredFields) {
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(source);

                if (value != null && (ignoredFields == null || !ignoredFields.contains(field.getName()))) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Erro ao copiar propriedades", e);
            }
        }
    }
}
