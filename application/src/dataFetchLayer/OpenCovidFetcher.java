package dataFetchLayer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OpenCovidFetcher extends Fetcher{

    public OpenCovidFetcher(String country, String yearStart, String yearEnd) {
        super("https://api.opencovid.ca/summary?geo=%s&after=%s&before=%s&fill=true&version=true&pt_names=short&hr_names=hruid&fmt=json", country, yearStart, yearEnd);
    }

    public String BuildURL() {
        return String.format(this.url, this.country.toLowerCase(), this.yearStart, this.yearEnd);
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
				JsonParser parse = new JsonParser();
				JsonObject data_obj = (JsonObject) parse.parse(inline);
				JsonArray jsonArray = (JsonArray) data_obj.get("data");

				int sizeOfResults = jsonArray.size();
				String year;
				double dataForYear = 0;
				for (int i = 0; i < sizeOfResults; i++) {
					year = jsonArray.get(i).getAsJsonObject().get("date").getAsString();
					if (jsonArray.get(i).getAsJsonObject().get("date").isJsonNull()) {
						dataForYear += 0;
					} else {
						// get the year only from the date
						dataForYear += jsonArray.get(i).getAsJsonObject().get("cases_daily").getAsDouble();
					}
					dataResult.put(year.substring(0, 4), dataForYear);
					System.out.println(year + " " + dataForYear); 
				}
				System.out.println("TOTAL COVID CASES: " + dataForYear);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataResult;
    }
    
}
