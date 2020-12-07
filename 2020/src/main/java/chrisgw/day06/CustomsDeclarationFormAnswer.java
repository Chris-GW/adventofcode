package chrisgw.day06;

import lombok.Data;


@Data
public class CustomsDeclarationFormAnswer {

    public static final int QUESTION_COUNT = 26;

    private final String question;
    private final boolean affirmed;

}
