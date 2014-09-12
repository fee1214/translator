package impl;

import java.util.HashMap;
import java.util.Map;

import exception.NotImplementedException;
import exception.UnknownValueException;

public class GlobalConverter {

	private ValueFetcher fetcher;
	private Map<Keyword, Object> values;

	public GlobalConverter(ValueFetcher fetcher) {
		this.fetcher = fetcher;
		this.values = new HashMap<Keyword, Object>();
		startConversion();
	}

	private void startConversion() {
		for(Keyword kw : fetcher.getKeywords()){
			String input = fetcher.getValue(kw);
			try {
				values.put(kw, kw.converter().getOutput(input, values));
			} catch (UnknownValueException | NotImplementedException e) {
				e.printStackTrace();
			}
		}
	}

	public Object get(Keyword keyword) {
		return values.get(keyword);
	}

}