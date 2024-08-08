import java.io.*;
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
        String outputFile = args.length == 3 ? args[2] : null;

        // Read or write selection
        if (operation.equals("-w") || operation.equals("-write")) {
            writeData(inputFile, outputFile);
        } else if (operation.equals("-r") || operation.equals("-read")) {
            readData(inputFile, outputFile);
        } else {
            System.out.println("Invalid operation. Use -w, -write or -r, -read.");
        }
    }
    
    public static void writeData(String inputFilename, String outputFilename) throws FileNotFoundException, IOException {
        System.out.println("Reading from: " + inputFilename);
        BufferedReader br = new BufferedReader(new FileReader(inputFilename));
        
        if (outputFilename == null) outputFilename = "output.dat";
        System.out.println("Writing to: " + outputFilename);
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilename));
    
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println("Writing line: " + line);
            bw.write(line);
            bw.newLine();
        }
    
        br.close();
        bw.close();
    }
    
    public static void readData(String inputFilename, String outputFilename) throws FileNotFoundException, IOException {
        System.out.println("Reading from: " + inputFilename);
        BufferedReader br = new BufferedReader(new FileReader(inputFilename));
        BufferedWriter bw = null;
    
        if (outputFilename != null) {
            bw = new BufferedWriter(new FileWriter(outputFilename));
            System.out.println("Writing to: " + outputFilename);
        }
    
        try {
            String line;
            while ((line = br.readLine()) != null) {
                String filteredLine = line.replaceAll("[^a-zA-Z0-9 ]", "");
                System.out.println("Read line: " + filteredLine);
                if (outputFilename == null) {
                    System.out.println(filteredLine);
                } else {
                    bw.write(filteredLine);
                    bw.newLine();
                }
            }
        } finally {
            br.close();
            if (bw != null) {
                bw.close();
            }
        }
    }
    
}