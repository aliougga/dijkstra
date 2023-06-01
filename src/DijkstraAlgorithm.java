import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DijkstraAlgorithm {
	private static final int INFINITY = Integer.MAX_VALUE;

	public List<Integer> dijkstra(int[][] graph, int source, int destination) {
		int numNodes = graph.length;
		// Tableau pour stocker les distances
		int[] distances = new int[numNodes];
		boolean[] visited = new boolean[numNodes]; // Tableau pour suivre les nœuds visités
		int[] predecessors = new int[numNodes]; // Tableau pour stocker les prédécesseurs

		// Initialisation des distances et des prédécesseurs
		Arrays.fill(distances, INFINITY);
		Arrays.fill(predecessors, -1);

		// La distance du nœud source à lui-même est de 0
		distances[source] = 0;

		// Boucle principale
		for (int i = 0; i < numNodes - 1; i++) {
			// Sélection du nœud non visité avec la distance minimale
			int minDistanceNode = findMinDistanceNode(distances, visited);
			visited[minDistanceNode] = true;

			// Mise à jour des distances des nœuds voisins
			for (int j = 0; j < numNodes; j++) {
				if (!visited[j] && graph[minDistanceNode][j] != 0 && distances[minDistanceNode] != INFINITY
						&& distances[minDistanceNode] + graph[minDistanceNode][j] < distances[j]) {
					distances[j] = distances[minDistanceNode] + graph[minDistanceNode][j];
					predecessors[j] = minDistanceNode;
				}
			}
		}

		// Affichage du plus court chemin de la source à la destination
		// printShortestPath(distances, predecessors, source, destination);

		return getShortestPath(distances, predecessors, source, destination);
	}

	// Méthode pour trouver le nœud non visité avec la distance minimale
	private int findMinDistanceNode(int[] distances, boolean[] visited) {
		int minDistance = INFINITY;
		int minDistanceNode = -1;

		for (int i = 0; i < distances.length; i++) {
			if (!visited[i] && distances[i] < minDistance) {
				minDistance = distances[i];
				minDistanceNode = i;
			}
		}

		return minDistanceNode;
	}

	// Méthode pour afficher le plus court chemin de la source à la destination
	private void printShortestPath(int[] distances, int[] predecessors, int source, int destination) {
		List<Integer> path = new ArrayList<>();
		int current = destination;

		while (current != -1) {
			path.add(0, current);
			current = predecessors[current];
		}

		if (path.get(0) == source) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < path.size() - 1; i++) {
				sb.append(path.get(i)).append(" -> ");
			}
			sb.append(path.get(path.size() - 1));

			System.out.println("Plus court chemin de " + source + " à " + destination + ":");
			System.out.println("Distance = " + distances[destination] + ", Chemin = " + sb.toString());
		} else {
			System.out.println("Il n'existe pas de chemin de " + source + " à " + destination);
		}
	}

	private List<Integer> getShortestPath(int[] distances, int[] predecessors, int source, int destination) {
		List<Integer> path = new ArrayList<>();
		int current = destination;

		while (current != -1) {
			path.add(0, current);
			current = predecessors[current];
		}

//		if (path.get(0) == source) {
//			StringBuilder sb = new StringBuilder();
//			for (int i = 0; i < path.size() - 1; i++) {
//				sb.append(path.get(i)).append(" -> ");
//			}
//			sb.append(path.get(path.size() - 1));
//
//			System.out.println("Plus court chemin de " + source + " à " + destination + ":");
//			System.out.println("Distance = " + distances[destination] + ", Chemin = " + sb.toString());
//		} else {
//			System.out.println("Il n'existe pas de chemin de " + source + " à " + destination);
//		}
		return path;
	}

//	// Exemple d'utilisation
//	public static void main(String[] args) {
//		int[][] graph = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
//				{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
//				{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
//
//		int source = 0;
//		int destination = 4;
//
//		DijkstraAlgorithm algorithm = new DijkstraAlgorithm();
//		algorithm.dijkstra(graph, source, destination);
//	}
}
