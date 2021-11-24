package com.qijianguo.micro.services.base.libs.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author qijianguo
 */
public class MapUtils {
    public MapUtils() {
    }

    public static Map<String, String> order(Map<String, String> map) {
        HashMap<String, String> tempMap = new LinkedHashMap();
        List<Entry<String, String>> infoIds = new ArrayList(map.entrySet());
        Collections.sort(infoIds, Comparator.comparing(Entry::getKey));
        for(int i = 0; i < infoIds.size(); ++i) {
            Entry<String, String> item = infoIds.get(i);
            tempMap.put(item.getKey(), item.getValue());
        }

        return tempMap;
    }

    public static Map<String, String> objectToMap(Object object, String... ignore) {
        return objectToMapIgnoreNull(object, true, ignore);
    }

    public static Map<String, String> objectToMapIgnoreNull(Object object, boolean ignoreNull, String... ignore) {
        Map<String, String> tempMap = new LinkedHashMap();
        Iterator var3 = getAllFields(object.getClass()).iterator();
        while(var3.hasNext()) {
            Field f = (Field)var3.next();
            if (!f.isAccessible()) {
                f.setAccessible(true);
            }
            boolean ig = false;
            if (ignore != null && ignore.length > 0) {
                String[] var6 = ignore;
                int var7 = ignore.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    String i = var6[var8];
                    if (i.equals(f.getName())) {
                        ig = true;
                        break;
                    }
                }
            }

            if (!ig) {
                Object o = null;

                try {
                    o = f.get(object);
                    if (ignoreNull && o == null) {
                        continue;
                    }
                } catch (IllegalArgumentException | IllegalAccessException var10) {
                    var10.printStackTrace();
                }
                tempMap.put(f.getName(), o == null ? "" : o.toString());
            }
        }
        return tempMap;
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if (!clazz.equals(Object.class)) {
            List<Field> fields = new ArrayList(Arrays.asList(clazz.getDeclaredFields()));
            List<Field> fields2 = getAllFields(clazz.getSuperclass());
            if (fields2 != null) {
                fields.addAll(fields2);
            }

            return fields;
        } else {
            return null;
        }
    }

    public static String mapJoin(Map<String, String> map, boolean keyLower, boolean valueUrlencode) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator var4 = map.keySet().iterator();

        while(true) {
            String key;
            do {
                do {
                    if (!var4.hasNext()) {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        }

                        return stringBuilder.toString();
                    }

                    key = (String)var4.next();
                } while(map.get(key) == null);
            } while("".equals(map.get(key)));

            try {
                String temp = key.endsWith("_") && key.length() > 1 ? key.substring(0, key.length() - 1) : key;
                stringBuilder.append(keyLower ? temp.toLowerCase() : temp).append("=").append(valueUrlencode ? URLEncoder.encode((String)map.get(key), "utf-8").replace("+", "%20") : (String)map.get(key)).append("&");
            } catch (UnsupportedEncodingException var7) {
                var7.printStackTrace();
            }
        }
    }

    /**
     * Map转Object
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, String> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }
        Object obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            if (map.containsKey(field.getName())) {
                String s = map.get(obj);
                field.set(obj, map.get(field.getName()));
            }
        }
        return obj;
    }



}
