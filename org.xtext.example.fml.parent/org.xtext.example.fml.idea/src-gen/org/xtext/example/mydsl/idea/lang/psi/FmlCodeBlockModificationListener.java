/*
 * generated by Xtext 2.9.1
 */
package org.xtext.example.mydsl.idea.lang.psi;

import com.intellij.psi.util.PsiModificationTracker;
import org.eclipse.xtext.psi.BaseXtextCodeBlockModificationListener;
import org.xtext.example.mydsl.idea.lang.FmlLanguage;

public class FmlCodeBlockModificationListener extends BaseXtextCodeBlockModificationListener {

	public FmlCodeBlockModificationListener(PsiModificationTracker psiModificationTracker) {
		super(FmlLanguage.INSTANCE, psiModificationTracker);
	}

}