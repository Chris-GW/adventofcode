package chrisgw.day06;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static chrisgw.day06.CustomsDeclarationFormAnswer.QUESTION_COUNT;


@Data
public class Person {

    private final int id;
    private final List<CustomsDeclarationFormAnswer> answers = new ArrayList<>(QUESTION_COUNT);
    private PersonGroup personGroup;


    public void addAffirmedAnswerFor(String question) {
        answers.add(new CustomsDeclarationFormAnswer(question, true));
    }

}
