/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import Interfaces.List;
import Iterator.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;


/**
 *
 * @author bllv1
 */
public class CircleDoubleLinkedList <E> implements List<E>{
    private Node<E> last;
    private int effective;

    public CircleDoubleLinkedList() {
        last=null;
        effective=0;
    }
    
    
    
    @Override
    public int size() {
        return effective;
    }

    @Override
    public boolean isEmpty() {
        return last==null;
    }

    @Override
    public boolean addFirst(E element) {
        Node <E> node=new Node<>(element);
        if(element==null){
            return false;
        }else if(isEmpty()){
            last=node;
            last.setNext(last);
            last.setPrevious(last);
        }else{
            last.getNext().setPrevious(node);
            node.setNext(last.getNext());
            last.setNext(node);
        }
        effective++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        Node<E> node= new Node<>(element);
        if(element==null){
            return false;
        }else if(isEmpty()){
            addFirst(element);
        }else{
            last.getNext().setPrevious(node);
            node.setNext(last.getNext());
            last.setNext(node);
            node.setPrevious(last);
            last=node;
            
        }
        effective++;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()){
            return false;
        }else if(last.getNext()==last){
            last.setContent(null);
            last=null;
        }else{
            last.getNext().setContent(null);
            last.getNext().setPrevious(null);
            last.setNext(last.getNext().getNext());
            last.getNext().getPrevious().setNext(null);
            last.getNext().setPrevious(last);
        }
        effective--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()){
            return false;
        }else if(last.getNext()==last){
            last.setContent(null);
            last=null;
        }else{
            Node<E> temp=last;
            last.getPrevious().setNext(last.getNext());
            last.getNext().setPrevious(last.getPrevious());
            last=last.getPrevious();
            temp.setPrevious(null);
            temp.setNext(null);
            temp.setContent(null);
        }
        effective--;
        return true;
    }

    @Override
    public E remove(int index) {
        E tempElement=null;
        if(isEmpty()|| index<0 || index>effective-1){
            return tempElement;
        }else if(index == 0){
            tempElement=last.getNext().getContent();
            removeFirst();
        }else if(index == effective-1){
            tempElement=last.getContent();
            removeLast();
        }else{
            Node<E> j=last.getNext();
            for(int i=0; i<effective; i++){
                if(i == index){
                    tempElement=j.getContent();
                    j.getPrevious().setNext(j.getNext());
                    j.getNext().setPrevious(j.getPrevious());
                    j.setContent(null);
                    j.setNext(null);
                    j.setPrevious(null);
                    effective--;
                    return tempElement;
                }
                j=j.getNext();
            }
        }
        effective--;
        return tempElement;
    }

    @Override
    public boolean remove(E element) {
        if(isEmpty() || element == null || !(contains(element))){
            return false;
        }else{
            remove(indexOf(element));
            return true;
        }
    }

    @Override
    public E getFirst() {
        return last.getNext().getContent();
    }

    public Node getFirstNode(){
        return last.getNext();
    }
    
    @Override
    public E getLast() {
        return last.getContent();
    }
    
    public Node getLastNode(){
        return last;
    }

    @Override
    public boolean contains(E element) {
        if(isEmpty() || element == null){
            return false;
        }else if(last.getNext().getContent().equals(element) || last.getContent().equals(element)){
            return true;
        }else{
            Node<E> j=last.getNext();
            for(int i=0; i<effective;i++){
                if(j.getContent().equals(element)){
                    return true;
                }
                j=j.getNext();
            }
        }
        return false;
    }

    @Override
    public boolean insert(int index, E element) {
        if(element==null || isEmpty() || index<0 || index>effective-1){
            return false;
        }
        Node<E> node =new Node<>(element);
        Node<E> j=last.getNext();
        for(int i=0; i<effective;i++){
            if(i+1==index){
                node.setNext(j.getNext());
                node.setPrevious(j);
                j.setNext(node);
                node.getNext().setPrevious(node);
                effective++;
                return true;
            }
            j=j.getNext();
        }
        return false;
    }

    @Override
    public E get(int index) {
        if(isEmpty() || index<0 || index>effective-1){
            return null;
        }
        Node <E> j=last.getNext();
        for(int i=0; i<effective;i++){
            if(i==index){
                return j.getContent();
            }
            j=j.getNext();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if(isEmpty() || index<0 || index>=effective || element==null){
            return null;
        }else{
            Node<E>j=last.getNext();
            for(int i=0; i<effective;i++){
                if(i==index){
                    j.setContent(element);
                    return j.getContent();
                }
                j=j.getNext();
            }
        }
        return null;
    }

    @Override
    public int indexOf(E element) {
        if(isEmpty() || element==null){
            return -1;
        }else if(last.getNext().getContent().equals(element)){
            return 0;
        }else if(last.getContent().equals(element)){
            return effective-1;
        }else{
            Node<E> j=last.getNext();
            for(int i=0; i<effective;i++){
                if(j.getContent().equals(element)){
                    return i;
                }
                j=j.getNext();
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node<E> j=last.getNext();
        for (int i=0;i<effective;i++){   
            if (j==last){
                s.append(last.getContent());
            }
            else{
                s.append(j.getContent()+",");
            }
            j=j.getNext();
        }
        s.append("]");
        return s.toString();
    }
    
    
    
    private Node<E> nodeIndex(int index){
        if (index==0)
            return last.getNext();
        if (index==effective-1){
            return last;
        }
        Node<E> p = last.getNext();
        for (int i=0;i<effective;i++){
            if (i==index){
                return p;
            }
            p=p.getNext();
        }
        return null;
    }
    
    public ListIterator<E> listIteratorNode(int index){
        ListIterator<E> i;
        i = new ListIterator<E>() {
        private Node<E> p = nodeIndex(index);
        private int currentIndex=index;
            
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public E next() {
            E temp=p.getContent();
            p=p.getNext();
            if (currentIndex==effective-1){
                currentIndex=0;
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return temp;
        }

        @Override
        public boolean hasPrevious() {
            return true;
        }

        @Override
        public E previous() {
            E temp=p.getContent();
            p=p.getPrevious();
            if (currentIndex==0){
                currentIndex=effective-1;
            }
            if(!hasPrevious()){
                throw new NoSuchElementException();
            }
            return temp;
        }

        @Override
        public int nextIndex() {
            return currentIndex + 1;
        }

        @Override
        public int previousIndex() {
            return currentIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet 1."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("Not supported yet 2."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("Not supported yet 3."); //To change body of generated methods, choose Tools | Templates.
        }
            
        };
        return i;
    }
    
    

    @Override
    public Iterator<E> iterator() {
        return new miIterator();
    }
    
    public class miIterator<E> implements Iterator<E>{
        Node<E> nv=getFirstNode();
        @Override
        public boolean hasNext() {
            return nv!=null;
        }

        @Override
        public E next() {
            E element=null;
            element=nv.getContent();
            nv=nv.getNext();
            return element;
        }
    
    }
}
