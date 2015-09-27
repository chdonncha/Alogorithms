// Simple weighted graph representation 
// Uses an Adjacency Matrix, suitable for dense graphs
// Converted from C# some of which has been commented out

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

class GraphMatrix 
{
    // V = number of vertices
    // E = number of edges
    // adj[ ][ ] is the adjacency matrix
    private int vert, edge;
    private int[][] adj;
    
    // used for traversing graph
    private int[] visited;
    private int id;
   
    
    
    // default constructor
    public GraphMatrix(String graphFile)  throws IOException
    {
        int u, v;
        int e, wgt;

        // StreamReader reader = new StreamReader(graphFile);
		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	   
         //char[] splits = new char[] { ' ', ',', '\t'};     
         //string line = reader.ReadLine();
		 //string[] parts = line.Split(splits, StringSplitOptions.RemoveEmptyEntries);
        
        String splits = " +";  // multiple whitespace as delimiter		   
		String line = reader.readLine();       
        String[] parts = line.split(splits);
		System.out.println(" ");
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
		
        // find out number of vertices and edges
        //V = int.Parse(parts[0]);
        //E = int.Parse(parts[1]);
        
		vert = Integer.parseInt(parts[0]);
        edge = Integer.parseInt(parts[1]);

        // create adjacency matrix, initialised to 0's
        //adj = new int[V+1, V+1];
        adj = new int[vert+1][vert+1];        
        
        visited = new int[vert+1];
        
       // read the edges
        System.out.println("Reading edges from text file");
		System.out.println(" ");
        for(e = 1; e <= edge; ++e)
       {
            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);
			
           System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));    
            // write code to put edge into adjacency matrix     
            // missing code here  
				adj[u][v] = wgt;
				adj[v][u] = wgt;
       }	       
    }
	
	// convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
	
public void display() {
        int u,v;
       
        for(v=1; v <= vert; ++v){
            System.out.print("\nadj[" + v + "] = ");
            for(u=1; u<=vert; ++u)
                System.out.print("  " + adj[u][v]);
        }    
       
    }

   // method to initialise Depth First Traversal of Graph
    public void DF(int s)
    {
        int id = 0;
        visitedReset();//to set all visited values to zero to show that they were NOT Visited Yet
        for(int i=1; i < vert; i++)  {
            visited[i] = 0;
        }
        dfVisit(0, s);//-1 to pretend to be NULL
    }
	
	public void BF(int s)
    {
		Queue q = new Queue();
		
		id = 0;
		for(int v = 0; v < vert; v++)
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
					for(int u=1; u<adj[v].length; u++) 
					{ 
						if(visited[u] == 0) 
						{ 
						q.enQueue(u);
						}						
					}			
				}	
			}
		}
	
    // Recursive Depth First Traversal for adjacency matrix
    private void dfVisit(int prev, int v)  {
           
        visited[v] = ++id;
	//	System.out.print(" " + toChar(v));
			System.out.println("Visited vertex | "+toChar(v)+" | though edge  | " + prev + " | "+ toChar(v) + " | ");
       // System.out.println("Visited vertex '" + v + "' along edge '" + prev + "-- '" + v +"'");
 
	for(int u=1; u<adj[v].length; u++) 
	{ 
	if(visited[u] == 0) 
	{ 
	dfVisit(adj[u][v],u); 
			}
		}
	}
	   private void visitedReset()  {
        for(int i : visited)  {
            visited[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException
    {
        int s = 4;
        String fname = "wGraph3.txt";               

        GraphMatrix g = new GraphMatrix(fname);
       
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

