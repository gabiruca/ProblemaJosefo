/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Iterable.Iterable;
/**
 *
 * @author bllv1
 * @param <E>
**/
//Interface List con parametrizacion por tipo y extiende de la interface Iterable
public interface List<E> extends Iterable<E>{
    int size();
    boolean isEmpty();
    boolean addFirst(E element);
    boolean addLast (E element);
    boolean removeFirst();
    boolean removeLast();
    E remove(int index);
    boolean remove(E element);
    E getFirst();
    E getLast();
    boolean contains(E element);
    boolean insert(int index, E element);
    E get(int index);
    E set(int index, E element);
    int indexOf(E element);    
    
}
