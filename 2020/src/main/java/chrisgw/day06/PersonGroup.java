package chrisgw.day06;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


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


    public long affirmedQuestions() {
        return persons.stream()
                .map(Person::getAnswers)
                .flatMap(Collection::stream)
                .filter(CustomsDeclarationFormAnswer::isAffirmed)
                .distinct()
                .count();
    }

}
