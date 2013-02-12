package fr.unice.polytech.modalis.familiar.fm;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.javabdd.BDD;

public class FMLBDDReader {

	private String _filename;

	private boolean hasBeenParsed;

	private StringBuilder _bdd;
	private StringBuilder _fm;
	private StringBuilder _builder;

	private BDDBuilder<String> _BDDBuilder = null;

	public FMLBDDReader(String filename, BDDBuilder<String> builder) {
		_BDDBuilder = builder;
		_filename = filename;
		assert (_filename.endsWith(FMLBDDWriter.FML_BDD_EXTENSION));
		reset();
	}

	public FMLBDDReader(String filename) {
		this(filename, null);
	}

	public String getFMRepresentation() {

		if (!hasBeenParsed)
			parseFile();
		hasBeenParsed = true;

		return _fm.toString();
	}

	public String getBDDRepresentation() {
		if (!hasBeenParsed)
			parseFile();
		hasBeenParsed = true;

		return _bdd.toString();
	}

	public String getBuilderRepresentation() {
		if (!hasBeenParsed)
			parseFile();
		hasBeenParsed = true;

		return _builder.toString();
	}

	public void reset() {
		hasBeenParsed = false;
		_bdd = null;
		_fm = null;
		_builder = null;
	}

	private void parseFile() {

		File file = new File(_filename);

		try {
			boolean inBDD = false;
			boolean inFM = false;
			boolean inBuilder = false;

			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = "";
			while ((str = br.readLine()) != null) {

				// close brackets
				if (str.equals(FMLBDDWriter._END_BDD)) {
					inBDD = false;
					assert (_bdd != null);
				}
				if (str.equals(FMLBDDWriter._END_FM)) {
					inFM = false;
					assert (_fm != null);
				}
				if (str.equals(FMLBDDWriter._END_BUILDER)) {
					inBuilder = false;
					assert (_builder != null);
				}

				if (inBuilder) {
					_builder.append(str + FMLBDDWriter._NEWLINE);
				}

				if (inBDD) {
					_bdd.append(str + FMLBDDWriter._NEWLINE);
				}

				if (inFM) {
					_fm.append(str + FMLBDDWriter._NEWLINE);
				}

				// open brackets
				if (str.equals(FMLBDDWriter._BEGIN_BUILDER)) {
					inBuilder = true;
					_builder = new StringBuilder();
				}

				if (str.equals(FMLBDDWriter._BEGIN_FM)) {
					inFM = true;
					_fm = new StringBuilder();
				}

				if (str.equals(FMLBDDWriter._BEGIN_BDD)) {
					inBDD = true;
					_bdd = new StringBuilder();
				}

			}

			assert (!inBDD && !inFM && !inBuilder);
			hasBeenParsed = true;

		} catch (FileNotFoundException e) {
			FMLShell.getInstance().printError("Unable to load FML BDD " + e);
		} catch (IOException e) {
			FMLShell.getInstance().printError(
					"(IO) Unable to load FML BDD " + e);
		}

	}

	public Map<String, Integer> getMapBuilder() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String builder = getBuilderRepresentation();
		String[] keyValues = builder.split(FMLBDDWriter._COMMA);
		for (int i = 0; i < keyValues.length; i++) {
			String keyValue = keyValues[i];
			if (keyValue.isEmpty())
				continue;
			String[] kv = keyValue.split(FMLBDDWriter._ASSOCIATION);
			if (kv.length == 1)
				continue;
			assert (kv.length == 2);
			String value = kv[0];
			String key = kv[1];
			map.put(key.trim(), Integer.parseInt(value.trim()));
		}
		return map;
	}

	public Formula<String> getFormula() {
		return new Formula<String>(getBDD(), getMapBuilder().keySet(),
				getBDDBuilder());
	}

	public BDD getBDD() {

		BDDBuilder<String> lbuilder = getBDDBuilder();
		String bddRepresentation = getBDDRepresentation();
		BDD bdd = null;
		try {
			bdd = lbuilder.getFactory().load(
					new BufferedReader(new StringReader(bddRepresentation)));
		} catch (IOException e) {
			FMLShell.getInstance()
					.printError("(bdd) Unable to return BDD " + e);
		}
		return bdd;
	}

	public BDDBuilder<String> getBDDBuilder() {

		if (_BDDBuilder == null) {
			return BDDBuilderFactory.mkBuilder(getMapBuilder()); // new one
		} else
			return _BDDBuilder;
	}

}