import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BinFileWiz {

    static ArrayList<String> binaryFileExtensions = new ArrayList<String>(Arrays.asList(
        ".exe", ".bin", ".jpg", ".jpeg", ".png", ".gif", ".mp3", ".wav",
        ".mp4", ".avi", ".pdf", ".doc", ".docx", ".db", ".sqlite", ".zip",
        ".rar", ".gz", ".tar", ".7z", ".iso", ".dll", ".class", ".o", ".so",
        ".dylib", ".apk", ".bat", ".com", ".dat", ".msi", ".sys", ".vxd",
        ".img", ".toast", ".vmdk", ".ova"
    ));

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Check length of args
        if ( args.length < 2) {
            System.out.println("Usage: java WritingBinaryFiles <operation> <input_file_path> <output_file>");
            return;
        }

        String operation = args[0];
        String inputFile = args[1];
        String outputFile = null; if (args.length == 3) outputFile = args[2];

        // Read or write selection
        if (operation.equals("-w") || operation.equals("-write")) {
            System.out.println("Write selected.");
            writeData(inputFile, outputFile);
        } else if (operation.equals("-r") || operation.equals("-read")) {
            System.out.println("Read selected.");
            readData(inputFile, outputFile);
        } else {
            System.out.println("Invalid operation. Use -w, -write or -r, -read.");
        }
    }
    
    public static void writeData(String inputFilename, String outputFilename) throws FileNotFoundException, IOException {
        // Open the input file for reading
        BufferedReader br = new BufferedReader(new FileReader(inputFilename));
        // Open the output file for writing in binary format
        if (outputFilename == null) outputFilename = "output.dat";
        FileOutputStream fos = new FileOutputStream(outputFilename);
        DataOutputStream dos = new DataOutputStream(fos);
        
        String line;
        try {
            while ((line = br.readLine()) != null) {
                dos.writeUTF(line);
            }
        } catch (IOException ex) {
            System.out.println("Data couldn't be saved.");
        } finally {
            // Close both the input and output streams
            br.close();
            dos.close();
        }
    }
    
    public static void readData(String inputFilename, String outputFilename) throws FileNotFoundException, IOException {
        // Open the input binary file for reading
        FileInputStream fis = new FileInputStream(inputFilename);
        DataInputStream dis = new DataInputStream(fis);
        BufferedWriter bw = null;
    
        // Open the output file for writing if outputFilename is not null
        if (outputFilename != null) {
            bw = new BufferedWriter(new FileWriter(outputFilename));
        }
    
        try {
            boolean endOfFile = false;
            while (!endOfFile) {
                try {
                    // Read a UTF string from the binary file and write it to the text file
                    String line = dis.readUTF();
                    if (outputFilename == null) System.out.println(line); // Print the line to console
                    if (bw != null) {
                        bw.write(line);
                        bw.newLine();
                    }
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
        } finally {
            // Close the input stream
            dis.close();
            // Close the output stream if it was opened
            if (bw != null) {
                bw.close();
            }
        }
    }
}