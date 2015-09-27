// CirQueue.java
// Circular queue implementation of a ADT Queue


class CirQueue {
  
    private int q[], back, front;
    private int qmax, size;

  CirQueue( int _qmax) {
    qmax = _qmax;
    size = front = back = 0;
    q = new int[qmax];
  }


  void enQueue( int x) throws Exception
  {
    
    if( qmax == size) throw new Exception("Stack array full\n");

    q[back] = x;
    back = (back + 1) % qmax;
    ++size;
  }


  // implement this method
  int deQueue()
  {
      int x = q[front];
      front = (front + 1) % qmax;
      --size;

	  return x;
  }


  /* implement this method
  boolean isEmpty() {

  }
  */


  void display()
  {
    int i, n;
    System.out.println("\nQueue is: ");
    for(i=front, n=1; n<=size; ++n) {
        System.out.print( q[i] + "  ");
        i = (i + 1) % qmax;
    }
    System.out.println("\n");
  }



  public static void main( String arg[] )
  {
    CirQueue q = new CirQueue(5);

    int i, j;
    for(i=1; i<=6; ++i)
        try {
          q.enQueue(i); 
        }
        catch(Exception e) { 
            System.out.println("\nException thrown: " + e);
        }

    q.display();

    i = q.deQueue();
    j = q.deQueue();
    
    try { q.enQueue(11);  } 
        catch(Exception e) { System.out.println("\nException thrown: " + e); }


	System.out.println("\nJust deQueued " + i + " and " + j
	        + " and enQueued " + 11);

    q.display();
  }

} // end of class
