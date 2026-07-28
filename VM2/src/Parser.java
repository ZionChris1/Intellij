/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 12/12/2022
 **/

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
public class Parser {
    private VMTranslator.CommandType mCommandType;
    private Scanner mInputFile;
    private String mArg1;
    private int mArg2;

    public Parser(String fileName) {
        //Try to open input file
        try {
            mInputFile = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public void advance() {
        //read and clean input line
        String[] cleanLineParts = mInputFile.nextLine().strip().replace("\t", "").split("//");
        String[] lineParts;
        if(cleanLineParts.length != 0)
            lineParts = cleanLineParts[0].split(" ");
        else
            lineParts = new String[] {""};
        String command = lineParts[0].strip();

        //parse command
        switch(command) {
            case "push" -> {
                mCommandType = VMTranslator.CommandType.C_PUSH;
                mArg1 = lineParts[1];
                mArg2 = Integer.parseInt(lineParts[2]);
            }
            case "pop" -> {
                mCommandType = VMTranslator.CommandType.C_POP;
                mArg1 = lineParts[1];
                mArg2 = Integer.parseInt(lineParts[2]);
            }
            case "add","sub","neg","eq","gt","lt","and","or","not" -> {
                mCommandType = VMTranslator.CommandType.C_ARITHMETIC;
                mArg1 = lineParts[0];
                mArg2 = 0;
            }
            case "label" -> {
                mCommandType = VMTranslator.CommandType.C_LABEL;
                mArg1 = lineParts[1];
                mArg2 = 0;
            }
            case "goto" -> {
                mCommandType = VMTranslator.CommandType.C_GOTO;
                mArg1 = lineParts[1];
                mArg2 = 0;
            }
            case "if-goto" -> {
                mCommandType = VMTranslator.CommandType.C_IF;
                mArg1 = lineParts[1];
                mArg2 = 0;
            }
            case "function" -> {
                mCommandType = VMTranslator.CommandType.C_FUNCTION;
                mArg1 = lineParts[1];
                mArg2 = Integer.parseInt(lineParts[2]);
            }
            case "return" -> {
                mCommandType = VMTranslator.CommandType.C_RETURN;
                mArg1 = "";
                mArg2 = 0;
            }
            case "call" -> {
                mCommandType = VMTranslator.CommandType.C_CALL;
                mArg1 = lineParts[1];
                mArg2 = Integer.parseInt(lineParts[2]);
            }
            default -> {
                mCommandType = null;
                mArg1 = "";
                mArg2 = 0;
            }
        }
    }

    public boolean hasMoreCommands() {
        return mInputFile.hasNextLine();
    }

    public VMTranslator.CommandType commandType() {
        return mCommandType;
    }

    public String arg1() {
        return mArg1;
    }

    public int arg2() {
        return mArg2;
    }
}