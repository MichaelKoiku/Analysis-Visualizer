package dataFetchLayer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class WorldBankDataFetcher extends Fetcher {
	
	public WorldBankDataFetcher(String country, String indicator, String yearStart, String yearEnd) {
		super("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json", country, indicator,
				yearStart, yearEnd);
	}
	
	public String BuildURL() {
		return String.format(this.url, this.country, this.indicator, this.yearStart, this.yearEnd);
	}
	
	public Map<String, Double> GetData() {
		
		String urlFormatted = BuildURL();
		Map<String, Double> dataResult = new HashMap<String, Double>();
		
		try {
			URL url = new URL(urlFormatted);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				int size = jsonArray.size();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
				String year;
				double dataForYear;
				for (int i = 0; i < sizeOfResults; i++) {
					year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsString();
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						dataForYear = 0;
					else
						dataForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsDouble();
					dataResult.put(year, dataForYear);
					System.out.println(this.indicator + " for " + this.country + " in " + year + " is " + dataForYear);
				}
			}
			
		} catch (IOException e) {
			
		}
		
		return dataResult;
	}
}