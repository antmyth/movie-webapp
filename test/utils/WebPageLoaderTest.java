package utils;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import scala.collection.immutable.List;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class WebPageLoaderTest {
//    @Test
//    public void vvvv() throws Exception {
//        String content = new WebPageLoader().content();
//        System.out.println("content = " + content);
//
//        assertNotNull(content);
//    }


    @Test
    public void buildsCorrectTitleForQueryWithNoNumbers() throws Exception {
        String title = "Word1 Word2 ttt";
        String expected = "Word1+Word2+ttt";
        String actual = WebPageLoader.parseTitle(title);

        assertThat(actual, is(expected));
    }

    @Ignore
    @Test
    public void infoFor() throws Exception {
//        String info = new WebPageLoader().infoFor("");
//        System.out.println("info = " + info);
        assertTrue(true);
    }

    @Test
    public void resultListShouldHaveMultipleTitles() throws Exception {
        File file = new File("test/utils/multipleResults");
        String html = FileUtils.readFileToString(file);
        List<SearchResultItem> stringList = new WebPageLoader().searchResultsIn(html);

        assertThat(stringList.size(),is(24));
        System.out.println("stringList = " + stringList);
    }
}
