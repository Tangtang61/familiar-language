package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class JGraphXFMViewer extends FMViewer {
	
	private final static String VERTEX_DEFAULT_COLOR = "lightblue";
	private final static String VERTEX_SELECTED_COLOR = "orange";
	
	private mxGraph graph;
	private mxHierarchicalLayout layout;
	private HashMap<String, mxCell> vertices;
	
	public JGraphXFMViewer() {
		// Create graph
		graph = new mxGraph();
		layout = new mxHierarchicalLayout(graph, SwingConstants.NORTH);
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setEnabled(false);
		// Set layout
		this.setPreferredSize(new Dimension(100, 100));
		this.setLayout(new GridLayout(1, 1));
		this.add(new JScrollPane(graphComponent));
	}

	@Override
	public void updateFM(FeatureModelVariable fmv) {
		FeatureGraph<String> hierarchy = fmv.getFm().getDiagram();
		vertices = new HashMap<String, mxCell>();
		graph.getModel().beginUpdate();
		graph.selectAll();
		graph.removeCells();
		
		for (FeatureEdge edge : hierarchy.edges()) {
			if (edge.getType() == FeatureEdge.HIERARCHY) {
				FeatureNode<String> source = hierarchy.getSource(edge);
				FeatureNode<String> target = hierarchy.getTarget(edge);
				if (!target.isTop()) {
					mxCell sourceCell = seekVertex(source.getFeature());
					mxCell targetCell = seekVertex(target.getFeature());
					String style = "startArrow=none;endArrow=none";
					graph.insertEdge(graph.getDefaultParent(), null, null, targetCell, sourceCell, style);	
				}
			}
		}

		graph.getModel().endUpdate();
		layout.execute(graph.getDefaultParent());
		this.revalidate();
	}
	
	private mxCell seekVertex(String vertex) {
		mxCell cell = vertices.get(vertex);
		if (cell == null) {
			String style = mxConstants.STYLE_FILLCOLOR + "=" + VERTEX_DEFAULT_COLOR;
			cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, vertex, 0, 0, vertex.length() * 10, 20, style);
			vertices.put(vertex, cell);
		}
		return cell;
	}

	@Override
	public void updateSelectedClusters(List<Set<String>> selectedClusters, List<Set<String>> unselectedClusters) {
		for (Set<String> unselectedCluster : unselectedClusters) {
			for (String feature : unselectedCluster) {
				mxCell vertex = vertices.get(feature);
				if (vertex != null) {
					vertex.setStyle(mxConstants.STYLE_FILLCOLOR + "=" + VERTEX_DEFAULT_COLOR);
				}
			}
		}
		
		for (Set<String> selectedCluster : selectedClusters) {
			for (String feature : selectedCluster) {
				mxCell vertex = vertices.get(feature);
				if (vertex != null) {
					vertex.setStyle(mxConstants.STYLE_FILLCOLOR + "=" + VERTEX_SELECTED_COLOR);
				}
			}
		}
		
		graph.refresh();
	}


}
