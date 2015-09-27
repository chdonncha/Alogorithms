
// QueueTest.java
// Allocation of Queue objects in main()

class Queue {

    private class Node {
        int data;
        Node next;
    }

    Node z;
    Node head;
    Node tail;

     public Queue() {
        z = new Node(); z.next = z;
        head = z;  tail = null;
    }


  public void display() {
    System.out.println("\nThe queue values are:\n");

    Node temp = head;
    while( temp != temp.next) {
        System.out.print( temp.data + "  ");
        temp = temp.next;
    }
    System.out.println("\n");
  }


  public void enQueue( int x) {
    Node temp;

    temp = new Node();
    temp.data = x;
    temp.next = z;

    if(head == z)    // case of empty list
        head = temp;
    else                // case of list not empty
        tail.next = temp;

    tail = temp;        // new node is now at the tail
  }


  // assume the queue is non-empty when this method is called
  public int deQueue() {
	 System.out.println("Last Item");
	 Node temp = tail;
	 while(temp.next != z){
		 temp = temp.next;
		 System.out.println(("t: "+ temp.data));
	 }
	 System.out.println("temp val: "  + temp.data);
	 return temp.data;
  }


  public boolean isEmpty() {
      return z == null;
  }

	public boolean isMember(int x) {
		Node temp = head;
		while (temp != z) {
			if (temp.data == x)
				return true;
			temp = temp.next;
		}
		return false;
	}
  
} // end of Queue class



class QueueTest {
  
  // try out the ADT Queue using static allocation
  public static void main(String[] arg) {

    Queue q = new Queue();

    System.out.println("Inserting ints from 9 to 1 into queue gives:\n");
    for (int i = 9; i > 0; --i) {
       q.enQueue( i);
    }

    q.display();
    
    System.out.println(q.deQueue());

    if( ! q.isEmpty())
    System.out.println("Deleting value from queue " + q.deQueue() + "\n");

    System.out.println("Adding value to queue " + 27 + "\n");
    q.enQueue(27);
    q.display();
    
    int x = 500;
	boolean j = q.isMember(x);
	System.out.println("is" + x + "is 10 in the Stack \n" + j);
	q.display();
  }

} //end of Test class

