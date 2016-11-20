package wurmatron.voidrpg;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by matthew on 20/11/16.
 */
public class GenericWrapper<T> extends Object {

    private final List<T> obj;

    public GenericWrapper(T t, boolean mutable) {
        if (mutable) {
            obj = new LinkedList<T>() {
                {
                    add(t);
                }
            };
        } else {
            obj = new LinkedList<T>() {
                {
                    super.add(t);
                }
                @Override
                public T remove() {
                    return null;
                }

                public T add() {
                    return null;
                }
            };
        }
    }

    public void setObj(T t) {
        this.obj.clear();
        this.obj.add(t);
    }

    public T getObj() {
        return this.obj.get(0);
    }

    @Override
    public void finalize() throws Throwable {
        obj.remove(obj.get(0));
        super.finalize();
    }
}
