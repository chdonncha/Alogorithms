package Lab1;

package Lab1;

import Lab1.Stack.Node;

//StackTest.java
//Linked list implementation of Stack

class Stack {

class Node {
   int data;
   Node next;  
}
private Node top;
 
public Stack()
{ 
   top = null;
}
   
public void push(int x) {
   Node  t = new Node();
   t.data = x;
   t.next = top;
   top = t;
}

// only to be called if list is non-empty.
// Otherwise an exception should be thrown.
/* public int pop(){
   do yourself 
   
} */

/*
public boolean isEmpty(){
  do yourself
}*/


public void display() {
   Node t = top;
   //Console.Write("\nStack contents are:  ");
   System.out.println("\nStack contents are:  ");
   
   while (t != null) {
       //Console.Write("{0} ", t.data);
       System.out.print(t.data + " ");
       t = t.next;
   }
   //Console.Write("\n");
   System.out.println("\n");
}

}

