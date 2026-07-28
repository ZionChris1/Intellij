/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 10/30/2022
 **/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    public enum Command {
        A_COMMAND,
        C_COMMAND,
        L_COMMAND,
        NO_COMMAND
    }
    private String mRawLine, mCleanLine, mCompMnemonic, mDestMnemonic, mJumpMnemonic, mSymbol;
    private Command mCommandType;
    private Scanner mInputFile;
    private int mLineNumber;

    //Initializes a new Scanner for the assembly file
    //Precondition: fileName is valid
    //Postcondition: A new scanner is created for the assembly file
    public Parser(String fileName) {
        try {
            mInputFile = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            //If file could not be opened print message and exit with error code
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    //Reads the next line from the assembly file and parses it
    //Precondition: the Scanner has not reached the end of the file
    //Postcondition: the next line of input from the assembly file has been parsed
    public void advance() {
        //Get next line and parse it
        mRawLine = mInputFile.nextLine();
        cleanLine();
        parseCommandType();
        parse();

        //Only increment mLineNumber if the instruction is an A or C command
        if(mCommandType == Command.A_COMMAND || mCommandType == Command.C_COMMAND)
            mLineNumber++;
    }

    //Parses mCleanLine
    //Precondition: mCleanLine has been called for the current line
    //Postcondition: mCleanLine has been parsed into proper variables
    private void parse() {
        if(mCommandType == Command.C_COMMAND) {
            //Parse C command parts
            parseDest();
            parseComp();
            parseJump();
        } else if(mCommandType == Command.L_COMMAND || mCommandType == Command.A_COMMAND)
            //Parse symbol part
            parseSymbol();
    }

    //Cleans up the line read from the assembly file
    //Precondition: advance has been called
    //Postcondition: mCleanLine contains the cleaned up line
    public void cleanLine() {
        //End is index of start of comment
        int end = mRawLine.indexOf("//");

        //If no comment in line set end to end of string
        if(end == -1)
            end = mRawLine.length();

        //Remove comment(if any), trim whitespace from ends, and remove all spaces
        mCleanLine = mRawLine.substring(0, end).trim().replace(" ", "");
    }

    //Parses the command type
    //Precondition: advance has been called
    //Postcondition: mCommandType contains the command type
    private void parseCommandType() {
        //Parse which command type mCleanLine is
        if(mCleanLine.length() == 0)
            mCommandType = Command.NO_COMMAND;
        else if(mCleanLine.charAt(0) == '@')
            mCommandType = Command.A_COMMAND;
        else if(mCleanLine.charAt(0) == '(')
            mCommandType = Command.L_COMMAND;
        else
            mCommandType = Command.C_COMMAND;
    }

    //Extracts the symbol part of mCleanLine
    //Precondition: mCleanLine is not null and mCommandType is A or L command
    //Postcondition: mSymbol contains the symbol from mCleanLine
    private void parseSymbol() {
        if(mCleanLine.charAt(0) == '@')
            mSymbol = mCleanLine.substring(1);
        else
            mSymbol = mCleanLine.substring(1, mCleanLine.length() - 1);
    }

    //Extracts the comp part of mCleanLine
    //Precondition: mCleanLine is not null and mCommandType is C command
    //Postcondition: mJumpMnemonic contains the dest part of mCleanLine
    private void parseComp() {
        //Start at index of '=' or -1 if '=' not in mCleanLine
        int start = mCleanLine.indexOf("=");

        //End = index of ';' or end of string if ';' not in mCleanLine
        int end = mCleanLine.indexOf(";");
        if(end == -1)
            end = mCleanLine.length();

        //mCompMnemonic = mCleanLine from start + 1 to end
        mCompMnemonic = mCleanLine.substring(start + 1, end);
    }

    //Extracts the dest part of mCleanLine
    //Precondition: mCleanLine is not null and mCommandType is C command
    //Postcondition: mJumpMnemonic contains the dest part of mCleanLine
    private void parseDest() {
        //end is the index of the first '='
        int end = mCleanLine.indexOf("=");
        //If there is no '=' then mDestMnemonic = null
        if(end == -1)
            mDestMnemonic = null;
        //Otherwise mJumpMnemonic = mCleanLine from start of string to end
        else
            mDestMnemonic = mCleanLine.substring(0, end);
    }

    //Extracts the jump part of mCleanLine
    //Precondition: mCleanLine is not null and mCommandType is C command
    //Postcondition: mJumpMnemonic contains the jump part of mCleanLine
    private void parseJump() {
        //start is the index of the first ';'
        int start = mCleanLine.indexOf(";");
        //If there is no ';' then mDestMnemonic = null
        if(start == -1)
            mJumpMnemonic = null;
        //Otherwise mJumpMnemonic = mCleanLine from start + 1 onwards
        else
            mJumpMnemonic = mCleanLine.substring(start + 1);
    }

    //Checks if there are more lines in the assembly file
    //Precondition: Scanner has been initialized
    //Postcondition: returns whether there are more commands to process
    public boolean hasMoreCommands() {
        return mInputFile.hasNextLine();
    }

    //Returns the raw line read from the assembly file
    //Precondition: advance has been called
    //Postcondition: the raw line is returned
    public String getRawLine() {
        return mRawLine;
    }

    //Returns the cleaned up current assembly line
    //Precondition: cleanLine has been called
    //Postcondition: the clean line is returned
    public String getCleanLine() {
        return mCleanLine;
    }

    //Returns the comp mnemonic for the current instruction being processed
    //Precondition: current instruction is a C instruction
    //Postcondition: the comp mnemonic for the current instruction is returned
    public String getCompMnemonic() {
        return mCompMnemonic;
    }

    //Returns the dest mnemonic for the current instruction being processed
    //Precondition: current instruction is a C instruction
    //Postcondition: the dest mnemonic for the current instruction is returned
    public String getDestMnemonic() {
        return mDestMnemonic;
    }

    //Returns the jump mnemonic for the current instruction being processed
    //Precondition: current instruction is a C instruction
    //Postcondition: the jump mnemonic for the current instruction is returned
    public String getJumpMnemonic() {
        return mJumpMnemonic;
    }

    //Returns the symbol for the current line
    //Precondition: Current instruction is an A or L instruction
    //Postcondition: the symbol for the current line is returned
    public String getSymbol() {
        return mSymbol;
    }

    //Returns the command type of the current assembly line
    //Precondition: parseCommandType ahs been called
    //Postcondition: the binary code for mnemonic is returned
    public Command getCommandType() {
        return mCommandType;
    }

    //Returns the line number of the current assembly line
    //Precondition: advance has been called
    //Postcondition: the current line number is returned
    public int getLineNumber() {
        return mLineNumber;
    }
}
