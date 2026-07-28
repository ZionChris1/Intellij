import java.io.FileNotFoundException;

public class Main {
  public static final String PROGRAM_NAME = "Max";
  public static void main(String[] args) {
    try {
      AssemblyFileParser ap = new AssemblyFileParser(PROGRAM_NAME);
      System.out.println(ap);
      MachineCodeWriter.writeToBinaryFile(PROGRAM_NAME, ap.getParsedAssemblyInstructions());

    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
  }
}