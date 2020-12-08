package de.adventofcode.chrisgw.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;


/**
 * https://adventofcode.com/2020/day/6
 */
public class AdventOfCodeDay06 {

    private final List<PersonGroup> personGroups;


    private AdventOfCodeDay06(List<PersonGroup> personGroups) {
        this.personGroups = requireNonNull(personGroups);
    }

    public static AdventOfCodeDay06 parsePersonGroupCustomsDeclarationForms(Readable personGroupForms) {
        try (Scanner sc = new Scanner(personGroupForms)) {
            List<PersonGroup> personGroups = new ArrayList<>();
            PersonGroup currentPersonGroup = new PersonGroup();
            for (int i = 0; sc.hasNextLine(); i++) {
                String line = sc.nextLine();
                if (line.isBlank()) {
                    personGroups.add(currentPersonGroup);
                    currentPersonGroup = new PersonGroup();
                } else {
                    Person person = new Person(i);
                    person.getAnswers().addAll(readAffirmedAnswers(line));
                    currentPersonGroup.addPerson(person);
                }
            }
            if (!currentPersonGroup.isEmpty()) {
                personGroups.add(currentPersonGroup);
            }
            return new AdventOfCodeDay06(personGroups);
        } catch (Exception e) {
            throw new RuntimeException("could not parse", e);
        }
    }

    private static List<CustomsDeclarationFormAnswer> readAffirmedAnswers(String line) {
        return IntStream.range(0, line.length())
                .mapToObj(i -> String.valueOf(line.charAt(i)))
                .map(question -> new CustomsDeclarationFormAnswer(question, true))
                .collect(Collectors.toList());
    }


    public long countPersonGroupAffirmedQuestions() {
        return personGroups.stream().mapToLong(PersonGroup::affirmedQuestions).sum();
    }


    // part 02

    public long countEveryoneAffirmedQuestions() {
        return personGroups.stream().mapToLong(PersonGroup::everyoneAffirmedQuestionCount).sum();
    }

}
