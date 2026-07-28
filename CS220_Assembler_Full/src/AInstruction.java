public class AInstruction extends Instruction {

  public AInstruction(String code) {

    if (code.length() < 2 || !code.startsWith("@"))
      throw new IllegalArgumentException("A instructions must start with @ and be followed by an address or symbol");
    this.assemblyCode = code;
    int address =  Integer.parseInt(assemblyCode.substring(1));
    this.machineCode = decimalToBinary(address);
  }

  private static String decimalToBinary(int address) {
    StringBuilder sb = new StringBuilder(16);
    while (address != 0)
    {
      sb.insert(0, address % 2);
      address /= 2;
    }
    while (sb.length() < 16)
      sb.insert(0, '0');

    return sb.toString();
  }

  @Override
  public String toString() {
    return "A-Instruction [Assembly = " + assemblyCode + ", Machine = " + machineCode + "]";
  }
}
