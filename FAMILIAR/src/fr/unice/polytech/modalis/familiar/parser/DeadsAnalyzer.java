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
package fr.unice.polytech.modalis.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.Deads;
import org.xtext.example.mydsl.fML.FMCommand;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class DeadsAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public DeadsAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public DeadsAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#
	 * eval()
	 */
	@Override
	public void eval() {
		assert (_command instanceof Deads);
		Deads deadsCmd = (Deads) _command;

		FMCommand fmCmd = deadsCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
		Set<String> deads = fmv.deads();
		Set<Variable> vars = new HashSet<Variable>();

		for (String dead : deads) {
			FeatureVariable fv = new FeatureVariable(null, dead, fmv);
			vars.add(fv);
		}

		SetVariable sv = new SetVariable(vars);
		setVariable(sv);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#
	 * getType()
	 */
	@Override
	public RType getType() {
		return RType.SET;
	}

}