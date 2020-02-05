package com.adaptavist.wordcount;

import org.junit.*;
import org.junit.rules.TestWatcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCountTest {

    //Test data
    private String[] arguments;
    private Stream<String> stream;
    private List<String> referentList = new ArrayList<>();

    @Before
    public void setup() {
        arguments = new String[1];
        stream = null;

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("lorem-ipsum.txt")).getFile());
        arguments[0] = file.getAbsolutePath();

        referentList.add("lorem");
        referentList.add("ipsum");
        referentList.add("dolor");
        referentList.add("sit");
        referentList.add("amet");
    }

    @Rule
    public TestWatcher watchman = new Log4jTestWatcher();

    @Test
    public void assertNotNullGetAndSplitByWords() throws IOException {
        stream = WordCount.getAndSplitByWords(arguments);

        Assert.assertNotNull(stream);
    }

    @Test
    public void assertSameGetAndSplitByWords() throws IOException {
        // obtain the list to test against the referent
        stream = WordCount.getAndSplitByWords(arguments);
        List<String> obtainedList = stream.collect(Collectors.toList());

        Assert.assertTrue(referentList.get(1).matches(obtainedList.get(1)));
    }
}
