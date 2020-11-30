
package Iterable;

import Iterator.Iterator;

/**
 *
 * @author bllv1
 */

//Interface Iterable con parametrizacion por tipo.
public interface Iterable<E> {
    Iterator<E> iterator();
}
