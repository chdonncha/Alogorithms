class Stack<T> { // class of type T

	private class Node {
		T data; // object of type T
		Node next;
	}

	Node z;
	Node head;
	Node tail;
	
	public void push(T x) {
		node t = new Node();
		t.data = x;
		t.next = top;
		top = t;
	}
	
	/*public int t pop() {
		T y = top.data;
		top = top.next;
		return y;
		}*/
	
	public bool isEmpty()
	{
		return top == null;
	}
	
	
	public void display()
	{
		Node t = top;
		System.out.println("\nStack contents are: ");
		
		while (t != null)
		{
			System.out.println("\n");
		}
	}
	
	public class StackGeneric
	{
		public static void main(string[] arg){
			
			Stack s = new Stack();
			//Console.Write("Stack is created\n");
			System.out.println("Stack is created\n");
			
			s.push(10); s.push(3); s.push(11); s.push(7);
			s.display();
			
			
			/* int i = s.pop();
        System.out.println("Just popped " + i);
        s.display();
			 */
		}
	}
}
