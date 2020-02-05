package com.adaptavist.wordcount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordCountTest {

    @Rule
    public TestWatcher watchman = new Log4jTestWatcher();

    //Test data
    private String[] arguments;
    private List<String> mockedList = new ArrayList<>();
    private List<String> referenceList = new ArrayList<>();

    @Before
    public void setup() {
        arguments = new String[1];
        mockedList = Arrays.asList(" ", " ", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam lacinia hendrerit aliquet. Vestibulum nisl leo, vestibulum vel augue ac, hendrerit malesuada dui. Duis scelerisque felis lobortis nisl eleifend interdum. Phasellus tempus augue quis eros congue, vel lobortis ipsum ultrices. Nunc quis feugiat leo. Mauris porta egestas tortor, vel blandit justo mattis sit amet. Ut faucibus nibh sit amet diam cursus ornare. Duis orci sapien, facilisis vitae molestie ac, luctus imperdiet libero. Aenean vel libero porttitor, pulvinar elit ac, lacinia lectus. Integer quis eleifend diam. Aliquam sollicitudin, erat nec vehicula pharetra, nulla sem pellentesque justo, at hendrerit ex sem in neque. Sed vitae mauris efficitur ligula bibendum lacinia in nec diam.");
        referenceList = Arrays.asList("lorem", "ipsum", "dolor", "sit", "amet", "lorem", "lorem", "lorem", "sit", "sit", "consectetur", "adipiscing", "elit", "aliquam", "vel", "leo");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("lorem-ipsum.txt")).getFile());

        arguments[0] = file.getAbsolutePath();
    }

    @Test
    public void assertGetFile() throws IOException {
        List<String> obtainedList = WordCount.getFile(arguments);

        Assert.assertNotNull(obtainedList);
    }

    @Test
    public void assertSplitByWord() {
        List<String> filteredList = WordCount.splitByWord(mockedList);

        Assert.assertNotNull(filteredList);
        Assert.assertTrue(referenceList.get(1).matches(filteredList.get(1)));
    }

    @Test
    public void assertGroupByConcurrences() {
        Map<String, Integer> concurrentMap = WordCount.groupByConcurrences(referenceList);

        Assert.assertNotNull(concurrentMap);
        Assert.assertEquals(4, (int) concurrentMap.get("lorem"));
        Assert.assertEquals(3, (int) concurrentMap.get("sit"));
    }
}
