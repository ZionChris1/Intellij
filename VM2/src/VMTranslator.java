/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 12/12/2022
 **/

import java.io.File;
import java.util.ArrayList;

public class VMTranslator {
    private static CodeWriter codeWriter;
    private static Parser parser;

    public static void main(String[] args) {
        //Get input and output file names
        ArrayList<String> infileNames = new ArrayList<>();
        File file = new File(args[0]);
        String inFileName = args[0], outFileName;

        int dotIndex = args[0].indexOf(".");

        //If file name provided
        if(file.isFile()) {
            outFileName = file.getName().substring(0, dotIndex) + ".asm";
            infileNames.add(file.getName());
        }
        //If folder name provided
        else {
            //Get all files in folder
            File[] files = file.listFiles();
            for(int i = 0;i < files.length; i++)
                //If file is a vm file add it to the list if input files
                if(files[i].getName().endsWith(".vm"))
                    infileNames.add(files[i].getName());

            //Generate path for output file
            outFileName = file.getAbsolutePath() + "\\" + file.getName() + ".asm";
        }


        //Initialize parser and codeWriter
        codeWriter = new CodeWriter(outFileName);
        codeWriter.writeInit();
        for(String filename : infileNames) {

            if(file.isFile())
                parser = new Parser(filename);
            else
                parser = new Parser(file.getAbsolutePath() + "\\" + filename);

            codeWriter.setFileName(filename);
            while (parser.hasMoreCommands()) {
                //Advance parser
                parser.advance();

                //Skip to next loop iteration if line has no command
                if (parser.commandType() == null)
                    continue;

                //Call appropriate codeWriter method for the command type
                switch (parser.commandType()) {
                    case C_ARITHMETIC -> {
                        codeWriter.writeArithmetic(parser.arg1());
                    }
                    case C_PUSH, C_POP -> {
                        codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
                    }
                    case C_LABEL -> {
                        codeWriter.writeLabel(parser.arg1());
                    }
                    case C_GOTO -> {
                        codeWriter.writeGoto(parser.arg1());
                    }
                    case C_IF -> {
                        codeWriter.writeIf(parser.arg1());
                    }
                    case C_FUNCTION -> {
                        codeWriter.writeFunction(parser.arg1(), parser.arg2());
                    }
                    case C_CALL -> {
                        codeWriter.writeCall(parser.arg1(), parser.arg2());
                    }
                    case C_RETURN -> {
                        codeWriter.writeReturn();
                    }
                }
            }
        }
        //Close output file
        codeWriter.close();
    }
    public enum CommandType {
        C_ARITHMETIC,
        C_PUSH,
        C_POP,
        C_LABEL,
        C_GOTO,
        C_IF,
        C_FUNCTION,
        C_RETURN,
        C_CALL;
    }
}
