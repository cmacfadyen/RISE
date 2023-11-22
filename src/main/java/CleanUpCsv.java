package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CleanUpCsv {

	public static void main(String[] args) {
		List<String> stuff = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(
				new FileReader("/Users/charliemacfadyen/RISE/rise/src/results.csv"))) {
			String line;

			// Read header (if it exists)
			// Uncomment the next line if your CSV file has a header
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				System.out.println(data[0]);

				if (!data[0].contains("Student 0")) {
					if(data[0].length()==10) {
						StringBuilder name = new StringBuilder(data[0]);
						name.insert(8, "00");
						data[0] = name.toString();
						line = String.join(",", data);
					}
					else if(data[0].length()==11) {
						StringBuilder name = new StringBuilder(data[0]);
						name.insert(8, '0');
						data[0] = name.toString();
						line = String.join(",", data);
					}
					
				}

				stuff.add(line);
			}
		}

	catch(IOException e){
            e.printStackTrace();
        }
	try(
	BufferedWriter bw = new BufferedWriter(new FileWriter("newResults.csv"))){
		for (String l: stuff) {
			bw.write(l);
			bw.newLine();

		}
	}
catch(Exception e) {
	e.printStackTrace();
}
}

}
