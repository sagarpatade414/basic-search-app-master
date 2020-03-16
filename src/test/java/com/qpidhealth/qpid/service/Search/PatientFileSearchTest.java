package com.qpidhealth.qpid.service.Search;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PatientFileSearchTest {

	@Test
	public void testsearchPatients() {
		PatientFileSearch pfs = new PatientFileSearch();
		assertNotEquals(0, pfs.findAllPatients().size());
	}

	@Test
	public void testresource() {
		PatientFileSearch pfs = new PatientFileSearch();
		assertNotNull(pfs.resource("Mary_1"));
	}

}
