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

package fr.familiar.parser;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.Leaves;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.SetVariable;

/**
 * @author macher1
 *
 */
public class LeavesAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public LeavesAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public LeavesAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		assert (_command instanceof Leaves);
		Leaves leavesCmd = (Leaves) _command;

		FMCommand fmCmd = leavesCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
		SetVariable cls = fmv.leaves() ;
		setVariable(cls);

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.SET ; 
	}

}