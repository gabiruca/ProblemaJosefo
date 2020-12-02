/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterator;

/**
 *
 * @author bllv1
 * @param <E>
 */

//Interface Iterator con parametrizacion por tipo.
public interface Iterator<E> {
    boolean hasNext();
    E next();
}
