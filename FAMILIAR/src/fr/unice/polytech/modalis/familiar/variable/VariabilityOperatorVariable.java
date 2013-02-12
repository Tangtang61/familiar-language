/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.unice.polytech.modalis.familiar.variable;

import org.xtext.example.mydsl.fML.FeatureEdgeKind;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.NSFactory;
import fr.unice.polytech.modalis.familiar.parser.NameSpace;

public class VariabilityOperatorVariable extends VariableImpl {

	private FeatureEdgeKind fek;

	public VariabilityOperatorVariable(String name, NameSpace ns,
			FeatureEdgeKind fek) {
		this.name = name;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
		this.fek = fek;
	}

	public VariabilityOperatorVariable(String name, FeatureEdgeKind fek) {
		this(name, NSFactory.mkEmpty(), fek);
	}

	@Override
	public Variable copy() {
		return new VariabilityOperatorVariable(getIdentifier(), getFek());
	}

	@Override
	public RType getRType() {
		return RType.VARIABILITY_OPERATOR;
	}

	@Override
	public String getSpecificValue() {
		return fek.name();
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof VariabilityOperatorVariable) {
			VariabilityOperatorVariable vow = (VariabilityOperatorVariable) vari;
			setFek(vow.getFek());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");
	}

	/**
	 * @return the fek
	 */
	public FeatureEdgeKind getFek() {
		return fek;
	}

	/**
	 * @param fek
	 *            the fek to set
	 */
	public void setFek(FeatureEdgeKind fek) {
		this.fek = fek;
	}

}