// Sorted linked list with a sentinel node
// Skeleton code
using System;

class SortedLL
{
    // internal data structure and
    // constructor code to be added here
    
    // this is the crucial method
    public void insert(int x)
    {
        Node prev, curr, t;
               
        // write code to insert x into sorted list
             
    }    

    public void display()
    {
        Node t = head;
        Console.Write("\nHead -> ");
        while( t != z) {
            Console.Write("{0} -> ", t.data);
            t = t.next;
        }
        Console.Write("Z\n");
    }
    
    public static void Main()
    {
        SortedLL list = new SortedLL();
        list.display();
        
        int i, x;
        Random r = new Random();
        
        for(i=0; i<10; ++i) { // fill the array with random integers
            x = r.Next(20);
            list.insert(x);
            Console.Write("\nInserting {0}", x);
            list.display();            
        }
        Console.ReadKey();
    }
	
	public T dequeue() {

        if (isEmpty()) {
            return (null);
        }

        else {
            T item = this.base.data;

            if (this.base.next == this.end) {
                this.base = null;
                this.end = null;
            }

            else {
                this.base = this.base.next;
            }

            return (item);
        }

    }
	
	void enQueue(int x)
	{
	
	}
	
	 public boolean isEmpty() {

        return (base == null);

    }
}