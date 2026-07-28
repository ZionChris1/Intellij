/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 10/30/2022
 **/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
public class Assembler {

    //Converts a given number to binary and pads the left with zeroes to length 16
    //Precondition:
    //Postcondition: a binary String 16 bits long is returned
    private static String decimalToBinary(int number) {
        //Convert number to a binary string
        String binary = Integer.toBinaryString(number);

        //Pad left with zeroes until length is 16
        while(binary.length() < 16)
            binary = "0" + binary;

        return binary;
    }

    //Reads all the jump labels and adds them to the symbol table
    //Precondition: assemblyFileName and symbolTable are not null
    //Postcondition: the symbol table contains all the jump symbols
    private static void firstPass(String assemblyFileName, SymbolTable symbolTable) {
        Parser parser = new Parser(assemblyFileName);

        //Loop through all assembly lines
        while (parser.hasMoreCommands()) {
            //Advance to next assembly line
            parser.advance();
            //If assembly line is an L command update the symbol table
            if(parser.getCommandType() == Parser.Command.L_COMMAND)
                symbolTable.addEntry(parser.getSymbol(), parser.getLineNumber());
        }
    }

    //Assembles the assembly into machine code and writes it to an output file
    //Precondition: firstRun has been run on symbolTable and binaryOutputFile is not null
    //Postcondition: output file contains the assembled code
    private static void secondPass(String assemblyFileName, SymbolTable symbolTable, String binaryFileName) {
        Parser parser = new Parser(assemblyFileName);
        CInstructionMapper mapper = new CInstructionMapper();
        PrintWriter output = null;

        //Start variables at 16
        int address = 16;

        //Try to create output file
        try {
            output = new PrintWriter(new File(binaryFileName));
        }
        //If output file can't be created quit
        catch (FileNotFoundException e) {
            handleError(e.getMessage());
        }

        //Loop through all assembly lines
        while(parser.hasMoreCommands()){
            parser.advance();

            switch (parser.getCommandType()) {
                case A_COMMAND:
                    //Try to convert label to a number
                    try {
                        //Convert label to number and add to output file
                        output.println(decimalToBinary(Integer.parseInt(parser.getSymbol())));
                    }
                    //If label can't be converted to a number
                    catch(NumberFormatException e) {
                        //Add symbol to table if absent
                        if(!symbolTable.contains(parser.getSymbol()))
                            symbolTable.addEntry(parser.getSymbol(), address++);

                        //Get address of label, translate, and add to output file
                        output.println(decimalToBinary(symbolTable.getAddress(parser.getSymbol())));
                    }
                    break;
                case C_COMMAND:
                    //Add instruction to output file
                    output.print("111");
                    output.print(mapper.comp(parser.getCompMnemonic()));
                    output.print(mapper.dest(parser.getDestMnemonic()));
                    output.println(mapper.jump(parser.getJumpMnemonic()));
                    break;
            }

        }
        output.close();
    }

    //Reads the assembly filename from the command line and assembles it into an output file
    //Precondition: filename is passed from command line
    //Postcondition: Assembly file is assembled into an output file
    public static void main(String[] args) {

        String inFileName = args[0];
        int dotIndex = inFileName.indexOf(".");
        String outFileName = inFileName.substring(0, dotIndex) + ".hack";
        SymbolTable symbolTable = new SymbolTable();
     
        //Run the first and second passes
        firstPass(inFileName, symbolTable);
        secondPass(inFileName, symbolTable, outFileName);
    }

    //Prints the given error message and stops the program
    //Precondition: Error is thrown
    //Postcondition: error message is printed and program is stopped
    private static void handleError(String message) {
        //Print message and exit with error code
        System.err.println(message);
        System.exit(-1);
    }
}
