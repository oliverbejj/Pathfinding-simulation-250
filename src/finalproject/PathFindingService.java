package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class PathFindingService {
	Tile source;
	Graph g;
	
	public PathFindingService(Tile start) {
    	this.source = start;
    }

	public abstract void generateGraph();
    
    //TODO level 4: Implement basic dijkstra's algorithm to find a path to the final unknown destination
    public ArrayList<Tile> findPath(Tile startNode) {
        init_ss(g.vertices,startNode);

        ArrayList<Tile> S= new ArrayList<>();
        TilePriorityQ Q= new TilePriorityQ(g.vertices);

        while(Q.heap.size()>1){
            Tile u= Q.removeMin();

            S.add(u);
            for(Tile each: g.getNeighbors(u)){
                ArrayList<Tile> uv= new ArrayList<>();
                uv.add(u);
                uv.add(each);
                if(each.costEstimate>u.costEstimate+g.computePathCost(uv)){
                    each.costEstimate=u.costEstimate+g.computePathCost(uv);
                    each.predecessor=u;
                    Q.updateKeys(each,u,u.costEstimate+g.computePathCost(uv));
                }
            }
        }
        ArrayList<Tile> f=new ArrayList<>();
        Tile temp=S.get(0);
        for (Tile each: S){
            if (each.isDestination) {
                temp=each;
                break;
            }
        }
        while(!temp.equals(startNode)){
            f.add(0,temp);
            temp=temp.predecessor;
        }
        f.add(0,startNode);
        return f;

    }
    private void init_ss(ArrayList<Tile> a,Tile t){
        for(Tile each:a){
            each.costEstimate=Double.POSITIVE_INFINITY;
            each.predecessor=null;
        }
        t.costEstimate=0.0;
    }


    
    //TODO level 5: Implement basic dijkstra's algorithm to path find to a known destination
    public ArrayList<Tile> findPath(Tile start, Tile end) {
        init_ss(g.vertices,start);

        ArrayList<Tile> S= new ArrayList<>();
        TilePriorityQ Q= new TilePriorityQ(g.vertices);

        while(Q.heap.size()>1){
            Tile u= Q.removeMin();

            S.add(u);
            for(Tile each: g.getNeighbors(u)){
                ArrayList<Tile> uv= new ArrayList<>();
                uv.add(u);
                uv.add(each);
                if(each.costEstimate>u.costEstimate+g.computePathCost(uv)){
                    each.costEstimate=u.costEstimate+g.computePathCost(uv);
                    each.predecessor=u;
                    Q.updateKeys(each,u,u.costEstimate+g.computePathCost(uv));
                }
            }
        }
        ArrayList<Tile> f=new ArrayList<>();
        Tile temp=end;
        while(!temp.equals(start)){
            f.add(0,temp);
            temp=temp.predecessor;
        }
        f.add(0,start);
        return f;
    }

    //TODO level 5: Implement basic dijkstra's algorithm to path find to the final destination passing through given waypoints
    public ArrayList<Tile> findPath(Tile start, LinkedList<Tile> waypoints){

        ArrayList<Tile> f=new ArrayList<>();
        if(!waypoints.isEmpty()){
            f=findPath(start,waypoints.get(0));
            Tile temp=f.remove(f.size()-1);
            for (int i=0;i<waypoints.size()-1;i++){
                ArrayList<Tile> sub = findPath(waypoints.get(i),waypoints.get(i+1));
                temp= sub.remove(sub.size()-1);
                f.addAll(sub);
            }
            f.addAll(findPath(temp));
            return f;
        }else{
            return findPath(start);
        }
    }
        
}

