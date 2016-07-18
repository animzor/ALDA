// Johnny Huhtala
// johu7086
// Animzor@gmail.com

import java.util.Iterator;

public class MyLinkedList<E> implements ALDAList<E> {

	private int size;
	private int modCount;

	// kod för nodklassen från föreläsningsbilderna
	static class Node<E> {
		E data;
		Node<E> next;

		public Node(E data) {
			this.data = data;
		}

		public E getData() {
			return this.data;
		}
	}

	private Node<E> first;
	private Node<E> last;

	// add-metoden från föreläsningsbilderna
	@Override
	public void add(E data) {
		if (first == null) {
			first = new Node<E>(data);
			last = first;
		} else {
			last.next = new Node<E>(data);
			last = last.next;
		}
		size++;
		modCount++;
	}

	@Override
	public void add(int index, E data) {

		if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index > size) {
			throw new IndexOutOfBoundsException();
		}

		else if (index == size) {
			add(data);
			return;
		} else if (index == 0) {
			Node<E> temp = first;
			first = new Node<E>(data);
			first.next = temp;
			size++;
			modCount++;
			return;
		}

		Node<E> node = first;
		for (int i = 1; i < index; i++) {
			node = node.next;
		}
		Node<E> temp = node.next;
		node.next = new Node<E>(data);
		node.next.next = temp;
		size++;
		modCount++;
	}

	@Override
	public E remove(int index) {
		if (first == null) {
			throw new IndexOutOfBoundsException();
		} else if (index < 0) {
			throw new IndexOutOfBoundsException();
		} else if (index > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		else if (index == 0) {
			Node<E> temp = first;
			first = first.next;
			size--;
			modCount++;
			return temp.data;
		}

		Node<E> removed;
		Node<E> temp = first;
		for (int i = 1; i < index; i++) {
			temp = temp.next;
		}
		removed = temp.next;
		temp.next = temp.next.next;
		if (index == size - 1) {
			last = temp;
		}
		size--;
		modCount++;
		return removed.data;
	}

	@Override
	public boolean remove(E data) {

		if (first.data == data) {
			first = first.next;
			size--;
			modCount++;
			return true;
		} else if (!this.contains(data)) {
			return false;
		}
		// for-loopen delvis från föreläsningsbilderna
		for (Node<E> temp = first; temp != null; temp = temp.next) {
			if (temp.next.data == data) {
				temp.next = temp.next.next;
				if(temp.next == null){
					last = temp;
				}
				size--;
				modCount++;
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}

		else if (first == null) {
			throw new IndexOutOfBoundsException();
		} else if (index > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		int counter = 0;
		// for-loopen delvis från föreläsningsbilderna
		for (Node<E> temp = first; temp != null; temp = temp.next) {
			if (counter == index)
				return temp.data;
			counter++;

		}

		return null;
	}

	@Override
	public boolean contains(E data) {
		// for-loopen delvis från föreläsningsbilderna
		for (Node<E> temp = first; temp != null; temp = temp.next)
			if (temp.data == data) {
				return true;
			}

		return false;
	}

	@Override
	public int indexOf(E data) {

		Node<E> temp = first;
		for (int i = 0; i < size; i++) {
			if (temp.getData().equals(data)) {
				return i;
			}
			temp = temp.next;

		}

		return -1;

	}

	@Override
	public void clear() {

		first = null;
		last = first;
		size = 0;
		modCount++;

	}

	@Override
	public int size() {
		return size;
	}

	public String toString() {
		if (first != null) {
			String output = "[";
			// for-loopen delvis från föreläsningsbilderna
			for (Node<E> temp = first; temp != null; temp = temp.next) {
				output += temp.data + "" + ", ";
			}
			if (output.endsWith(", ")) {
				output = output.substring(0, output.length() - 2);
				output += "]";
			}
			return output;

		}
		return "[]";
	}

	@Override
	public Iterator<E> iterator() {
		
		return new LinkedListIterator();
	}
	
	//Iteratorklassen delvis från s.82 i kursboken
	
	private class LinkedListIterator implements java.util.Iterator<E>
	 {
	 private Node<E> current = null;
	 private int expectedModCount = modCount;
	 private boolean okToRemove = false;
	 private Node<E> previous = null;
	
	 public boolean hasNext(){
		 return current != last;
		 }
	
	 public E next()
	 {
	 if( modCount != expectedModCount){
	 throw new java.util.ConcurrentModificationException();
	 }
	 if( !hasNext( ) ){
	 throw new java.util.NoSuchElementException();
	 }
	 
	 E nextItem;
	 
	 if(current == null){
		 current = first;
		 nextItem = current.data;
	 } else {
		 previous = current;
		 current = current.next;
		 nextItem = current.data;
	 }
	 okToRemove = true;
	 return nextItem;
	 }
	
	 public void remove()
	 {
	 if( modCount != expectedModCount)
	 throw new java.util.ConcurrentModificationException();
	 if( !okToRemove )
	 throw new IllegalStateException();
	 
	 if(current == first){
		 first = first.next;
		 size--;
		 modCount++;
		 expectedModCount++;
		 okToRemove = false;
		 return;
	 } else {
	 previous.next = current.next;
	 size--;
	 modCount++;
	 expectedModCount++;
	 okToRemove = false;
	 }
	 }
	 }
	
}
