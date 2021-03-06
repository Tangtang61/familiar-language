/*
* generated by Xtext
*/
package fr.inria.familiar.fmlero.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import fr.inria.familiar.fmlero.services.FmleroGrammarAccess;

public class FmleroParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private FmleroGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected fr.inria.familiar.fmlero.parser.antlr.internal.InternalFmleroParser createParser(XtextTokenStream stream) {
		return new fr.inria.familiar.fmlero.parser.antlr.internal.InternalFmleroParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "FeatureModel";
	}
	
	public FmleroGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(FmleroGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
