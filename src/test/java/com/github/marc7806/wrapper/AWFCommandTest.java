package com.github.marc7806.wrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;

import org.junit.jupiter.api.Test;

class AWFCommandTest {
    @Test
    public void shouldBuildCommandWithInputAndOutput() {
        //given
        File input = new File("awf-example-input.mp3");
        File output = new File("awf-example-ouput.json");

        //when
        AWFCommand command = AWFCommand.builder()
                .setInput(input)
                .setOutput(output)
                .build();

        //then
        assertTrue(command.getArguments().contains(input.getAbsolutePath()));
        assertTrue(command.getArguments().contains(output.getAbsolutePath()));
    }

    @Test
    public void shouldBuildFullCommand() {
        //given
        File input = new File("awf-example-input.mp3");
        File output = new File("awf-example-ouput.json");

        //when
        AWFCommand command = AWFCommand.builder()
                .setInput(input)
                .setOutput(output)
                .setBits(AWFBit.SIXTEEN)
                .setColorSchema(AWFColorSchema.AUDITION)
                .setAmplitudeScale("auto")
                .setHeight(100)
                .setWidth(200)
                .setStart(50)
                .addZoom(256)
                .hideAxisLabels()
                .splitChannels()
                .build();

        //then
        assertEquals(command.getArguments().size(), 20);
    }

    @Test
    public void shouldThrowNullPointerExceptionIfInputFileEmpty() {
        assertThrows(NullPointerException.class, () -> {
            AWFCommand command = AWFCommand.builder()
                    .build();
            command.getArguments();
        });
    }

    @Test
    public void shouldThrowNullPointerExceptionIfOutputFileEmpty() {
        assertThrows(NullPointerException.class, () -> {
            AWFCommand command = AWFCommand.builder()
                    .setInput(new File("awf-example-output.json"))
                    .build();
            command.getArguments();
        });
    }
}
