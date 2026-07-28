/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 10/30/2022
 **/
import java.util.HashMap;

public class SymbolTable {
    private static final String ALL_VALID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_.$:";
    private static final String INITIAL_VALID_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_.$:";
    private HashMap<String, Integer> mSymbolTable;

    //Initializes the mSymbolTable and adds the default symbols
    //Precondition: none
    //Postcondition: mSymbolTable is set up
    public SymbolTable() {
        //Initialize mSymbolTable
        mSymbolTable = new HashMap<>();

        //Add default labels
        mSymbolTable.put("R0", 0);
        mSymbolTable.put("R1", 1);
        mSymbolTable.put("R2", 2);
        mSymbolTable.put("R3", 3);
        mSymbolTable.put("R4", 4);
        mSymbolTable.put("R5", 5);
        mSymbolTable.put("R6", 6);
        mSymbolTable.put("R7", 7);
        mSymbolTable.put("R8", 8);
        mSymbolTable.put("R9", 9);
        mSymbolTable.put("R10", 10);
        mSymbolTable.put("R11", 11);
        mSymbolTable.put("R12", 12);
        mSymbolTable.put("R13", 13);
        mSymbolTable.put("R14", 14);
        mSymbolTable.put("R15", 15);
        mSymbolTable.put("SCREEN", 16384);
        mSymbolTable.put("KBD", 24576);
        mSymbolTable.put("SP", 0);
        mSymbolTable.put("LCL", 1);
        mSymbolTable.put("ARG", 2);
        mSymbolTable.put("THIS", 3);
        mSymbolTable.put("THAT", 4);
    }

    //Adds a new entry to mSymbolTable
    //Precondition: symbol is not null and address is not negative
    //Postcondition: true is returned if the entry was added successfully and false otherwise
    public boolean addEntry(String symbol, int address) {
        //If symbol is a valid name
        if(validName(symbol)) {
            //Add a new symbol table entry and return true
            mSymbolTable.put(symbol, address);
            return true;
        } else {
            //If symbol in not a valid name return false
            return false;
        }
    }

    //Check whether mSymbolTable contains the given symbol
    //Precondition: mSymbolTable is set up
    //Postcondition: returns whether symbol is in mSymbolTable
    public boolean contains(String symbol) {
        return mSymbolTable.containsKey(symbol);
    }

    //Returns the address of the given symbol
    //Precondition: symbol is in mSymbolTable
    //Postcondition: the binary code for mnemonic is returned
    public int getAddress(String symbol) {
        return mSymbolTable.get(symbol);
    }

    //Checks if a given name is valid
    //precondition: INITIAL_VALID_CHARS and ALL_VALID_CHARS are set up and symbol is not null
    //Postcondition: returns if the given name is valid
    public boolean validName(String symbol) {
        //If first character is valid
        if(INITIAL_VALID_CHARS.contains(Character.toString(symbol.charAt(0)))) {
            //Check rest of string
            for(char c : symbol.toCharArray())
                //If c is invalid return false
                if(!ALL_VALID_CHARS.contains(Character.toString(c)))
                    return false;

            //If all characters are valid
            return true;
        }
        //If first character is invalid
        else {
            return false;
        }
    }
}