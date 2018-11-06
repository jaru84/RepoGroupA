package fundation.search.model;

import com.fundation.search.model.CustomFile;
import com.fundation.search.model.Search;
import com.fundation.search.model.SearcherCriteria;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class TestModel {
    /**
     * Method used to test the search method
     */
    @Test
    public void Model_searchFile(){
        SearcherCriteria criteria = new SearcherCriteria();
        criteria.setPath(".\\src\\test\\resources");
        criteria.setFileName("test");
        criteria.setExt("txt");
        criteria.setSize("0");
        criteria.setSizeScale("KB");
        criteria.setOperator(">");
        Search search = new Search();
        String currentPsth = Paths.get(".").toAbsolutePath().normalize().toString();
        System.out.println(currentPsth);
        ArrayList<CustomFile> actual = new ArrayList<CustomFile>();
        try {
            actual = search.searchFile(criteria);
        }
        catch(IOException e){

        }
        assertEquals("test", actual.get(0).getFileName());
        assertEquals("txt", actual.get(0).getExt());
        assertEquals(currentPsth + "\\src\\test\\resources\\", actual.get(0).getPath());
    }
}
