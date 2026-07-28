import java.util.HashMap;

public class SymbolTable {

  private static final HashMap<String, Integer> theTable;
  private static final String INITIAL_VALID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONPQRSTUVWXYZ_.$:";
  private static final String ALL_VALID_CHARS = INITIAL_VALID_CHARS + "0123456789";
  private SymbolTable() {}
  static
  {
      theTable = new HashMap<>(50);
      theTable.put("R0"	, 0);
      theTable.put("R1"	, 1);
      theTable.put("R2"	, 2);
      theTable.put("R3"	, 3);
      theTable.put("R4"	, 4);
      theTable.put("R5"	, 5);
      theTable.put("R6"	, 6);
      theTable.put("R7"	, 7);
      theTable.put("R8"	, 8);
      theTable.put("R9"	, 9);
      theTable.put("R10"	, 10);
      theTable.put("R11"	, 11);
      theTable.put("R12"	, 12);
      theTable.put("R13"	, 13);
      theTable.put("R14"	, 14);
      theTable.put("R15"	, 15);
      theTable.put("SCREEN", 16384);
      theTable.put("KBD"	, 24576);
      theTable.put("SP"	, 0);
      theTable.put("LCL"	, 1);
      theTable.put("ARG"	, 2);
      theTable.put("THIS"	, 3);
      theTable.put("THAT"	, 4);
    }


  public static boolean contains(String symbol)
  {
    return theTable.containsKey(symbol);
  }

  public static boolean add(String symbol, int address)
  {
    if (!isValidName(symbol) || contains(symbol))
      return false;
    return theTable.put(symbol, address) == null;
  }

  private static boolean isValidName(String symbol)
  {
    String validChars = INITIAL_VALID_CHARS;
    for (int i = 0; i < symbol.length(); i++)
    {
      if (!validChars.contains(symbol.substring(i, i+1)))
        return false;
      validChars = ALL_VALID_CHARS;
    }
    return true;
  }

  public static int getAddress(String symbol)
  {
    return theTable.get(symbol);
  }

}
