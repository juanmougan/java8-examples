package com.github.juanmougan;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by juanma on 20/7/17.
 */
public class SortByTwoFields {

    @Test
    public void testSortByTwoFields() {
        // Given
        List<Person> peopleAndJobs = new ArrayList<>();
        peopleAndJobs.add(new Person("John", "Software Engineer"));
        peopleAndJobs.add(new Person("Mary", "Secretary"));
        peopleAndJobs.add(new Person("Paul", "Accountant"));
        peopleAndJobs.add(new Person("Steve", "Janitor"));
        peopleAndJobs.add(new Person("Ann", "Business Analyst"));
        peopleAndJobs.add(new Person("John", "CEO"));
        peopleAndJobs.add(new Person("Mary", "Accountant"));
        peopleAndJobs.add(new Person("Rose", "Software Engineer"));
        peopleAndJobs.add(new Person("Paul", "Software Engineer"));
        peopleAndJobs.add(new Person("Mary", "HR"));

        // When
        List<Person> sortedList = this.sortByNameThenJob(peopleAndJobs);

        // Then
        assertEquals(new Person("Ann", "Business Analyst"), sortedList.get(0));
        assertEquals(new Person("John", "CEO"), sortedList.get(1));
        assertEquals(new Person("John", "Software Engineer"), sortedList.get(2));
        assertEquals(new Person("Mary", "Accountant"), sortedList.get(3));
        assertEquals(new Person("Mary", "HR"), sortedList.get(4));
        assertEquals(new Person("Mary", "Secretary"), sortedList.get(5));
        assertEquals(new Person("Paul", "Accountant"), sortedList.get(6));
        assertEquals(new Person("Paul", "Software Engineer"), sortedList.get(7));
        assertEquals(new Person("Rose", "Software Engineer"), sortedList.get(8));
        assertEquals(new Person("Steve", "Janitor"), sortedList.get(9));
    }

    class Person {
        String name;
        String job;

        public Person(String name, String job) {
            this.name = name;
            this.job = job;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            if (name != null ? !name.equals(person.name) : person.name != null) return false;
            return job != null ? job.equals(person.job) : person.job == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (job != null ? job.hashCode() : 0);
            return result;
        }
    }

    private List<Person> sortByNameThenJob(List<Person> peopleAndJobs) {
        List<String[]> sortedList;
        Comparator<Person> comparator = Comparator.comparing(person -> person.name);
        comparator = comparator.thenComparing(Comparator.comparing(person -> person.job));

        // Sort the stream:
        Stream<Person> personStream = peopleAndJobs.stream().sorted(comparator);
        return personStream.collect(Collectors.toList());
    }

}
