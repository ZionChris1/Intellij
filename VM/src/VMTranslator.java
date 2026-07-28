/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 10/30/2022
 **/

public class VMTranslator {
    private static CodeWriter codeWriter;
    private static Parser parser;
    
    public static void main(String[] args) {
        //Get input and output file names
        String inFileName = args[0];
        int dotIndex = inFileName.indexOf(".");
        String outFileName = inFileName.substring(0, dotIndex) + ".asm";

        //Initialize parser and codeWriter
        parser = new Parser(inFileName);
        codeWriter = new CodeWriter(outFileName);

        while(parser.hasMoreCommands()) {
            //Advance parser
            parser.advance();

            //Skip to next loop iteration if line has no command
            if(parser.commandType() == null)
                continue;

            //Call appropriate codeWriter method for the command type
            switch(parser.commandType()) {
                case C_ARITHMETIC -> {codeWriter.writeArithmetic(parser.arg1());}
                case C_PUSH, C_POP -> {codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());}
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
