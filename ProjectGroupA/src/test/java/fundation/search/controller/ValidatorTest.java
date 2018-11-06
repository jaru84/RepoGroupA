package fundation.search.controller;

import static org.junit.Assert.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.fundation.search.controller.CustomSearchException;
import com.fundation.search.controller.Validator;
import com.fundation.search.model.SearcherCriteria;

public class ValidatorTest {
	
	Validator val = new Validator();
	SearcherCriteria criteria = new SearcherCriteria();
	
	@Test
	public void validatePath_ThrowsException_IfPathIsEmpty() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("The path is a required field.", exception.getMessage());
	}
	
	@Test
	public void validatePath_ThrowsException__IfPathNotExists() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("x:\\PathNotExistent");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("The path inserted does not exist.", exception.getMessage());
	}
	
	@Test
	public void validatePath_ThrowsException_IfIsNotDirectory() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("C:\\Users\\jacqueline rosales\\Documents\\test.txt");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("The path inserted is not valid directory.", exception.getMessage());
	}

	@Test
	public void validateOwner_ThrowsException_IfOwnerNotHaveCorrectFormat() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("DomainUser");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Please introduce an account value in Owner field with following format: domain\\user.", exception.getMessage());
	}
	
	@Test
	public void validateFileName_ThrowsException_IfFileNameLengthLongerThan100Chars() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("LongFileName123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Your file name inserted exceeds the limit of letters allowed 100", exception.getMessage());
	}
	
	@Test
	public void validateFileName_ThrowsException_IfFileNameHasSymbolsNotAllowed() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("FileNameWithSymbols\\/:?\"<>");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Your file name can't contain any of following characters: \\/:?\"<>", exception.getMessage());
	}
	
	@Test
	public void validateExtension_ThrowsException_IfExtensionHasSymbolsNotAllowed() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("");
			criteria.setExt("txt\\/:?\"<>");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Your extension can't contain any of following characters: \\/:?\"<>", exception.getMessage());
	}
	
	@Test
	public void validateSize_ThrowsException_IfSizeValueIsNotNumber() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("");
			criteria.setExt("");
			criteria.setSize("2.5");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("You only can insert integer numbers on size field.", exception.getMessage());
	}
	
	@Test
	public void validateDates_ThrowsException_IfStartDateIsAfterEndDate() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("");
			criteria.setExt("");
			criteria.setSize("");
			criteria.setStartDate(new Date(2019, 11, 21));
			criteria.setEndDate(new Date(2018, 11, 06));
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("The Start Date selected could not be after End Date.", exception.getMessage());
	}
	
	@Test
	public void validateDates_ThrowsException_IfStartDateIsAfterCurrentDate() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		Date currDate = criteria.setTimeCustom(new Date(), 23, 59, 59);
		DateFormat dateF = new SimpleDateFormat("EEEE, MMMM d, yyyy");
		
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("");
			criteria.setExt("");
			criteria.setSize("");
			criteria.setStartDate(new Date(2018, 12, 30));
			criteria.setEndDate(new Date(2019, 01, 01));
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("The Start Date selected could not be after your Current Date: " + dateF.format(currDate), exception.getMessage());
	}
	
	@Test
	public void validateDateType_ThrowsException_IfDatesWereSelectedButNotDateType() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
				
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("");
			criteria.setExt("");
			criteria.setSize("");
			criteria.setStartDate(new Date(2018, 11, 01));
			criteria.setEndDate(new Date(2018, 11, 06));
			criteria.setDateType("< Select a Value >");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Please select some value from drop-down list different to: <Select a value> and ensure that you have selected valid dates in the interval.", exception.getMessage());
	}
	
	@Test
	public void validateDateType_ThrowsException_IfDateTypeWasSelectedButNotDates() throws CustomSearchException, IOException {
		CustomSearchException exception = null;
		
		try {
			criteria.setPath("c:\\");
			criteria.setOwner("");
			criteria.setFileName("");
			criteria.setExt("");
			criteria.setSize("");
			criteria.setStartDate(null);
			criteria.setEndDate(null);
			criteria.setDateType("Creation Date");
			val.validate(criteria);
		} catch (CustomSearchException e) {
			exception = e;
		}
		assertNotNull(exception);
		assertEquals("Please select valid dates in the interval: between - and.", exception.getMessage());
	}
}
