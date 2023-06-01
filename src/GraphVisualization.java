import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphVisualization extends JFrame {
	private static final int NODE_RADIUS = 30;
	private static final int ARC_WIDTH = 2;
	private static final Color DEFAULT_ARC_COLOR = Color.BLACK;
	private static final Color SELECTED_ARC_COLOR = Color.GREEN;

	private int[][] graph;
	private int source;
	private int destination;
	private Set<Integer> selectedArcs;

	public GraphVisualization(int[][] graph, int source, int destination, Set<Integer> selectedArcs) {
		this.graph = graph;
		this.source = source;
		this.destination = destination;
		this.selectedArcs = selectedArcs;

		setTitle("Graph Visualization");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setContentPane(new GraphPanel());
	}

	private class GraphPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			int numNodes = graph.length;
			int centerX = getWidth() / 2;
			int centerY = getHeight() / 2;
			int radius = Math.min(centerX, centerY) - NODE_RADIUS - 10;

			// Dessiner les nœuds
			for (int i = 0; i < numNodes; i++) {
				double angle = (2 * Math.PI * i) / numNodes;
				int x = (int) (centerX + radius * Math.cos(angle));
				int y = (int) (centerY + radius * Math.sin(angle));

				// Dessiner le cercle du nœud
				g.setColor(Color.BLUE);
				g.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

				// Dessiner le numéro du nœud
				g.setColor(Color.WHITE);
				g.drawString(String.valueOf(i), x - 20, y + 4);
			}

			// Dessiner les arcs
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					int weight = graph[i][j];
					if (weight > 0) {
						double startAngle = (2 * Math.PI * i) / numNodes;
						double endAngle = (2 * Math.PI * j) / numNodes;

						int startX = (int) (centerX + radius * Math.cos(startAngle));
						int startY = (int) (centerY + radius * Math.sin(startAngle));
						int endX = (int) (centerX + radius * Math.cos(endAngle));
						int endY = (int) (centerY + radius * Math.sin(endAngle));

						// Déterminer la couleur de l'arc en fonction de s'il est sélectionné ou non
						Color arcColor = selectedArcs.contains(i) && selectedArcs.contains(j) ? SELECTED_ARC_COLOR
								: DEFAULT_ARC_COLOR;

						// Dessiner l'arc
						Graphics2D g2d = (Graphics2D) g;
						g2d.setStroke(new BasicStroke(ARC_WIDTH));
						g2d.setColor(arcColor);
						g2d.drawLine(startX, startY, endX, endY);

						// Dessiner la pondération de l'arc
						g2d.setColor(Color.BLACK);
						int labelX = (startX + endX) / 2;
						int labelY = (startY + endY) / 2;
						g2d.drawString(String.valueOf(weight), labelX, labelY);
					}
				}
			}
		}
	}

	public void displayGraph() {
		setVisible(true);
	}

	// Exemple d'utilisation
	public static void main(String[] args) {
		int[][] graph = { { 0, 4, 0, 0, 0, 0, 0, 8, 5 }, { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
				{ 0, 0, 7, 0, 9, 14, 0, 0, 0 }, { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
				{ 0, 0, 0, 0, 0, 2, 0, 1, 6 }, { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, { 5, 0, 2, 0, 0, 0, 6, 7, 0 } };

		int source = 4;
		int destination = 8;

		Set<Integer> selectedArcs = new HashSet<>();
		DijkstraAlgorithm algorithm = new DijkstraAlgorithm();

		List<Integer> shortest = algorithm.dijkstra(graph, source, destination);

		for (Integer s : shortest) {
			selectedArcs.add(s);
		}

		GraphVisualization visualization = new GraphVisualization(graph, source, destination, selectedArcs);
		visualization.displayGraph();
	}
}
