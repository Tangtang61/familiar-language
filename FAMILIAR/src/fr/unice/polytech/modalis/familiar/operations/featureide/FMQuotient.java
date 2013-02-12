package fr.unice.polytech.modalis.familiar.operations.featureide;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.SatSolver;
import org.sat4j.specs.TimeoutException;

import fr.unice.polytech.modalis.familiar.operations.FeatureIDEUtils;
import fr.unice.polytech.modalis.familiar.parser.MyExpressionParser;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

/**
 * Based on the algorithm proposed by Uli Fahrenberg, Axel Legay, Wasowski et al. MODELS'11
 * @author macher1
 *
 */
public class FMQuotient {
	
	protected Logger _LOGGER = Logger.getLogger(FMQuotient.class);

	private FeatureModelVariable _lfmv;
	private FeatureModelVariable _rfmv;

	public FMQuotient(FeatureModelVariable lfmv, FeatureModelVariable rfmv) {
		_lfmv = lfmv ; 
		_rfmv = rfmv ; 
	}
	
	public Set<Expression<String>> quotient() {
	Set<Expression<String>> exprs = new HashSet<Expression<String>>();
	try {
		Set<Node> quotients = _quotient() ;
		for (Node quotient : quotients) {
			
			String toStringExpr = quotient.toString(FeatureIDEUtils._textualSymbols) ;
			exprs.add(MyExpressionParser.parseString(toStringExpr));				
		}
	} catch (TimeoutException e) {
		e.printStackTrace();
		_LOGGER.debug("Unable to compute the quotient in time: " + e);
	} 
	
	return exprs;
	}

	public Set<Node> _quotient() throws TimeoutException {
		// input
		Node n1 = new SATFMLFormula(_lfmv).getNode() ;
		//n1 = new And(n1, SATBuilder.mkTrueNode(), new Not(
			//	SATBuilder.mkFalseNode()));
		//n1.simplify();
		n1 = n1.toCNF() ; 
		
		Node n2 = new SATFMLFormula(_rfmv).getNode() ;
		//n2 = new And(n2, SATBuilder.mkTrueNode(), new Not(
			//	SATBuilder.mkFalseNode()));
		//n2.simplify();
		n2 = n2.toCNF() ;
		
		
		Set<Node> r = new HashSet<Node>();
		// for each clause		
		Node[] c1 = n1.getChildren() ;
		SatSolver sat2 = new SatSolver(n2, SATFMLFormula.SAT_TIMEOUT) ; 
		for (int i = 0; i < c1.length; i++) {
			Node clause = c1[i] ;
			Node rClause = clause.clone() ;
			if (sat2.isSatisfiable(new Not(clause).toCNF())) {
				r.add(rClause);
			}
			
		}
		
		return r ;
	}

}