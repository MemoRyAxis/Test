package test.test.test.test.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class MrMusician {

	public static void main(String[] args) throws IOException, TagException,
			ReadOnlyFileException, InvalidAudioFrameException {
		
		File musicAlbum = new File("F:\\music\\0216");
		File[] music = musicAlbum.listFiles();
		List<File> musicList = Arrays.asList(music);
		
		musicList.forEach(m -> {
			try {
				MP3File mp3 = new MP3File(m);
				Tag tag = mp3.getTag();
				String musicTitle = tag.getFirst(FieldKey.TITLE);
				String musicArtist = tag.getFirst(FieldKey.ARTIST);
				File newFile = new File("F:\\music\\0000\\" + musicArtist + " - " + musicTitle + ".mp3");
				boolean isRename = m.renameTo(newFile);
				System.out.println(isRename);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
		System.out.println("\t\t\t -> *");
		File tiktok = new File("F:\\music\\0000\\Ke$ha - Tik Tok.mp3");
		
		System.out.println(tiktok.isFile());
		MP3File tiktokMP3 = new MP3File(tiktok);
		Tag tag = tiktokMP3.getTag();

		System.out.println(tag.getFirst(FieldKey.ARTIST));
		System.out.println(tag.getFirst(FieldKey.ALBUM));
		System.out.println(tag.getFirst(FieldKey.TITLE));
		System.out.println(tag.getFirst(FieldKey.COMMENT));
		System.out.println(tag.getFirst(FieldKey.YEAR));
		System.out.println(tag.getFirst(FieldKey.TRACK));
		System.out.println(tag.getFirst(FieldKey.DISC_NO));
		System.out.println(tag.getFirst(FieldKey.COMPOSER));
		System.out.println(tag.getFirst(FieldKey.ARTIST_SORT));

		try (InputStream is = new FileInputStream(tiktok);) {
			byte[] header = new byte[128];
			is.read(header);
			for (int i = 0; i < header.length; i++) {
				System.out.print(header[i] + " ");
			}
			System.out.println();
			System.out.println(new String(header));
			System.out.println(new String(header, 0, 2));
			System.out.println(new String(header, 3, 30));
			System.out.println(new String(header, 33, 30));
			System.out.println(new String(header, 63, 30));
			System.out.println(new String(header, 93, 4));
			System.out.println(new String(header, 97, 30));
			System.out.println(new String(header, 127, 1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
