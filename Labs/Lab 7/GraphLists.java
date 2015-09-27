// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

import java.io.*;

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
	/*System.out.println("Last Item");
	 Node temp = tail;
	 while(temp.next != z){
		 temp = temp.next;
		 System.out.println(("t: "+ temp.data));
	 }
	 System.out.println("temp val: "  + temp.data);
	 return temp.data;*/
	 if(head == head.next)
		return -1;
	
	else {
		
		int temp = head.data;
		
		head = head.next;
		
		return temp;
		}
  }


  public boolean isEmpty() {
      return head == head.next;
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

class GraphLists {
    class Node {
        public int vert;
        public int wgt;
        public Node next;
    }
    
    // V = number of vertices
    // E = number of edges
    // adj[] is the adjacency lists array
    private int V, E;
    private Node[] adj;
    private Node z;
    
    // used for traversing graph
    private int[] visited;
    private int id;
    
    
    // default constructor
    public GraphLists(String graphFile)  throws IOException
    {
        int u, v;
        int e, wgt;
        Node t;

        FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	           
        String splits = " +";  // multiple whitespace as delimiter
		String line = reader.readLine();        
        String[] parts = line.split(splits);
		System.out.println(" ");
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
        
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        
        // create sentinel node
        z = new Node(); 
        z.next = z;
        
        // create adjacency lists, initialised to sentinel node z
        visited = new int[V+1];
        adj = new Node[V + 1];
        for(v = 1; v <= V; ++v)
		{
           adj[v] = z;               
        }
        
        // read the edges
        System.out.println("Reading edges from text file");
        
        // missing for loop here
		System.out.println(" ");
		 for(e = 1; e <= E; ++e)
       {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);
            
            System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));    
            // write code to put edge into adjacency matrix     
            // missing code here  
		
			// insert new values to linked list
			insert(u, wgt, v); 
			insert(v, wgt, u);
			
       }	   
    }
   
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
    
    // method to display the graph representation
    public void display() {
        int v;
        Node n;
        
        for(v=1; v<=V; ++v){
            System.out.print("\nadj[" + toChar(v) + "] ->" );
            for(n = adj[v]; n != z; n = n.next) 
                System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");    
        }
        System.out.println("");
    }
	
	public void insert(int i, int x, int vert)
	{
		Node node = new Node();
		node.wgt = x;
		node.vert = vert;
		node.next = adj[i];
		
		adj[i] = node;
	}


      // method to initialise Depth First Traversal of Graph
    public void DF(int s)
    {
        int id = 0;
        visitedReset();//to set all visited values to zero to show that they were NOT Visited Yet
        for(int i=1; i <= V; i++)  {
            visited[i] = 0;
        }
        dfVisit(0, s);//-1 to pretend to be NULL
    }
 
 
    // Recursive Depth First Traversal for adjacency matrix
    private void dfVisit(int prev, int cur)  {
           
       visited[cur] = ++id;
        if(prev != 0 )   {
		//System.out.print(" " + toChar(prev));
            //System.out.println("Visited vertex '" + toChar(prev) +"--" + toChar(cur));
				System.out.println("Visited vertex | "+toChar(prev)+" | though edge  | " + prev + " | "+ toChar(cur) + " | ");
        }
        //System.out.print("\nadj[" + toChar(v) + "] ->" );
        Node n;// = new Node();
        for(n = adj[cur]; n != z; n = n.next)  {
            if(visited[n.vert] == 0 )  {
                dfVisit(cur, n.vert);
            }
        }
	}
	   private void visitedReset()  {
        for(int i : visited)  {
            visited[i] = 0;
        }
    }
	
	/* private void dfVisit(int prev, int v)  {
	 
        Node curr = adj[v];
		   
        visited[v] = ++id;
		System.out.print(" " + toChar(v));
		//System.out.println("Visited vertex "+toChar(v)+" along edge " + prev + "--"+ toChar(v));
       // System.out.println("Visited vertex '" + v + "' along edge '" + prev + "-- '" + v +"'");
 
	do {
	{ 
	if(visited[u] == 0) 
		{ 
		dfVisit(v, curr.next); 
		}
	}
			while ((curr = curr.next) != z);
		}
	}*/
	
	public void BF(int s)
    {
		Queue q = new Queue();
		
		id = 0;
		for(int v = 0; v < V; v++)
		{
		visited[v] = 0;
		}
		q.enQueue(s);
			while(!q.isEmpty())
			{
			int v = q.deQueue();
		
				if(visited[v] == 0) 
				{
				visited[v] = ++id;
					System.out.print(" " + toChar(v));
					
					Node n = new Node();
				
					for(n = adj[v]; n != z; n = n.next) 
					{ 
						if(visited[n.vert] == 0 && n.wgt !=  0) 
						{ 
						q.enQueue(n.vert);
						}						
					}			
				}	
			}
		}
	
    public static void main(String[] args) throws IOException
    {
        int s = 4;
        String fname = "wGraph3.txt";               

        GraphLists g = new GraphLists(fname);
       
        g.display();
       
         System.out.println("\n");
	   	 System.out.println("DFS Traversal of a tree is ------------->\n");
		
         g.DF(s);
		 
		 System.out.println("\n");
	   	 System.out.println("BFS Traversal of a tree is ------------->\n");
		 
         g.BF(s);
		 
		 System.out.println("\n");
    }

}

