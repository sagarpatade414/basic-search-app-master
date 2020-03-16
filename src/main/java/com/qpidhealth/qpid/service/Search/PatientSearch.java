package com.qpidhealth.qpid.service.Search;

import java.util.List;
import com.qpidhealth.qpid.model.Patient;

public interface PatientSearch {
	public List<Patient> searchPatients(String query);
}