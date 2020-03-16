package com.qpidhealth.qpid.service.Search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import com.qpidhealth.qpid.model.Patient;

public class PatientFileSearch implements PatientSearch {	
	
	/**
	 * @param query
	 * @Returns List of patient records
	 *
	 */
	public List<Patient> searchPatients(String query) {

		List<Patient> patients = findAllPatients();
        List<Patient> res = new ArrayList<>();
        for (Patient p: patients){
            List<String> docs = new ArrayList<>();
            for (String doc : p.getDocuments()){
                if(doc.replace(":::","").toLowerCase().contains(query.toLowerCase()))
                    docs.add(doc);
            }
            if(!docs.isEmpty()) {
                Patient patient = new Patient();
                patient.setId(p.getId());
                patient.setName(p.getName());
                patient.setDocuments(docs);
                res.add(patient);
            }
        }
        return res;

	}
	
	/**
	 * 
	 * @return list of patients
	 */
	public List<Patient> findAllPatients() {
        Patient p1 = new Patient();
        p1.setId(1000000L);
        p1.setName("Mary TestPerson");
        List<String> docs = new ArrayList<String>();
        docs.add("Patient Note:::6/20/2010:::" + resource("Mary_1"));
        docs.add("Patient Note:::6/20/2010:::" + resource("Mary_2"));
        p1.setDocuments(docs);

        Patient p2 = new Patient();
        p2.setId(1000001L);
        p2.setName("Joe TestPerson");
        List<String> docs2 = new ArrayList<String>();
        docs2.add("Clinical Note:::4/6/2010:::" + resource("Joe_1"));
        docs2.add("Summary:::7/2/2010:::" + resource("Joe_2"));
        p2.setDocuments(docs2);

        Patient p3 = new Patient();
        p3.setId(1000002L);
        p3.setName("Sam TestPerson");
        List<String> docs3 = new ArrayList<String>();
        docs3.add("Patient Note:::8/3/2012:::" + resource("Sam_1"));
        p3.setDocuments(docs3);

        List<Patient> results = new ArrayList<Patient>();
        results.add(p1);
        results.add(p2);
        results.add(p3);

        return results;
    }
	
	/**
	 * 
	 * @param fileName
	 * @return String
	 */
    public String resource(String fileName) {
        try {
            return IOUtils.toString(getClass().getClassLoader().getResourceAsStream("documents/"+fileName+".txt"),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to retrieve resource "+fileName;
        }
    }
}
