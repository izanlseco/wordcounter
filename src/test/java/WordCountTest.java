import com.adaptavist.wordcount.WordCount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
class WordCountTest {

    private String[] argument = new String[1];

    @BeforeEach
    void setUp() {
        // get the filepath to the txt file inside the resources test folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("lorem-ipsum.txt")).getFile());
        argument[0] = file.getAbsolutePath();
    }

    @Test
    void assertThatGetAndSplitByWordsIsNotNullAndIsSplitted() throws IOException {
        Assert.notNull(WordCount.getAndSplitByWords(argument), "The file path has to be correct.");
    }

    @Test
    void assertThatIsGroupedByConcurrences() {

    }

    @Test
    void assertThatIsSortedAndPrinted() {

    }
}
