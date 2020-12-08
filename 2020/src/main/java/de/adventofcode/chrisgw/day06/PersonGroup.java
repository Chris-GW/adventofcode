package de.adventofcode.chrisgw.day06;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


@Data
public class PersonGroup {

    private final List<Person> persons = new ArrayList<>();


    public void addPerson(Person person) {
        person.setPersonGroup(this);
        persons.add(person);
    }

    public boolean isEmpty() {
        return persons.isEmpty();
    }

    public int size() {
        return persons.size();
    }


    public long affirmedQuestions() {
        return affirmedAnswers().distinct().count();
    }


    public long everyoneAffirmedQuestionCount() {
        Map<String, Long> affirmedAnswerCount = affirmedAnswers().collect(
                groupingBy(CustomsDeclarationFormAnswer::getQuestion, counting()));
        return affirmedAnswerCount.values().stream().filter(answerCount -> answerCount == this.size()).count();
    }

    private Stream<CustomsDeclarationFormAnswer> affirmedAnswers() {
        return persons.stream()
                .map(Person::getAnswers)
                .flatMap(Collection::stream)
                .filter(CustomsDeclarationFormAnswer::isAffirmed);
    }

}
