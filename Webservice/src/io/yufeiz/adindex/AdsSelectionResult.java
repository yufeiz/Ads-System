package io.yufeiz.adindex;

import java.util.ArrayList;
import java.util.List;

public class AdsSelectionResult {
	private List<Ad> adsList;
	public AdsSelectionResult() {
		adsList = new ArrayList<Ad>();
	}
	public synchronized void add(List<Ad> _adsList) {
		if(_adsList != null) {
			for(Ad ad : _adsList) {
				adsList.add(ad);
			}
		}
	}
	public synchronized List<Ad> getAdsList() {
		return adsList;
	}
}
