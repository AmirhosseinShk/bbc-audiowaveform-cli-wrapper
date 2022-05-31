package com.github.marc7806.wrapper;

import static com.google.common.hash.Hashing.sha256;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import com.google.common.io.Files;
import org.junit.jupiter.api.Test;

class BBCAudioWaveformTest {

    final String audioWavePath = "D:/Work/audiowaveform.exe";

    @Test
    void shouldSuccessfullyRunJSON_B_8_Sample() throws IOException {
        //given
        File input = new File(getClass().getClassLoader().getResource("audio/sample_01.mp3").getFile());
        File output = File.createTempFile("awf-sample_01_summary", ".json");
        output.deleteOnExit();
        BBCAudioWaveform bbcAudioWaveform = new BBCAudioWaveform(audioWavePath);
        AWFCommand command = AWFCommand.builder()
                .setInput(input)
                .setOutput(output)
                .setBits(AWFBit.EIGHT)
                .build();

        Map<String, String> result = bbcAudioWaveform.run(command);
        //when
        boolean success = Boolean.parseBoolean(result.get("status"));

        Path resourceDirectory = Paths.get("src","test","resources","json/sample_01_B_8.json");
        java.nio.file.Files.copy(output.toPath(), resourceDirectory,
                StandardCopyOption.REPLACE_EXISTING);
        //then
        File expected = Paths.get("src","test","resources","json/sample_01_B_8.json").toFile();

        assertTrue(success);
        assertEquals(Files.asByteSource(expected).hash(sha256()), Files.asByteSource(output).hash(sha256()));
    }

    @Test
    void shouldSuccessfullyRunJSON_B_8_Z_2048_Sample() throws IOException {
        //given
        File input = new File(getClass().getClassLoader().getResource("audio/sample_01.mp3").getFile());
        File output = File.createTempFile("awf-sample_01_summary", ".json");
        output.deleteOnExit();
        BBCAudioWaveform bbcAudioWaveform = new BBCAudioWaveform(audioWavePath);
        AWFCommand command = AWFCommand.builder()
                .setInput(input)
                .setOutput(output)
                .setBits(AWFBit.EIGHT)
                .addZoom(2048)
                .build();

        //when
        boolean success = Boolean.parseBoolean(bbcAudioWaveform.run(command).get("status"));

        Path resourceDirectory = Paths.get("src","test","resources","json/sample_01_B_8_Z_2048.json");
        java.nio.file.Files.copy(output.toPath(), resourceDirectory,
                StandardCopyOption.REPLACE_EXISTING);
        //then
        File expected = Paths.get("src","test","resources","json/sample_01_B_8_Z_2048.json").toFile();
        assertTrue(success);
        assertEquals(Files.asByteSource(expected).hash(sha256()), Files.asByteSource(output).hash(sha256()));
    }

    @Test
    void shouldSuccessfullyRunJSON_B_16_Sample() throws IOException {
        //given
        File input = new File(getClass().getClassLoader().getResource("audio/sample_01.mp3").getFile());
        File output = File.createTempFile("awf-sample_01_summary", ".json");
        output.deleteOnExit();
        BBCAudioWaveform bbcAudioWaveform = new BBCAudioWaveform(audioWavePath);
        AWFCommand command = AWFCommand.builder()
                .setInput(input)
                .setOutput(output)
                .setBits(AWFBit.SIXTEEN)
                .build();

        //when
        boolean success = Boolean.parseBoolean(bbcAudioWaveform.run(command).get("status"));

        Path resourceDirectory = Paths.get("src","test","resources","json/sample_01_B_16.json");
        java.nio.file.Files.copy(output.toPath(), resourceDirectory,
                StandardCopyOption.REPLACE_EXISTING);
        //then
        File expected = Paths.get("src","test","resources","json/sample_01_B_16.json").toFile();

        assertTrue(success);
        assertEquals(Files.asByteSource(expected).hash(sha256()), Files.asByteSource(output).hash(sha256()));
    }

    @Test
    void shouldSuccessfullyRunJSON_B_16_Z_2048_Sample() throws IOException {
        //given
        File input = new File(getClass().getClassLoader().getResource("audio/sample_01.mp3").getFile());
        File output = File.createTempFile("awf-sample_01_summary", ".json");
        output.deleteOnExit();
        BBCAudioWaveform bbcAudioWaveform = new BBCAudioWaveform(audioWavePath);
        AWFCommand command = AWFCommand.builder()
                .setInput(input)
                .setOutput(output)
                .setBits(AWFBit.SIXTEEN)
                .addZoom(2048)
                .build();

        //when
        boolean success = Boolean.parseBoolean(bbcAudioWaveform.run(command).get("status"));

        Path resourceDirectory = Paths.get("src","test","resources","json/sample_01_B_16_Z_2048.json");
        java.nio.file.Files.copy(output.toPath(), resourceDirectory,
                StandardCopyOption.REPLACE_EXISTING);
        //then
        File expected = Paths.get("src","test","resources","json/sample_01_B_16_Z_2048.json").toFile();

        assertTrue(success);
        assertEquals(Files.asByteSource(expected).hash(sha256()), Files.asByteSource(output).hash(sha256()));
    }

    @Test
    void shouldSuccessfullyRunPNG_SPLIT_W_1920_H_1080_AUTO_AUDITION() throws IOException {
        //given
        File input = new File(getClass().getClassLoader().getResource("audio/sample_01.mp3").getFile());
        File output = File.createTempFile("awf-sample_01_summary", ".png");
        output.deleteOnExit();
        BBCAudioWaveform bbcAudioWaveform = new BBCAudioWaveform(audioWavePath);
        AWFCommand command = AWFCommand.builder()
                .setInput(input)
                .setOutput(output)
                .splitChannels()
                .setWidth(1920)
                .setHeight(1080)
                .setAmplitudeScale("auto")
                .setColorSchema(AWFColorSchema.AUDITION)
                .build();

        //when
        boolean success = Boolean.parseBoolean(bbcAudioWaveform.run(command).get("status"));

        Path resourceDirectory = Paths.get("src","test","resources","json/sample_01_SPLIT_W_1920_H_1080_AUTO_AUDITION.png");
        java.nio.file.Files.copy(output.toPath(), resourceDirectory,
                StandardCopyOption.REPLACE_EXISTING);
        //then
        File expected = Paths.get("src","test","resources","json/sample_01_SPLIT_W_1920_H_1080_AUTO_AUDITION.png").toFile();

        assertTrue(success);
        assertEquals(Files.asByteSource(expected).hash(sha256()), Files.asByteSource(output).hash(sha256()));
    }
}
