package fundation.search.model;

import com.fundation.search.model.CustomFile;
import com.fundation.search.model.ResultFile;
import com.fundation.search.model.Search;
import com.fundation.search.model.SearcherCriteria;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static junit.framework.TestCase.assertEquals;

public class TestModel {
    SearcherCriteria criteria;
    Search search;
    String currentPath;
    ArrayList<CustomFile> actual;

    @Before
    public void setUp() {
        criteria = new SearcherCriteria();
        search = new Search();
        currentPath = Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\test\\resources\\";
        actual = new ArrayList<CustomFile>();
    }

    /**
     * Method used to test the search method with basic paramaters: path, name, extension
     * File test.txt required
     */
    @Test
    public void searchFile_IfSendBasicParamters() {
        criteria.setPath(currentPath);
        criteria.setFileName("test");
        criteria.setExt("txt");
        criteria.setSize("0");
        try {
            actual = search.searchFile(criteria);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals("test", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPath, actual.get(0).getPath());
        actual.clear();
    }

    /**
     * Method used to test the search method with all the parameters: path, name, extension,
     * size, owner and content
     * File test2.txt required
     */
    @Test
    public void searchFile_IfSendAllParameters() {
        Path path = Paths.get(currentPath + "test2.txt");
        String[] ownerFile = {"", ""};
        criteria.setPath(currentPath);
        criteria.setFileName("test2");
        criteria.setExt("txt");
        criteria.setSize("1024");
        criteria.setSizeScale("bytes");
        criteria.setOperator("==");
        criteria.setContent("test2");
        try {
            UserPrincipal owner = Files.getOwner(path);
            ownerFile = owner.toString().split(" ");
            criteria.setOwner(ownerFile[0]);
            actual = search.searchFile(criteria);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals("test2", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPath, actual.get(0).getPath());
        assertEquals("1 KB", actual.get(0).getSize());
        assertEquals(ownerFile[0], actual.get(0).getOwner());
        actual.clear();
    }

    /**
     * Method used to test the search method with creation date parameters
     */
    @Test
    public void searchFile_IfSendCreationDate() {
        File test3 = new File(currentPath + "test3.txt");
        Calendar today = Calendar.getInstance();
        today.clear(Calendar.HOUR);
        today.clear(Calendar.MINUTE);
        today.clear(Calendar.SECOND);
        criteria.setPath(currentPath);
        criteria.setFileName("test3.txt");
        criteria.setDateType("Creation Date");
        criteria.setStartDate(today.getTime());
        criteria.setEndDate(today.getTime());
        criteria.setSize("0");
        try {
            test3.createNewFile();
            actual = search.searchFile(criteria);
            test3.delete();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals("test3", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPath, actual.get(0).getPath());
        actual.clear();
    }

    /**
     * Method used to test the search method with modified date parameters
     */
    @Test
    public void searchFile_IfSendModifiedDate() {
        File test4 = new File(currentPath + "test4.txt");
        Calendar today = Calendar.getInstance();
        today.clear(Calendar.HOUR);
        today.clear(Calendar.MINUTE);
        today.clear(Calendar.SECOND);
        criteria.setPath(currentPath);
        criteria.setFileName("test4.txt");
        criteria.setDateType("Modified Date");
        criteria.setStartDate(today.getTime());
        criteria.setEndDate(today.getTime());
        criteria.setSize("0");
        try {
            test4.createNewFile();
            actual = search.searchFile(criteria);
            test4.delete();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals("test4", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPath, actual.get(0).getPath());
        actual.clear();
    }

    /**
     * Method used to test the search method with accessed date parameters
     */
    @Test
    public void searchFile_IfSendAccessedDate() {
        File test5 = new File(currentPath + "test5.txt");
        Calendar today = Calendar.getInstance();
        today.clear(Calendar.HOUR);
        today.clear(Calendar.MINUTE);
        today.clear(Calendar.SECOND);
        criteria.setPath(currentPath);
        criteria.setFileName("test5.txt");
        criteria.setDateType("Accessed Date");
        criteria.setStartDate(today.getTime());
        criteria.setEndDate(today.getTime());
        criteria.setSize("0");
        try {
            test5.createNewFile();
            actual = search.searchFile(criteria);
            test5.delete();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals("test5", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPath, actual.get(0).getPath());
        actual.clear();
    }

    /**
     * Method used to test the search method with Hidden checked
     * File test6.txt required
     */
    @Test
    public void searchFile_IfSendHidden() {
        criteria.setPath(currentPath);
        criteria.setIsHidden(true);
        criteria.setSize("0");
        try {
            actual = search.searchFile(criteria);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals("test6", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPath, actual.get(0).getPath());
        actual.clear();
    }

    /**
     * Method used to test the search method with Readonly checked
     * File test7.txt required
     */
    @Test
    public void searchFile_IfSendReadOnly() {
        criteria.setPath(currentPath);
        criteria.setIsReadOnly(true);
        criteria.setSize("0");
        try {
            actual = search.searchFile(criteria);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals("test7", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPath, actual.get(0).getPath());
        actual.clear();
    }

    /**
     * Method used to test the search method with Directory checked
     * Directory test8 required
     */
    @Test
    public void searchFile_IfSendDirectory() {
        File directory = new File(currentPath + "test8");
        directory.mkdirs();
        criteria.setPath(currentPath);
        criteria.setIsDirectory(true);
        criteria.setSize("0");
        try {
            actual = search.searchFile(criteria);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
        assertEquals(currentPath + "test8", actual.get(0).getPath());
        actual.clear();
    }
}
