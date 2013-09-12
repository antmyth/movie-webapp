package utils;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import scala.collection.immutable.List;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IafdLoaderTest {

    @Test
    public void buildsCorrectTitleForQueryWithNoNumbers() throws Exception {
        String title = "Word1 Word2 ttt";
        String expected = "Word1+Word2+ttt";
        String actual = new IafdLoader().parseTitle(title);

        assertThat(actual, is(expected));
    }

    @Test
    public void resultListShouldHaveMultipleTitles() throws Exception {
        File file = new File("test/utils/multipleResults");
        String html = FileUtils.readFileToString(file);
        List<SearchResultItem> stringList = new IafdLoader().searchResultsIn(html);

        assertThat(stringList.size(),is(24));
        System.out.println("stringList = " + stringList);
    }
}
