/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

import Interface.List;
import Iterator.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author bllv1
 */
public class CircleDoubleLinkedList<E> implements List<E> {
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
        return last == null;
    }

    @Override
    public boolean addFirst(E element) {
        Node<E> node= new Node<>(element);
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
        Node<E> node = new Node<>(element);
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
            last.getPrevious().setNext(last);
            last.getNext().setPrevious(last);
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
        if(isEmpty() || index <0 || index>effective-1){
            return tempElement;
        }else if(index == 0){
            tempElement=last.getNext().getContent();
            removeFirst();
            
        }else if(index == effective-1){
            tempElement=last.getContent();
            removeLast();
        }else{
            Node<E> j=last.getNext();
            for(int i=0; i<effective;i++){
                if(i==index){
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
        if(isEmpty()|| element==null){
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
        if(isEmpty()||element==null){
            return false;
        }else if(last.getNext().getContent().equals(element)||last.getContent().equals(element)){
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
        if(element == null || isEmpty() || index<0){
            return false;
        }
        Node<E> node = new Node<>(element);
        Node <E> j=last.getNext();
        for(int i=0; i<effective; i++){
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
        if(isEmpty()|| index<0||index>effective-1){
            return null;
        }
        Node<E> j=last.getNext();
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
        if(isEmpty()|| index<0||index>=effective||element==null){
            return null;
        }else{
            Node<E> j=last.getNext();
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

    public Node<E> getNode(int index){
        Node<E> nodoE=null;
        if(!isEmpty()){
            if(index==0){
                nodoE=last.getNext();
            }else{
                if(index>0 && index < effective){
                    Node<E> aux =last.getNext();
                    for(int i=0; i<effective-1 ;i++){
                        if(i==index-1){
                            Node<E> m=aux.getNext();
                            nodoE=m;
                        }else{
                            aux=aux.getNext();
                        }
                    }
                }
            }
        }
        return nodoE;
    }
    @Override
    public int indexOf(E element) {
        if(isEmpty()||element==null){
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
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node<E> j=last.getNext();
        for(int i=0; i<effective;i++){
            if(j==last){
                s.append(last.getContent());
            }else{
                s.append(j.getContent()+",");
            }
            j=j.getNext();
        }
        s.append("]");
        return s.toString();
    }
    
    private Node<E> nodeIndex(int index){
        if(index==0){
            return last.getNext();
        }else if(index==effective-1){
            return last;
        }
        Node<E> p=last.getNext();
        for(int i=0; i<effective;i++){
            if(i==index){
                return p;
            }
            p=p.getNext();
        }
        return null;
    }
    
    public ListIterator<E> listIteratorNode(int index){
        ListIterator<E> it= new ListIterator(){
            Node<E> nv= getNode(index);
            int punt=effective;
            @Override
            public boolean hasNext() {
                boolean v=false;
                if(punt !=0 && nv != null){
                    v=true;
                }
                return v;
            }

            @Override
            public Object next() {
                if(!hasNext()){
                    throw new NoSuchElementException();
                }else{
                    E element=nv.getContent();
                    nv=nv.getNext();
                    return element;
                }
            }

            @Override
            public boolean hasPrevious() {
                boolean v=false;
                if(punt !=0 && nv != null){
                    v=true;
                }
                return v;
            }

            @Override
            public Object previous() {
                E element = nv.getContent();
                nv=nv.getPrevious();
                return element;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(Object e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(Object e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            
        
        };
        return it;
    }
    
    public ListIterator<E> lisIterator(){
        ListIterator<E> it=new ListIterator(){
            Node<E> nv=last.getNext();
            int punt=effective;
            @Override
            public boolean hasNext() {
                boolean v=false;
                if(punt!=0 && nv !=null){
                    v=true;
                }else{
                    punt=effective;
                }
                return v;
            }

            @Override
            public Object next() {
                if(!hasNext()){
                    throw new NoSuchElementException();
                }else{
                    E element=nv.getContent();
                    nv=nv.getNext();
                    punt--;
                    return element;
                }
            }

            @Override
            public boolean hasPrevious() {
                boolean v=false;
                if(punt !=0 && nv!=null){
                    v=true;
                }else{
                    punt=effective;
                }
                return v;
            }

            @Override
            public Object previous() {
                nv=nv.getPrevious();
                E element=nv.getContent();
                punt--;
                return element;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(Object e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(Object e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        return it;
    }
    @Override
    public Iterator<E> iterator() {
        return new miIterator();
    }
    
    public class miIterator<E> implements Iterator<E>{
        Node <E> nv=getFirstNode();
        @Override
        public boolean hasNext() {
            return nv!=null;
        }

        @Override
        public E next() {
            E element = null;
            element=nv.getContent();
            nv=nv.getNext();
            return element;
        }
    
    }
    
}
