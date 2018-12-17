package de.adventofcode.chrisgw.day16;

import de.adventofcode.chrisgw.TestUtils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static de.adventofcode.chrisgw.day16.DeviceOperationSample.REGISTER_VALUE_LINE_PATTERN;
import static org.junit.Assert.*;


public class ChronalClassificationTest {

    @Test
    public void parseNextOperationSampleEntry_example_01() {
        String operationSampleStr = "" //
                + "Before: [3, 2, 1, 1]\n" //
                + "9 2 1 2\n" //
                + "After:  [3, 2, 2, 1]"; //
        DeviceOperationSample expectedOperationSample = DeviceOperationSample.builder()
                .registerValuesBefore(new int[] { 3, 2, 1, 1 })
                .opCode(9)
                .valueA(2)
                .valueB(1)
                .outputC(2)
                .registerValuesAfter(new int[] { 3, 2, 2, 1 })
                .build();

        DeviceOperationSample operationSample = DeviceOperationSample.parseOperationSampleEntry(operationSampleStr);
        assertEquals("operationSample for:\n" + operationSampleStr, expectedOperationSample, operationSample);
    }


    @Test
    public void mapRegisterInstructionToOpCodes_example_01() {
        String operationSampleStr = "" //
                + "Before: [3, 2, 1, 1]\n" //
                + "9 2 1 2\n" //
                + "After:  [3, 2, 2, 1]"; //
        DeviceOperationSample operationSample = DeviceOperationSample.parseOperationSampleEntry(operationSampleStr);

        Set<String> expectedRegisterInstructionNames = new HashSet<>();
        expectedRegisterInstructionNames.add("mulr");
        expectedRegisterInstructionNames.add("addi");
        expectedRegisterInstructionNames.add("seti");

        ChronalClassification chronalClassification = new ChronalClassification();
        Map<DeviceOperationSample, Set<DeviceRegisterInstruction>> opCodeToRegisterInstructions = chronalClassification.mapOpCodeToRegisterInstructions(
                Collections.singletonList(operationSample));

        Optional<Set<DeviceRegisterInstruction>> firstSample = opCodeToRegisterInstructions.values()
                .stream()
                .findFirst();
        assertTrue("first OperationSample is present", firstSample.isPresent());

        Set<String> registerInstrictionNames = firstSample.get()
                .stream()
                .map(DeviceRegisterInstruction::getOperationName)
                .collect(Collectors.toSet());
        assertEquals("registerInstrictionNames", expectedRegisterInstructionNames, registerInstrictionNames);
    }


    @Test
    public void mapRegisterInstructionToOpCodes_myPuzzleInput() throws Exception {
        Path puzzleInputFile = TestUtils.getResourcePath("/day16/myPuzzleInput.txt");
        long expectedOperationSamplesWithThreeOrMoreOpCodes = 591;

        List<DeviceOperationSample> operationSamples = parseOperationSampleEntries(puzzleInputFile);

        ChronalClassification chronalClassification = new ChronalClassification();
        Map<DeviceOperationSample, Set<DeviceRegisterInstruction>> opCodeToRegisterInstructions = //
                chronalClassification.mapOpCodeToRegisterInstructions(operationSamples);

        long operationSamplesWithThreeOrMoreOpCodes = opCodeToRegisterInstructions.values()
                .stream()
                .mapToInt(Collection::size)
                .filter(size -> size >= 3)
                .count();
        System.out.println(operationSamplesWithThreeOrMoreOpCodes);
        assertEquals("operationSamplesWithThreeOrMoreOpCodes", expectedOperationSamplesWithThreeOrMoreOpCodes,
                operationSamplesWithThreeOrMoreOpCodes);
    }

    private List<DeviceOperationSample> parseOperationSampleEntries(Path puzzleInputFile) throws IOException {
        List<DeviceOperationSample> operationSamples = new ArrayList<>();
        List<String> lines = Files.readAllLines(puzzleInputFile);
        for (int i = 0; i < lines.size() - 4; i += 4) {
            String firstLine = lines.get(i);
            if (!REGISTER_VALUE_LINE_PATTERN.matcher(firstLine).matches()) {
                break;
            }
            String secondLine = lines.get(i + 1);
            String thirdLine = lines.get(i + 2);
            String operationSampleLines = String.join("\n", firstLine, secondLine, thirdLine);

            DeviceOperationSample operationSample = DeviceOperationSample.parseOperationSampleEntry(
                    operationSampleLines);
            operationSamples.add(operationSample);
        }
        return operationSamples;
    }

}
