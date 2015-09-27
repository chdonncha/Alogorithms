// StackTest.java
// Linked list implementation of Stack

class Stack {

	class Node {
		int data;
		Node next;
	}

	public Node top;

	public Stack() {
		top = null;
	}

	public void push(int x) {
		Node t = new Node();
		t.data = x;
		System.out.println("Element " + x + " is pushed to Stack !");
		t.next = top;
		top = t;
	}

	public boolean isMember(int x) {
		Node t = top;
		while (t != null) {
			if (t.data == x)
				return true;
			t = t.next;
		}
		return false;
	}

	/*
	 * public int pop() { if (t == top) { t = t.next; t.next = null;
	 * System.out.println("Pop operation done !"); } else {
	 * System.out.println("Stack Underflow !"); } return top.data; }
	 */

	/*
	 * public int pop() { if(top.data >= 0) { top.data --; // t.next = null;
	 * System.out.println("Pop operation done !"); } else {
	 * System.out.println("Stack Underflow !"); } return top.data; }
	 */

	public int pop() {

		int t = top.data;
		top = top.next;
		return t;

	}

	/*
	 * public int pop(data, t) { // Node t = new Node(); if(!isEmpty()){ t =
	 * top; // save item to return // top = top.next; // delete first node top =
	 * top - 1; return t; // return the saved item }
	 */

	// only to be called if list is non-empty.
	// Otherwise an exception should be thrown.
	/*
	 * public int pop(){ do yourself
	 * 
	 * }
	 */

	/*
	 * public boolean isEmpty(){ do yourself }
	 */

	public void display() {
		Node t = top;
		// Console.Write("\nStack contents are:  ");
		System.out.println("\nStack contents are:  ");

		while (t != null) {
			// Console.Write("{0} ", t.data);
			System.out.print(t.data + " ");
			t = t.next;
		}
		// Console.Write("\n");
		System.out.println("\n");
	}

	public boolean isEmpty() {
		return top == null;
	}
}

class StackTest {
	public static void main(String[] arg) {
		Stack s = new Stack();
		// Console.Write("Stack is created\n");
		System.out.println("Stack is created\n");

		s.push(10);
		s.push(3);
		s.push(11);
		s.push(7);
		s.display();

		int i = s.pop();
		System.out.println("Just popped " + i);
		s.display();

		boolean j = s.isMember(10);
		System.out.println("is 10 in the Stack \n" + j);
		s.display();
		// System.out.println(" " + isMember);
	}
}
