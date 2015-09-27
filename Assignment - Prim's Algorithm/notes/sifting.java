public void siftUp( int k) 
{ 
int v = h[k];
 h[0] = Integer.MAX_VALUE;
 // code yourself
 // must use hPos[] and dist[] arrays
 while (v>h[k/2]) { 
 h[k] = h[k/2]; 
 hPos[h[k]] = k;
 k = k / 2;
 }
 h[k] = v;
 }

public void siftDown( int k) 
{ 
int v;
//v is to temp hold heap variable, j is the iterator 
//must use hPos[] and dist[]
 v = h[k]; //starting at h[k] 
 h[0] = Integer.MAX_VALUE;

while(k <= N/2) 
{
//while left child is within heap
 int j=2*k + 1;
 //left child of k since heap begins in a[0]
 if(j<N-1 && h[j] < h[j+1]) 
	 {
	 j++; 
	 }
	 if(v>= h[j])
	 {
	 break;
	 }
	 h[k] = h[j]; 
	 hPos[h[k]] = k; k=j;
	 }
	 h[k] = v; 
	 }