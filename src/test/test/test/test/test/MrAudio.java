package test.test.test.test.test;

import java.io.File;

import javazoom.jl.converter.Converter;

public class MrAudio {


    public static void main(String[] args) throws Exception {

        String mp3ButwavFile = "C:\\Users\\Administrator\\Downloads\\R00205c3f_20150906152848.wav";

        Converter converter = new Converter();
        File wavFile = new File(mp3ButwavFile);
        File mp3File = new File(mp3ButwavFile.replace(".wav", ".mp3"));
        wavFile.renameTo(mp3File);

        System.out.println(wavFile.getPath());
        System.out.println(mp3File.getPath());
        converter.convert(mp3File.getPath(), wavFile.getPath());


        // AudioFileFormat inputFileFormat = AudioSystem.getAudioFileFormat(new
        // File(mp3ButwavFile));
        // AudioFileFormat inputFileFormat = AudioSystem.getAudioFileFormat(new
        // File(mp3ButwavFile));
        // AudioInputStream ais = AudioSystem.getAudioInputStream(new File(mp3ButwavFile));
        //
        // AudioFormat audioFormat = ais.getFormat();

        // System.out.println("File Format Type: " + inputFileFormat.getType());
        // System.out.println("File Format String: " + inputFileFormat.toString());
        // System.out.println("File lenght: " + inputFileFormat.getByteLength());
        // System.out.println("Frame length: " + inputFileFormat.getFrameLength());
        // System.out.println("Channels: " + audioFormat.getChannels());
        // System.out.println("Encoding: " + audioFormat.getEncoding());
        // System.out.println("Frame Rate: " + audioFormat.getFrameRate());
        // System.out.println("Frame Size: " + audioFormat.getFrameSize());
        // System.out.println("Sample Rate: " + audioFormat.getSampleRate());
        // System.out.println("Sample size (bits): " + audioFormat.getSampleSizeInBits());
        // System.out.println("Big endian: " + audioFormat.isBigEndian());
        // System.out.println("Audio Format String: " + audioFormat.toString());
    }
}
