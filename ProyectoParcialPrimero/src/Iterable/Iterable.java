/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iterable;

import Iterator.Iterator;

/**
 *
 * @author bllv1
 * @param <E>
 */

//Interface Iterable con parametrizacion por tipo.
public interface Iterable<E> {
    Iterator<E> iterator();
}
