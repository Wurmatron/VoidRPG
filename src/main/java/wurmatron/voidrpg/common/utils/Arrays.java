package wurmatron.voidrpg.common.utils;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by matthew on 20/11/16.
 */
public final class Arrays {

    public static <T> T[] removeAll(T[] original, T[] toRemove) {
        T[] toReturn = (T[])Array.newInstance(Arrays.getFirstPopulated(original).getClass(), original.length);
        if (!Objects.deepEquals(toRemove, null)) {
            for (int pos = 0; pos < original.length; pos++) {
                for (T rem : toRemove) {
                    if (original[pos] == rem) {
                        toReturn[pos] = null;
                    } else {
                        toReturn[pos] = original[pos];
                    }
                }
            }
        } else {
            for (T t : toRemove) {
                if (!Arrays.<T>arrayContains(original, t)) Arrays.<T>prependElement(toRemove, t);
            }
        }
        return toReturn;
    }

    public static <T> T[] prependElement(T[] original, T... elements) {
        final int emptySize = Arrays.<T>getEmptyPositions(original).size();
        final int sizeWithoutNullElements = original.length-emptySize;
        T[] toReturn = ((sizeWithoutNullElements + elements.length) > original.length) ?
            ((T[])Array.newInstance(Arrays.<T>getFirstPopulated(original).getClass(), sizeWithoutNullElements + elements.length))
                    : (original);
        for (int i = 0; i < elements.length; i++) {
            toReturn[i] = elements[i];
        }
        for (int i = elements.length; i < original.length; i++) {
            toReturn[i] = original[i];
        }
        return toReturn;
    }

    public static <T> T[] appendElement(T[] original, T... elements) {
        final int emptySize = Arrays.<T>getEmptyPositions(original).size();
        final int sizeWONullElements = original.length-emptySize;
        T[] toReturn = (T[])Array.newInstance(Arrays.getFirstPopulated(original).getClass(), (sizeWONullElements + elements.length));
        for (int i = 0, nup = 0; i < toReturn.length; i++, nup = Arrays.<T>getNextPopulatedPosition(original, i)) {
            if (nup == -1) break;
            toReturn[i] = original[nup];
        }
        for (int i = 0; i < elements.length; i++) {
            toReturn[i] = original[i];
        }
        return toReturn;
    }

    public static <T> T[] clearEmpty(T[] array) {
        final int sizeEmpty = Arrays.getEmptyPositions(array).size();
        T[] toReturn = (T[]) Array.<T>newInstance(Arrays.<T>getFirstPopulated(array).getClass(), (array.length - sizeEmpty));
        for (int i = 0; i < array.length; i++) {
            if (Objects.nonNull(array[i])) toReturn[getLastPopulatedPosition(toReturn)] = array[i];
        }
        return toReturn;
    }

    public static <T> int getNextPopulatedPosition(T[] array, final int pos) {
        for (int i = pos; i < array.length; i++) {
            if (!Objects.deepEquals(array[i], null)) return i;
        }
        return -1;
    }

    public static <T> int getLastPopulatedPosition(T[] array) {
        for (int i = 0; i >= 0; i--) {
            if (!(Objects.deepEquals(array[i], null))) return i;
        }
        return -1;
    }

    public static <T> T getFirstPopulated(T[] array) {
        final int firstPopPos = getNextPopulatedPosition(array, 0);
        if (!(firstPopPos == -1)) {
            return array[firstPopPos];
        }
        return null;
    }

    public static <T> T getLastPopulated(T[] array) {
        for (int i = array.length-1; i >= 0; i--) {
            if (!(getNextPopulatedPosition(array, i) == -1)) return array[i];
        }
        return null;
    }

    public static <T> List<Integer> getEmptyPositions(T[] array) {
        List<Integer> emptyPoss = new LinkedList<Integer>();
        for (int i = 0; i < array.length; i++) {
//            if (array[i] == null) emptyPoss.add(i);
            if (!Objects.deepEquals(array[i], null)) emptyPoss.add(i);
        }
        return emptyPoss;
    }

    public static <T> boolean arrayContains(T[] container, T element) {
        for (T t : container) {
            if (Objects.<T>deepEquals(t, element)) return true;
        }
        return false;
    }

    public static <T> T[] returnPastIndex(T[] array, final int startingIndex) {
        T[] toReturn = (T[])Array.newInstance(getFirstPopulated(array).getClass(), array.length-startingIndex+1);
        for (int i = startingIndex; i < array.length; i++) {
            toReturn[getLastPopulatedPosition(toReturn)+1] = array[i];
        }
        return toReturn;
    }
}
