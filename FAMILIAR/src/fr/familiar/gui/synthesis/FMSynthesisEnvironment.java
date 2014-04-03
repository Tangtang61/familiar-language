/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;

import fr.familiar.gui.FamiliarConsole;
import fr.familiar.gui.Tab2EnvVar;
import fr.familiar.gui.synthesis.actions.IgnoreParentAction;
import fr.familiar.gui.synthesis.actions.SelectClusterParentAction;
import fr.familiar.gui.synthesis.actions.SelectParentAction;
import fr.familiar.gui.synthesis.actions.SetRootAction;
import fr.familiar.gui.synthesis.actions.SynthesisAction;
import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.KSynthesisPlugin;
import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.familiar.operations.heuristics.metrics.MetricName;
import fr.familiar.variable.FeatureModelVariable;

public class FMSynthesisEnvironment extends JPanel implements Observer{

	private InteractiveFMSynthesizer synthesizer;
	private FMViewer fmViewer;
	private BIGViewer bigViewer;
	private ParentSelector parentSelector;
	private ClusterViewer clusterViewer;
	private CliqueViewer cliqueViewer;
	private LinkedList<SynthesisAction> history;

	public FMSynthesisEnvironment(InteractiveFMSynthesizer synthesizer) {
		this.synthesizer = synthesizer;
		synthesizer.addObserver(this);
		
		history = new LinkedList<SynthesisAction>();

		// Create views
		fmViewer = new JGraphXFMViewer();
		bigViewer = new JGraphXBIGViewer();
		parentSelector = new ParentSelector(this);
		clusterViewer = new ClusterViewer(this);
		cliqueViewer = new CliqueViewer(this);

		// Set layout
		this.setLayout(new GridLayout(1, 1));
		JSplitPane cliqueClusterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, clusterViewer, cliqueViewer);
		JSplitPane leftSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, parentSelector, cliqueClusterSplitPane);
		//		JSplitPane fmBigSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fmViewer, bigViewer); 
		//		JSplitPane globalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplitPane, fmBigSplitPane);
		JSplitPane globalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplitPane, fmViewer);
		this.add(globalSplitPane);

		// Create a new tab with the environment and add the new FM to the variables
		FeatureModelVariable newFmv = synthesizer.getFeatureModelVariable();
		FamiliarConsole.INSTANCE.addOrReplaceFMVariable(newFmv);
		Tab2EnvVar.INSTANCE.createNewTab(newFmv.getIdentifier(), this);

		update(synthesizer, null); // Initialize display

		// Adjust layout
		cliqueClusterSplitPane.setDividerLocation(0.5);
		leftSplitPane.setDividerLocation(0.5);
		globalSplitPane.setDividerLocation(0.2);
		
		// Handle keyboard shortcuts
		UndoAction undoAction = new UndoAction(this);
		this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "undo");
		this.getActionMap().put("undo", undoAction);
		
		
	}


	@Override
	public void update(Observable o, Object arg) {
		fmViewer.updateFM(synthesizer.getFeatureModelVariable());
		//		bigViewer.updateBIG(synthesizer.getWeightedImplicationGraph());
		parentSelector.updateParents(synthesizer.getParentCandidates());
		Set<Set<String>> similarityClusters = synthesizer.getSimilarityClusters();
		if (similarityClusters != null) {
			clusterViewer.updateSimilarityClusters(similarityClusters);	
		}
		cliqueViewer.updateCliques(synthesizer.getCliques());

		List<Set<Set<String>>> supportClusters = synthesizer.getSupportClusters();
		if (supportClusters != null) {
			clusterViewer.updateSupportClusters(supportClusters);	
		}
	}

	public void selectParent(String child, String parent) {
		selectParentWithoutHistory(child, parent);
		history.push(new SelectParentAction(synthesizer, child, parent));
	}
	
	public void selectParentWithoutHistory(String child, String parent) {
		String selectedParent = synthesizer.getParentOf(child);
		if (selectedParent == null) {
			synthesizer.selectParent(child, parent);
		} else if (!selectedParent.equals(parent)){
			int choice = JOptionPane.showConfirmDialog(null, 
					"Do you want to replace the current parent \"" + selectedParent + "\" of \"" + child + "\" by \"" + parent + "\"?");
			if (choice == JOptionPane.YES_OPTION) {
				synthesizer.selectParent(child, parent);
			}
		}
	}

	public void ignoreParent(String child, String parent) {
		synthesizer.ignoreParent(child, parent);
		history.push(new IgnoreParentAction(synthesizer, child, parent));
	}

	public void updateSelectedFeatures(List<String> selectedFeatures, List<String> unselectedFeatures) {
		clusterViewer.updateSelectedClusters(selectedFeatures, unselectedFeatures);
		cliqueViewer.updateSelectedCliques(selectedFeatures, unselectedFeatures);
	}

	public void setRoot(String root) {
		String selectedRoot = synthesizer.getRoot();
		if (selectedRoot == null) {
			synthesizer.setRoot(root);
			history.push(new SetRootAction(synthesizer, root));
		} else {
			int choice = JOptionPane.showConfirmDialog(null, 
					"Do you want to replace the current root \"" + selectedRoot + "\" by \"" + root + "\"?");
			if (choice == JOptionPane.YES_OPTION) {
				synthesizer.setRoot(root);
				history.push(new SetRootAction(synthesizer, root));
			}
		}
		
	}

	public void setParentSimilarityMetric(Heuristic metric) {
		synthesizer.setParentSimilarityMetric(metric);
	}


	public void setClusteringMetric(Heuristic metric) {
		synthesizer.setClusteringParameters(metric, synthesizer.getClusteringThreshold());
	}

	public void setClusteringThreshold(double threshold) {
		synthesizer.setClusteringParameters(synthesizer.getClusteringSimilarityMetric(), threshold);
	}

	public void computeCompleteFeatureModel() {
		FeatureModelVariable computedFM = synthesizer.computeCompleteFeatureModel();
		FamiliarConsole.INSTANCE.addOrReplaceFMVariable(computedFM);
		FMViewer computedFMViewer = new JGraphXFMViewer();
		computedFMViewer.updateFM(computedFM);
		Tab2EnvVar.INSTANCE.createNewTab(computedFM.getIdentifier(), computedFMViewer);

	}


	public static JMenu createSynthesisMenu() {

		// Parent similarity metric selection
		JMenu parentSimilarityMetricMenu = new JMenu("Similarity metric");
		ButtonGroup parentSimilarityMetricGroup = new ButtonGroup();

		for (MetricName metric : MetricName.values()) {
			JRadioButtonMenuItem metricItem = new JRadioButtonMenuItem(metric.toString());
			metricItem.addActionListener(new SimilarityMetricSelectionListener(metric));
			parentSimilarityMetricMenu.add(metricItem);
			parentSimilarityMetricGroup.add(metricItem);
			if (metric.equals(InteractiveFMSynthesizer.defaultParentSimilarityMetric)) {
				metricItem.setSelected(true);	
			}
		}


		// Clustering metric selection
		JMenu clusteringMetricMenu = new JMenu("Clustering metric");
		ButtonGroup clusteringMetricGroup = new ButtonGroup();

		for (MetricName metric : MetricName.values()) {
			JRadioButtonMenuItem metricItem = new JRadioButtonMenuItem(metric.toString());
			metricItem.addActionListener(new ClusteringMetricSelectionListener(metric));
			clusteringMetricMenu.add(metricItem);
			clusteringMetricGroup.add(metricItem);
			if (metric.equals(InteractiveFMSynthesizer.defaultClusteringSimilarityMetric)) {  
				metricItem.setSelected(true);
			}
		}

		// Clustering threshold selection
		JMenuItem clusteringThreshold= new JMenuItem("Define clustering threshold...");
		clusteringThreshold.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

				if (tab instanceof FMSynthesisEnvironment) {
					FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;

					// Display a input dialog to enter the threshold
					ThresholdValueDialog dialog = new ThresholdValueDialog();

					double threshold = dialog.getValue();
					if (threshold != -1) {
						environment.setClusteringThreshold(threshold);	
					}

				}
			}
		});

		// Undo button
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

				if (tab instanceof FMSynthesisEnvironment) {
					FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;
					environment.undo();
				}
			}
		});
		
		
		// Complete FM according to a heuristic
		JMenuItem completeFM = new JMenuItem("Complete FM");
		completeFM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

				if (tab instanceof FMSynthesisEnvironment) {
					FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;
					environment.computeCompleteFeatureModel();
				}
			}
		});

		// Create menu
		JMenu synthesisMenu = new JMenu("Synthesis");

		synthesisMenu.add(parentSimilarityMetricMenu);
		synthesisMenu.add(clusteringMetricMenu);
		synthesisMenu.add(clusteringThreshold);
		synthesisMenu.add(completeFM);
		synthesisMenu.add(undo);

		// Enable the menu only when the active tab is a synthesis environment
		Tab2EnvVar.INSTANCE.getTab().addChangeListener(new SynthesisTabListener(synthesisMenu));

		return synthesisMenu;
	}


	public void selectClusterParent(Set<String> cluster) {
		CommonParentSelectionDialog dialog = new CommonParentSelectionDialog(
				this, "Cluster's parent selection", "Select the parent of this cluster.", cluster, cluster);
		dialog.show();
		Set<String> selectedChildren = dialog.getSelectedChildren();
		String selectedParent = dialog.getSelectedParent();
		if (selectedParent != null) {
			for (String selectedChild : selectedChildren) {
				synthesizer.selectParent(selectedChild, selectedParent);
			}	
			history.push(new SelectClusterParentAction(synthesizer, selectedChildren, selectedParent));
		}

	}


	public void selectFeatureAsParentOf(String feature, Set<String> cluster) {
		Set<String> possibleChildren = synthesizer.getPossibleChildren(feature, cluster);
		if (possibleChildren.isEmpty()) {
			JOptionPane.showMessageDialog(null, "\"" + feature + "\" can not be a parent of one of these features.");
		} else {
			Set<String> selectedChildren = CheckBoxDialog.showCheckBoxDialog(
					"Children selection",
					"Select the children of \"" + feature + "\"", 
					possibleChildren, possibleChildren);
			for (String selectedChild : selectedChildren) {
				this.selectParentWithoutHistory(selectedChild, feature);
			}
			history.push(new SelectClusterParentAction(synthesizer, selectedChildren, feature));
		}
	}


	public Set<String> getPossibleParents(Set<String> features) {
		return synthesizer.getPossibleParents(features);
	}


	public InteractiveFMSynthesizer getSynthesizer() {
		return synthesizer;
	}


	public void updateSelectedClusters(List<Set<String>> selectedClusters, List<Set<String>> unselectedClusters) {
		fmViewer.updateSelectedClusters(selectedClusters, unselectedClusters);
	}

	public void undo() {
		if(!history.isEmpty()) {
			SynthesisAction lastCommand = history.pop();
			lastCommand.undo();
		}
	}
	
	
	private class UndoAction extends AbstractAction {

		private FMSynthesisEnvironment fmSynthesisEnvironment;

		public UndoAction(FMSynthesisEnvironment fmSynthesisEnvironment) {
			this.fmSynthesisEnvironment = fmSynthesisEnvironment;
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fmSynthesisEnvironment.undo();
		}


	}


}
