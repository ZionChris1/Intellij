import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssemblyFileParser {

  private List<String> cleanAssemblyCode;
  private List<Instruction> parsedAssemblyInstructions;
  private final Scanner fileReader;
  public AssemblyFileParser(String fileName) throws FileNotFoundException {
    File assemblyFile = new File(fileName + ".asm");
    if (!assemblyFile.exists() || assemblyFile.length() == 0)
      throw new FileNotFoundException(fileName + ".asm does not exist or is empty.");
    fileReader = new Scanner(assemblyFile);


    makeFirstPass();
    makeSecondPass();
  }

  private void makeSecondPass() {
    int ramAddress = 16;
    parsedAssemblyInstructions = new ArrayList<>();
    for (String code : cleanAssemblyCode) {
      if (code.startsWith("@")) {
          int address;
          try {
            address = Integer.parseInt(code.substring(1));
          }
          catch (NumberFormatException e) {
            String symbol = code.substring(1);
            if (SymbolTable.contains(symbol))
              address = SymbolTable.getAddress(symbol);
            else {
              SymbolTable.add(code.substring(1), ramAddress);
              address = ramAddress++;
            }
          }
          parsedAssemblyInstructions.add(new AInstruction("@" + address));
      }
      else {
        parsedAssemblyInstructions.add(new CInstruction(code));
      }
    }
  }

  private void makeFirstPass() {
    cleanAssemblyCode = new ArrayList<>(100);
    int romAddress = 0;
    String rawLine, cleanLine;
    while (fileReader.hasNextLine())
    {
      rawLine = fileReader.nextLine();
      cleanLine = clean(rawLine);

      if (!cleanLine.isEmpty()) {
        if (cleanLine.startsWith("(") && cleanLine.endsWith(")"))
          insertLabelInSymbolTable(cleanLine, romAddress);
        else {
          cleanAssemblyCode.add(cleanLine);
          romAddress++;
        }
      }

    }
    fileReader.close();
  }

  private String clean(String rawLine) {
    String cleanLine = rawLine.replaceAll("\\s+"," ").trim();
    int commentIndex = cleanLine.indexOf("//");
    if (commentIndex != -1)
      cleanLine = cleanLine.substring(0, commentIndex).trim();
    return cleanLine;
  }

  private void insertLabelInSymbolTable(String cleanLine, int romAddress) {
    String label = cleanLine.substring(1, cleanLine.length()-1);
    SymbolTable.add(label, romAddress);
  }

  public List<Instruction> getParsedAssemblyInstructions() {
    return parsedAssemblyInstructions;
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder(String.format("%-20s%s", "Assembly Code", "Machine Code\n"));
    for (Instruction inst : parsedAssemblyInstructions)
      sb.append(String.format( "%-20s%s", inst.assemblyCode, inst.machineCode)).append("\n");


    return sb.toString();
  }

}
