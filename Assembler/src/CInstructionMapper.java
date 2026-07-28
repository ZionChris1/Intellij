/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 10/30/2022
 **/
import java.util.HashMap;

public class CInstructionMapper {
    private HashMap<String, String> mCompCodes, mDestCodes, mJumpCodes;

    //Initializes compCodes, destCodes, and jumpCodes with binary codes
    //Precondition: none
    //Postcondition: HashMaps are initialized
    public CInstructionMapper() {
        //Initialize HashMaps
        mCompCodes = new HashMap<>();
        mDestCodes = new HashMap<>();
        mJumpCodes = new HashMap<>();

        //Add comp codes
        mCompCodes.put("0", "0101010");
        mCompCodes.put("1", "0111111");
        mCompCodes.put("-1", "0111010");
        mCompCodes.put("D", "0001100");
        mCompCodes.put("A", "0110000");
        mCompCodes.put("!D", "0001101");
        mCompCodes.put("!A", "0110001");
        mCompCodes.put("-D", "0001111");
        mCompCodes.put("-A", "0110011");
        mCompCodes.put("D+1", "0011111");
        mCompCodes.put("A+1", "0110111");
        mCompCodes.put("D-1", "0001110");
        mCompCodes.put("A-1", "0110010");
        mCompCodes.put("D+A", "0000010");
        mCompCodes.put("D-A", "0010011");
        mCompCodes.put("A-D", "0000111");
        mCompCodes.put("D&A", "0000000");
        mCompCodes.put("D|A", "0010101");
        mCompCodes.put("M", "1110000");
        mCompCodes.put("!M", "1110001");
        mCompCodes.put("-M", "1110011");
        mCompCodes.put("M+1", "1110111");
        mCompCodes.put("M-1", "1110010");
        mCompCodes.put("D+M", "1000010");
        mCompCodes.put("D-M", "1010011");
        mCompCodes.put("M-D", "1000111");
        mCompCodes.put("D&M", "1000000");
        mCompCodes.put("D|M", "1010101");

        //Add dest codes
        mDestCodes.put(null, "000");
        mDestCodes.put("M", "001");
        mDestCodes.put("D", "010");
        mDestCodes.put("MD", "011");
        mDestCodes.put("A", "100");
        mDestCodes.put("AM", "101");
        mDestCodes.put("AD", "110");
        mDestCodes.put("AMD", "111");

        //Add jump codes
        mJumpCodes.put(null, "000");
        mJumpCodes.put("JGT", "001");
        mJumpCodes.put("JEQ", "010");
        mJumpCodes.put("JGE", "011");
        mJumpCodes.put("JLT", "100");
        mJumpCodes.put("JNE", "101");
        mJumpCodes.put("JLE", "110");
        mJumpCodes.put("JMP", "111");
    }

    //Returns the binary code for the given comp mnemonic
    //Precondition: compCodes is initialized
    //Postcondition: binary code for mnemonic is returned
    public String comp(String mnemonic) {
        return mCompCodes.get(mnemonic);
    }

    //Returns the binary code for the given dest mnemonic
    //Precondition: destCodes is initialized
    //Postcondition: binary code for mnemonic is returned
    public String dest(String mnemonic) {
        return mDestCodes.get(mnemonic);
    }

    //Returns the binary code for the given jump mnemonic
    //Precondition: jumpCodes is initialized
    //Postcondition: binary code for mnemonic is returned
    public String jump(String mnemonic) {
        return mJumpCodes.get(mnemonic);
    }
}
