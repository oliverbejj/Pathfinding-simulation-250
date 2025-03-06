package finalproject;

import finalproject.system.Tile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;


public class GraphTraversal {


	//TODO level 1: implement BFS traversal starting from s
	public static ArrayList<Tile> BFS(Tile s) {
		ArrayList<Tile> visited = new ArrayList<>();
		ArrayList<Tile> a = new ArrayList<Tile>();
		ArrayList<Tile> q = new ArrayList<>();

		visited.add(s);
		q.add(s);
		while (!q.isEmpty()) {
			Tile temp = q.remove(0);
			a.add(temp);
			for (Tile each : temp.neighbors) {
				if (each.isWalkable() && !visited.contains(each)) {
					visited.add(each);
					q.add(each);
				}
			}
		}
		return a;
	}


	//TODO level 1: implement DFS traversal starting from s
	public static ArrayList<Tile> DFS(Tile s) {
		ArrayList<Tile> a = new ArrayList<Tile>();
		ArrayList<Tile> st = new ArrayList<>();
		ArrayList<Tile> visited = new ArrayList<>();
		visited.add(s);
		st.add(s);
		while (!(st.isEmpty())) {
			Tile temp = st.remove(st.size()-1);
			a.add(temp);
			visited.add(temp);
			for (Tile each : temp.neighbors) {
				if (!visited.contains(each) && each.isWalkable()) {
					visited.add(each);
					st.add(each);
				}
			}

		}
		return a;
	}



}

