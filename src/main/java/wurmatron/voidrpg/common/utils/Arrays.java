package wurmatron.voidrpg.common.utils;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by matthew on 20/11/16.
 */
public final class Arrays {

    public static <T> T[] removeAll(T[] original, T[] toRemove) {
        T[] toReturn = (T[])Array.newInstance(original[0].getClass(), original.length);
        for (int pos = 0; pos < original.length; pos++) {
            for (T rem : toRemove) {
                if (original[pos] == rem) {
                    toReturn[pos] = null;
                } else {
                    toReturn[pos] = original[pos];
                }
            }
        }
        return toReturn;
    }

    public static <T> T[] pushBackElements(T[] original, T... elements) {
        return null;
    }

    public static <T> T[] clearEmpty() {
        return null;
    }

    public static <T> List<Integer> getEmpty(T[] array) {
        List<Integer> emptyPoss = new LinkedList<Integer>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) emptyPoss.add(i);
        }
        return emptyPoss;
    }
}
