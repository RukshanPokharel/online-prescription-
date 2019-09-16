/**
 * 
 */
package com.hospital.prescription.analytics;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.prescription.Treatment.Treatment;
import com.hospital.prescription.Treatment.TreatmentRepository;
import com.hospital.prescription.patient.Patient;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

	@Autowired
	private TreatmentRepository treatmentRepository;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Object[]> readAllPatients(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		List<Object[]> treatments = treatmentRepository.readTreatment();
		Map<String, Map<String, Integer>> data = new HashMap<>();

		for (Object[] obj : treatments) {
			String disease = String.valueOf(obj[0]);
			if (data.containsKey(disease)) {
				data.get(disease).put(String.valueOf(obj[1]), Integer.parseInt(String.valueOf(obj[2])));
			} else {
				Map<String, Integer> valueMap = new HashMap<>();
				valueMap.put(String.valueOf(obj[1]), Integer.parseInt(String.valueOf(obj[2])));
				data.put(disease, valueMap);
			}
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date startDate = null;
		Date endDate = null;
		try {
			endDate = format.parse(toDate);
			startDate = format.parse(fromDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> dates = getDatesBetweenReportingPeriod(startDate, endDate);
		List<Object[]> gData = new ArrayList<>();

		Set<String> diseases = data.keySet();
		int rowSize = 1 + diseases.size();

		int i = 0;
		Object[] columns = new Object[rowSize];
		columns[i++] = "Months";
		for(String disease: data.keySet()){
			columns[i++] = disease;
		}		
		gData.add(columns);
		
		for (String date : dates) {
			int j = 0;
			Object[] row = new Object[rowSize];
			
			row[j++] = date; 
			for(String disease: data.keySet()){
				row[j++] = getData(date, data.get(disease));
			}
			gData.add(row);
		}
		
		return gData;
	}

	private int getData(String date, Map<String, Integer> monthlyValue) {
		if (monthlyValue.containsKey(date)) {
			return monthlyValue.get(date);
		} else {
			return 0;
		}
	}

	public List<String> getDatesBetweenReportingPeriod(Date startDate, Date endDate) {
		DateFormat formatter = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);

		List<String> datesInRange = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);

		while (calendar.before(endCalendar)) {
			Date result = calendar.getTime();
			datesInRange.add(formatter.format(result));
			calendar.add(Calendar.MONTH, 1);
		}
		return datesInRange;
	}
}
