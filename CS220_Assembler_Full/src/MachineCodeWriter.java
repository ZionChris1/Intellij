import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class MachineCodeWriter {

  public static void writeToBinaryFile(String fileName, List<Instruction> assemblyInstructions) throws FileNotFoundException {
    PrintWriter fileWriter = new PrintWriter(new File(fileName + ".hack"));
    for (Instruction inst : assemblyInstructions) {
      fileWriter.println(inst.getMachineCode());
    }
    fileWriter.close();
  }
}
