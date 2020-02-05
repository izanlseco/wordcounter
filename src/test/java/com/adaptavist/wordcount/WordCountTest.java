package com.adaptavist.wordcount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

public class WordCountTest {

    //Test data
    private String[] arguments;
    private Stream<String> stream;
    List<String> referentList = new ArrayList<>();

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
