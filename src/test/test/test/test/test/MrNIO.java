package test.test.test.test.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MrNIO {

  public static void main(String[] args) throws IOException {

    InputStream in1 = MrNIO.class.getResourceAsStream("NewFile.jsp");
    System.out.println(in1 == null);


    RandomAccessFile file = new RandomAccessFile("F:\\blog-mg\\regulars", "rw");
    RandomAccessFile file2 = new RandomAccessFile("F:\\blog-mg\\_blog-mg_out", "rw");
    System.out.println("你妹".length());
    System.out.println(file.length());
    System.out.println(file2.length());
    FileChannel channel = file.getChannel();

    ByteBuffer buffer = ByteBuffer.allocate(8);
    // Files.readAllLines("F:\\blog-mg\\regulars", Charset.forName("GBK"));
    int size = channel.read(buffer);
    while (size != -1) {
      // System.out.println("--->\t read " + size);

      buffer.flip(); // transform read to write

      while (buffer.hasRemaining()) {
        byte[] tempBuffer = new byte[1024];
        buffer.get(tempBuffer, 0, size);
        System.out.print(new String(tempBuffer, 0, size));
      }
      // buffer.compact();
      buffer.clear();
      size = channel.read(buffer);
    }
    file.close();
  }
}
