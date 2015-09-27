// StackTest.java
// Linked list implementation of Stack

class Stack {

	class Node {
		String data;
		Node next;
	}

	public Node top;

	public Stack() {
		top = null;
	}

	public void push(String string) {
		Node t = new Node();
		t.data = string;
		System.out.println("Element " + string + " is pushed to Stack !");
		t.next = top;
		top = t;
	}

	public boolean isMember(String x) {
		Node t = top;
		while (t != null) {
			if (t.data == x)
				return true;
			t = t.next;
		}
		return false;
	}

	public String pop() {
		String t = top.data;
		top = top.next;
		return t;
	}

	public boolean remove(String x) {
		Node t = top;
		while (t != null) {
			if (t.data != x)
				return true;
			t = t.next;
			top = top.next;
			System.out.println("x has been removed");
		}
		return false;
	}

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

		s.push("a");
		s.push("b");
		s.push("c");
		s.push("d");
		s.display();

		String i = s.pop();
		System.out.println("Just popped " + i);
		s.display();

		boolean a = s.isMember("g");
		// in this case x = char "g", g is not in the stack
		// so it will return false;
		System.out.println("is g in the Stack? \n" + a);
		s.display();

		boolean b = s.remove("c");
		// in this case x = char x is in the stack
		// so it will return true;
		// and remove char x
		System.out.println("remove: is x in the Stack? \n" + b);
		s.display();

		boolean c = s.isEmpty();
		// in this case x = char "g", g is not in the stack
		// so it will return false;
		System.out.println("is it Empty? \n" + c);
		s.display();
	}
}