// Simple weighted graph representation 
// Uses an Adjacency Matrix, suitable for dense graphs
// Converted from C# some of which has been commented out

import java.io.*;

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
        System.out.println("");
    }

   // method to initialise Depth First Traversal of Graph
    public void DF(int s)
    {
        int id = 0;
        visitedReset();//to set all visited values to zero to show that they were NOT Visited Yet
        for(int i=1; i < vert; i++)  {
            visited[i] = 0;
        }
        dfVisit(-1, s);//-1 to pretend to be NULL
    }
 
 
    // Recursive Depth First Traversal for adjacency matrix
    private void dfVisit(int prev, int v)  {
       
        visited[v] = ++id;
        System.out.println("Visited vertex '" + v + "' along edge '" + prev + "-- '" + v +"'");
       
        for(int u = 0; u < adj[v].length; u++) {
            if(visited[u] == 0)  {
                dfVisit(v, u);
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
        
         g.DF(s);
        // g.BF
    }

}

