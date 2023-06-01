import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DijkstraAlgorithmFull {
	private static final int INFINITY = Integer.MAX_VALUE;

	public void dijkstra(int[][] graph, int source) {

		int numNodes = graph.length;

		// Tableau pour stocker les distances
		int[] distances = new int[numNodes];

		// Tableau pour suivre les nœuds visités
		boolean[] visited = new boolean[numNodes];
		// Tableau pour stocker les prédécesseurs
		int[] predecessors = new int[numNodes];

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

		// Affichage des chemins les plus courts
		printShortestPaths(distances, predecessors, source);
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

	// Méthode pour afficher les chemins les plus courts
	private void printShortestPaths(int[] distances, int[] predecessors, int source) {
		int numNodes = distances.length;

		System.out.println("Plus courts chemins depuis le nœud source " + source + ":");

		for (int i = 0; i < numNodes; i++) {
			if (i != source) {
				List<Integer> path = new ArrayList<>();
				int current = i;

				while (current != -1) {
					path.add(0, current);
					current = predecessors[current];
				}

				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < path.size() - 1; j++) {
					sb.append(path.get(j)).append(" -> ");
				}
				sb.append(path.get(path.size() - 1));

				System.out.println(
						"Chemin vers le nœud " + i + ": Distance = " + distances[i] + ", Chemin = " + sb.toString());
			}
		}
	}

//	// Exemple d'utilisation
//	public static void main(String[] args) {
//		int[][] graph = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
//				{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
//				{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
//
//		DijkstraAlgorithmFull algorithm = new DijkstraAlgorithmFull();
//		algorithm.dijkstra(graph, 0);
//	}
}
