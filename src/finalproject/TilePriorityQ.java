package finalproject;

import java.util.ArrayList;
import java.util.Arrays;


import finalproject.system.Tile;

public class TilePriorityQ {
	// TODO level 3: Add fields that can help you implement this data type
	ArrayList<Tile> heap = new ArrayList<>();



	// TODO level 3: implement the constructor for the priority queue
	public TilePriorityQ (ArrayList<Tile> vertices) {
		heap.add(null);
		for (Tile each: vertices){
			add(each);
		}
	}
	private void add(Tile t){
		heap.add(t);
		Tile curr=t;
		int i=heap.size()-1;
		while(!t.equals(heap.get(1)) && curr.costEstimate<heap.get(i/2).costEstimate){
			Tile temp=heap.get(i/2);
			heap.set(i/2,curr);
			heap.set(i,temp);
			i=i/2;
		}
	}

	// TODO level 3: implement remove min as seen in class
	public Tile removeMin() {
		if(heap.size()==2){
			return heap.remove(1);
		}
		Tile temp=heap.get(1);
		heap.set(1,heap.remove(heap.size()-1));
		Tile cur=heap.get(1);
		int i =1;
		Tile min;
		while((i*2<heap.size()&& cur.costEstimate>heap.get(i*2).costEstimate) || ((i*2+1)<heap.size()&& cur.costEstimate>heap.get((i*2)+1).costEstimate)){
			if((i*2+1)<heap.size()&& heap.get(i*2).costEstimate>heap.get((i*2)+1).costEstimate){
				min=heap.get((i*2)+1);
				heap.set((i*2)+1,cur);
				heap.set(i,min);

				i=(i*2)+1;
			}

			else{
				min=heap.get(i*2);
				heap.set(i*2,cur);
				heap.set(i,min);

				i=i*2;
			}

		}
		return temp;
	}
	
	// TODO level 3: implement updateKeys as described in the pdf
	public void updateKeys(Tile t, Tile newPred, double newEstimate) {
		int j=0;
		boolean flag=true;
		for(int i=1;i<heap.size();i++){
			if(heap.get(i).equals(t)){
				heap.get(i).predecessor=newPred;
				heap.get(i).costEstimate=newEstimate;
				j=i;
				flag=false;
				break;
			}
		}
		if(flag){
			return;
		}
		Tile cur =heap.get(j);
		while(j>1&&cur.costEstimate<heap.get(j/2).costEstimate){
			Tile temp=heap.get(j/2);
			heap.set(j/2,cur);
			heap.set(j,temp);
			j=j/2;
		}

		while((j*2<heap.size()&&cur.costEstimate>heap.get(j*2).costEstimate) || ((j*2)+1<heap.size()&&cur.costEstimate>heap.get((j*2)+1).costEstimate)){
			if((j*2)+1<heap.size() && heap.get(j*2).costEstimate>heap.get((j*2)+1).costEstimate){
				Tile temp=heap.get((j*2)+1);
				heap.set((j*2)+1,cur);
				heap.set(j,temp);
				j=(j*2)+1;
			}
			else{
				Tile temp=heap.get((j*2));
				heap.set((j*2),cur);
				heap.set(j,temp);
				j=(j*2);
			}

		}
	}

}
