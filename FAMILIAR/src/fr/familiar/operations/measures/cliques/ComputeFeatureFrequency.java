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

package fr.familiar.operations.measures.cliques;

import fr.familiar.operations.heuristics.mst.WeightedImplicationGraph;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureModelVariableBDDFormula;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.Expression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ComputeFeatureFrequency {
	
	 private Map<SimpleEdge, Double> suppMap = new HashMap<SimpleEdge, Double>();
	 private Map<SimpleEdge, Double> confMap = new HashMap<SimpleEdge, Double>();
	 private ImplicationGraph<String> big;
	 private WeightedImplicationGraph supp_big; 
	 private WeightedImplicationGraph conf_big; 
	 
	 public ComputeFeatureFrequency (FeatureModelVariable phiR){
		 
		 	this.big = phiR.computeImplicationGraph() ;
			this.supp_big = new WeightedImplicationGraph(big);
			this.conf_big = new WeightedImplicationGraph(big);
	 }
	 
	 public void computeSupportConfidenceMeasures(FeatureModelVariable phiR) {
		 
		    System.err.println("phiR (BIG)=" + big);
			double trans_nb = phiR.counting();
			System.err.println("Nb total transactions = " + trans_nb);	
			System.err.println("phiR d'origine=" + phiR + "\n");
			
			Collection<SimpleEdge> ed = big.edges() ;
			for (SimpleEdge e : ed) {
				System.out.println("titi");
				FeatureModelVariable phiRprime = new FeatureModelVariableBDDFormula("", phiR.getFormula().clone(), phiR.getBuilder());
				System.err.println("phiR before calcul support =" + phiRprime.getFormula());
				getSupportConfidence(phiRprime, big, e, trans_nb);
				System.err.println("phiR after calcul support =" + phiRprime + "\n");
			}
			
			Set<SimpleEdge> suppKeys = suppMap.keySet();
			Iterator<SimpleEdge> suppItr = suppKeys.iterator();
			while (suppItr.hasNext()){
			   SimpleEdge cle = suppItr.next(); 
			   Double valeur = suppMap.get(cle); 
			   System.err.println("support of "+ big.getSource(cle) + big.getTarget(cle) +" is "+ valeur);
			}
			Set<SimpleEdge> confKeys = suppMap.keySet();
			Iterator<SimpleEdge> confItr = confKeys.iterator();
			while (confItr.hasNext()){
			   SimpleEdge cle = confItr.next(); 
			   Double valeur = confMap.get(cle); 
			   System.err.println("confidence of "+ big.getSource(cle) + big.getTarget(cle) +" is "+ valeur);
			}
			
			supp_big.setWeights(suppMap);
			conf_big.setWeights(confMap);
		}

	 public void getSupportConfidence(FeatureModelVariable phiR, ImplicationGraph<String> big, SimpleEdge e, double trans_nb){
			
	        System.out.println("before s : " + phiR.getFormula());
			phiR.addConstraint(new ConstraintVariable(new Expression<String>(big.getSource(e)), ""));
			System.out.println("after s : " + phiR.getFormula());
			double sourceSupp = phiR.counting()/trans_nb;
			
			System.out.println("before t : " + phiR.getFormula());
			phiR.addConstraint(new ConstraintVariable(new Expression<String>(big.getTarget(e)), ""));
			System.out.println("after t : " + phiR.getFormula());
			
			double supp = phiR.counting()/trans_nb;
			suppMap.put(e, supp);
			confMap.put(e, supp/sourceSupp);
			
			System.err.println("#phiR'=" + phiR.counting()); 
	 }
		public Map<SimpleEdge, Double> getSuppMap() {
			return suppMap;
		}
		public void setSuppMap(Map<SimpleEdge, Double> suppMap) {
			this.suppMap = suppMap;
		}
		public Map<SimpleEdge, Double> getConfMap() {
			return confMap;
		}
		public void setConfMap(Map<SimpleEdge, Double> confMap) {
			this.confMap = confMap;
		}
		public ImplicationGraph<String> getBig() {
			return big;
		}
		public void setBig(ImplicationGraph<String> big) {
			this.big = big;
		}
		public WeightedImplicationGraph getSupp_big() {
			return supp_big;
		}
		public void setSupp_big(WeightedImplicationGraph supp_big) {
			this.supp_big = supp_big;
		}
		public WeightedImplicationGraph getConf_big() {
			return conf_big;
		}
		public void setConf_big(WeightedImplicationGraph conf_big) {
			this.conf_big = conf_big;
		}
		}
