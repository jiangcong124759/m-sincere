//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.sg.zhsd.uav.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

public class C {
    public C() {
    }

    public static List<Long> listLong(String strings) {
        return listLong(strings, ",");
    }

    public static List<Long> listLong(String strings, String splitor) {
        if (strings != null && !strings.trim().isEmpty()) {
            if (splitor == null) {
                splitor = ",";
            }

            List<Long> ret = new ArrayList();
            Arrays.asList(strings.split(splitor)).stream().mapToLong((t) -> {
                return Long.valueOf(t);
            }).forEach((string) -> {
                ret.add(string);
            });
            return ret;
        } else {
            return Collections.emptyList();
        }
    }

    public static <T, E> List<T> list(Iterable<E> iterable, Function<E, T> test) {
        List<T> res = new ArrayList();
        iterable.forEach((item) -> {
            res.add(test.apply(item));
        });
        return res;
    }

    public static List<String> listString(String strings) {
        return listString(strings, ",");
    }

    public static List<String> listString(String strings, String splitor) {
        if (strings != null && !strings.trim().isEmpty()) {
            if (splitor == null) {
                splitor = ",";
            }

            List<String> ret = new ArrayList();
            Arrays.asList(strings.split(splitor)).forEach((string) -> {
                ret.add(string);
            });
            return ret;
        } else {
            return Collections.emptyList();
        }
    }

    public static <E> E fetchOne(Collection<E> targets) {
        if (targets != null && !targets.isEmpty()) {
            Iterator var1 = targets.iterator();
            if (var1.hasNext()) {
                E e = (E) var1.next();
                return e;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static <T> T copy(Object source, Class<T> target) {
        try {
            T o = target.newInstance();
            BeanUtils.copyProperties(source, o);
            return o;
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> copyList(Collection source, Class<T> target) {
        return source == null ? Collections.emptyList() : (List)source.stream().map((s) -> {
            return copy(s, target);
        }).collect(Collectors.toList());
    }

    public static boolean isArrayNotEmpty(Collection array) {
        return !isArrayEmpty(array);
    }

    public static boolean isArrayEmpty(Collection array) {
        return array == null || array.isEmpty();
    }
}
