/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author bllv1
 */
public interface List<E> {
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
