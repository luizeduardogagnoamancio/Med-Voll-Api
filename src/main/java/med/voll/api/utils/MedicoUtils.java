package med.voll.api.utils;

import java.lang.reflect.Field;

public class MedicoUtils {
    public static void copiarNonNullProperties(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(source);

                if (value != null &&
                !"email".equals(field.getName()) &&
                !"crm".equals(field.getName()) &&
                !"especialidade".equals(field.getName())) {
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
