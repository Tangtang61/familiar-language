package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import net.sf.extjwnl.dictionary.Dictionary;
import fr.unice.polytech.modalis.familiar.gui.FamiliarEditor;
import fr.unice.polytech.modalis.familiar.gui.Tab2EnvVar;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RandomMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerDB;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WuPalmerMetric;

public class ClusteringMetricSelectionListener implements ActionListener {

	private MetricName metric;

	public ClusteringMetricSelectionListener(MetricName metric) {
		this.metric = metric;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

		if (tab instanceof FMSynthesisEnvironment) {
			FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;
			switch (metric) {
			case ALWAYS_ZERO:
				environment.setClusteringMetric(new AlwaysZeroMetric());
				break;
			case RANDOM:
				environment.setClusteringMetric(new RandomMetric());
				break;
			case SIMMETRICS_SMITHWATERMAN:
				environment.setClusteringMetric(new SimmetricsMetric(metric));
				break;
			case SIMMETRICS_LEVENSHTEIN:
				environment.setClusteringMetric(new SimmetricsMetric(metric));
				break;
			case WORDNET_WUP:
				Dictionary dictionaryWUP = WordNetPropertyFileChooser.getInstance();
				if (dictionaryWUP != null) {
					environment.setClusteringMetric(new WuPalmerMetric(dictionaryWUP));	
				}
				break;
			case WORDNET_PATHLENGTH:
				Dictionary dictionaryPL = WordNetPropertyFileChooser.getInstance();
				if (dictionaryPL != null) {
					environment.setClusteringMetric(new WuPalmerMetric(dictionaryPL));	
				}
				break;
			case WIKIPEDIA_MINER:
				JFileChooser fileChooser = new JFileChooser();
				int choice = fileChooser.showOpenDialog(FamiliarEditor.INSTANCE);
				if (choice == JFileChooser.APPROVE_OPTION) {
					File propertiesFile = fileChooser.getSelectedFile();
					WikipediaMinerDB wikipediaMinerDB = new WikipediaMinerDB(propertiesFile.getAbsolutePath());
					try {
						wikipediaMinerDB.loadDatabase();
						WikipediaMinerMetric wikipediaMinerMetric = new WikipediaMinerMetric(wikipediaMinerDB);
						environment.setClusteringMetric(wikipediaMinerMetric);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}	
				break;
				
				// TODO : add LSA when ready
			default:
				break;

			}
			
		}

	}

}
