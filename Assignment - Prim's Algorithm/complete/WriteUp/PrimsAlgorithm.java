//Prim conversion// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs

import java.io.*;
//import static java.lang.Integer.MAX_VALUE;
//import static java.lang.Integer.MIN_VALUE;
import java.util.Scanner;
class Heap {
	public int[] h; // heap array
	public int[] hPos; // hPos[h[k]] == k
	public int[] dist; // dist[v] = priority of v

	private int N; // heap size

	// The heap constructor gets passed from the Graph:
	//    1. maximum heap size
	//    2. reference to the dist[] array
	//    3. reference to the hPos[] array
	public Heap(int maxSize, int[] dist, int[] hPos) {
		N = 0;
		h = new int[maxSize + 1];
		this.dist = dist;
		this.hPos = hPos;
	}

	public void insert(int x) {
		h[++N] = x;
		siftUp(N);
	}

	public boolean isEmpty() {
		return (N == 0);
	}


	public void siftUp(int k) {
		int v = h[k];
		h[0] = 0;
		dist[0] = 0;

		while (dist[v] < dist[h[k / 2]]) {
			h[k] = h[k / 2];
			hPos[h[k]] = k;
			k = k / 2;
		}
		h[k] = v;
	}


	public void siftDown(int k) {

		int v;
		v = h[k];
		h[0] = Integer.MAX_VALUE;

		while (k <= N / 2) {
			int j = 2 * k;
			if (j < N && dist[h[j]] > dist[h[j + 1]]) {
				j++;
			}
			if (dist[v] <= dist[h[j]]) {
				break;
			}
			h[k] = h[j];
			hPos[h[k]] = k;
			k = j;
		}
		h[k] = v;
		hPos[v] = k;

	}

	public int remove() {
		int v = h[1];

		h[1] = h[N--];
		siftDown(1);

		return v;
	}
}

class Graph {
	class Node {
		public int vert; // vertex variable
		public int wgt; // weight variable
		public Node next;

		Node(int vert, int wgt, Node n) {
			this.vert = vert;
			this.wgt = wgt;
			next = n;
		}
		Node() {}
	}

	private int V, E;
	private Node[] adj;
	private Node z;
	private int[] mst;

	private int count = 0;
	private int last = Integer.MIN_VALUE;

	private int[] visited;
	private int id;

	public int getCount() {
		return (count);
	}
	public int getLast() {
		return (last);
	}
	public Graph(String graphFile) throws IOException {
		int u, v;
		int e, wgt;

		Node t;

		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);

		String splits = " +";
		String line = reader.readLine();
		String[] parts = line.split(splits);
		System.out.println("Parts[] = " + parts[0] + " " + parts[1]);

		V = Integer.parseInt(parts[0]);
		E = Integer.parseInt(parts[1]);

		z = new Node();
		z.next = z;

		adj = new Node[V + 1];
		for (v = 1; v <= V; ++v)
		adj[v] = z;


		System.out.println("Reading edges from text file");
		for (e = 1; e <= E; ++e) {
			line = reader.readLine();
			parts = line.split(splits);
			u = Integer.parseInt(parts[0]);
			v = Integer.parseInt(parts[1]);
			wgt = Integer.parseInt(parts[2]);

			System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));

			if (u > last) last = u;
			if (v > last) last = v;

			adj[v] = new Node(u, wgt, adj[v]);
			adj[u] = new Node(v, wgt, adj[u]);

			count++;
		}
	}

	private char toChar(int u) {
		return (char)(u + 64);
	}

	public void display() {
	// display traverse route
		int v; 
		Node n;

		for (v = 1; v <= V; ++v) {
			System.out.print("\nadj[" + toChar(v) + "] ->");
			for (n = adj[v]; n != z; n = n.next)
			System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");
		}
		System.out.println("");
	}

	public void MST_Prim(int s, int count) {
	// calculate prims algorithm
		int v, u;
		int wgt, wgt_sum = 0;
		int[] dist = new int[count];
		int[] parent = new int[count];
		int[] hPos = new int[count];
		Node t;

		for (v = 0; v <= V; v++) {
			dist[v] = Integer.MAX_VALUE;
			parent[v] = 0;
			hPos[v] = 0;
		}

		Heap h = new Heap(V, hPos, dist);
		h.insert(s);

		dist[s] = 0;

		Heap pq = new Heap(V, dist, hPos);
		pq.insert(s);

		while (!h.isEmpty()) {
			v = h.remove();
			dist[v] = -dist[v];
			Node n;
			int w;

			for (n = adj[v]; n != z; n = n.next) {

				u = n.vert;
				w = n.wgt;

				if (w < dist[u]) {
					if (dist[u] != Integer.MAX_VALUE) {
						wgt_sum -= dist[u];
					}

					dist[u] = w;
					parent[u] = v;
					wgt_sum += w;

					if (hPos[u] == 0) {

						h.insert(u);
					} else {

						h.siftUp(hPos[u]);
					}
				}
			}
			// used to print the parent[] and dist[] arrays to see how it traverses through the graph

			/*
			System.out.println("\n");
				 for(int i = 1; i <= V; ++i)  {
			System.out.println("");
			 System.out.println( toChar(i) + " -> " + toChar(parent[i]));
			}
			System.out.println("\n");
			for(int b = 1; b <= V; ++b)  {
			System.out.println("");
			System.out.println(b + " -> " + dist[b]);
			}	*/
		}
		System.out.print("\n\nTotal weight of MST ------------->\n\n =" + wgt_sum + "\n");
		mst = parent;
	}

	public void displayMST() {
	// display the MST / weight after the traverse is complete
		System.out.println("\n");
		System.out.println("Minimum Spanning tree parent array is ------------->\n");
		for (int v = 1; v <= V; ++v) {
			System.out.println("");

			System.out.println(toChar(mst[v]) + " -> " + toChar(v)); // reversed from the skeleton code

		}

	}

}

public class PrimsAlgorithm {
	public static void main(String[] args) throws IOException {
	// error handling
		System.out.println(" ");
		String fname = new String(getUserInput("Enter .txt file name to read in: "));
		System.out.println(" ");
		boolean fileError = true;
		Graph g = null;
		while (fileError) {
			String newfName = new String();
			try {
				g = new Graph(fname);
				fileError = !fileError; // wont run if an fileError is caught
			} catch (IOException e) { // to catch if the file is incorrect named

				try {
					newfName = getUserInput("File not found, enter a valid file name: ");
				} catch (IOException f) {
					System.out.println("Invalid input");

				}

				fname = newfName; //reassign the string fname to this one.
			}
		}
		if (g == null) {
			g = new Graph("wgraph2.txt"); //this will never happen anyway so.
		}

		fileError = true;
		int getNum = g.getLast();
		while (fileError) {
			getNum = Integer.MIN_VALUE;
			try {
				System.out.println(" ");
				getNum = Math.abs(Integer.parseInt(getUserInput("Enter what vertex to start at (use numbers ie B = 2) : ")));
				fileError = false;
			} catch (IOException f) {
				System.out.println("Invalid input, must use numeric values");

			}
			if (fileError == false && getNum > g.getLast()) {
				System.out.println("Number to high, must be below " + (g.getLast() + 1));
				fileError = true;
			}
		}
		// updaters
		g.display();
		g.MST_Prim(getNum, g.getCount());
		g.displayMST();
	}

	// get user input
	public static String getUserInput(String q) throws IOException {
		Console console = System.console();
		String input = console.readLine(q);
		return input;
	}
}
