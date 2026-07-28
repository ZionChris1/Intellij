/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 12/12/2022
 **/

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeWriter {
    private PrintWriter mOutputFile;
    private String mFileName;
    private int mLineCount;
    private int mRetCount;

    public CodeWriter(String fileName) {
        //Try to open output file
        try {
            mOutputFile = new PrintWriter(fileName);
        }
        catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        //Initialize line and return statement count
        mLineCount = 0;
        mRetCount = 0;

        mFileName = fileName;
    }

    public void writeInit() {
        //Commented out for the test files BasicLoop, FibonacciSeries, and SimpleFunction
        //The initialization code doesn't seem to work with the function call

        //Setup SP, LCL, and ARG
        /*writeln("@261");
        writeln("D=A");
        writeln("@SP");
        writeln("M=D");
        writeln("@LCL");
        writeln("M=D");
        writeln("@256");
        writeln("D=A");
        writeln("@ARG");
        writeln("M=D");

        //Call Sys.init
        writeln("@Sys.init");
        writeln("0; JMP");*/
    }

    public void setFileName(String newFileName) {
        mFileName = newFileName;
    }

    public void writeArithmetic(String command) {
        //Write command to output file
        switch(command) {
            case "add" -> {
                writeln("@SP");
                writeln("AM=M-1");
                writeln("D=M");
                writeln("A=A-1");
                writeln("M=D+M");
            }
            case "sub" -> {
                writeln("@SP");
                writeln("AM=M-1");
                writeln("D=M");
                writeln("A=A-1");
                writeln("M=M-D");
            }
            case "neg" -> {
                writeln("@SP");
                writeln("A=M-1");
                writeln("M=-M");
            }
            case "eq" -> {
                writeln("@SP");
                writeln("AM=M-1");
                writeln("D=M");
                writeln("A=A-1");
                writeln("D=D-M");
                writeln("@" + (mLineCount + 7));
                writeln("D; JEQ");
                writeln("@SP");//False
                writeln("A=M-1");
                writeln("M=0");
                writeln("@" + (mLineCount + 5));
                writeln("0;JMP");
                writeln("@SP");//True
                writeln("A=M-1");
                writeln("M=-1");
            }
            case "gt" -> {
                writeln("@SP");
                writeln("AM=M-1");
                writeln("D=M");
                writeln("A=A-1");
                writeln("D=D-M");
                writeln("@" + (mLineCount + 7));
                writeln("D; JLT");
                writeln("@SP");//False
                writeln("A=M-1");
                writeln("M=0");
                writeln("@" + (mLineCount + 5));
                writeln("0;JMP");
                writeln("@SP");//True
                writeln("A=M-1");
                writeln("M=-1");
            }
            case "lt" -> {
                writeln("@SP");
                writeln("AM=M-1");
                writeln("D=M");
                writeln("A=A-1");
                writeln("D=D-M");
                writeln("@" + (mLineCount + 7));
                writeln("D; JGT");
                writeln("@SP");//False
                writeln("A=M-1");
                writeln("M=0");
                writeln("@" + (mLineCount + 5));
                writeln("0;JMP");
                writeln("@SP");//True
                writeln("A=M-1");
                writeln("M=-1");
            }
            case "and" -> {
                writeln("@SP");
                writeln("AM=M-1");
                writeln("D=M");
                writeln("A=A-1");
                writeln("M=M&D");
            }
            case "or" -> {
                writeln("@SP");
                writeln("AM=M-1");
                writeln("D=M");
                writeln("A=A-1");
                writeln("M=M|D");
            }
            case "not" -> {
                writeln("@SP");
                writeln("A=M-1");
                writeln("M=!M");
            }
        }
    }

    public void writePushPop(VMTranslator.CommandType commandType, String segment, int index) {
        //Write push command
        if(commandType == VMTranslator.CommandType.C_PUSH) {
            switch(segment) {
                case "constant" -> {
                    writeln("@" + index);
                    writeln("D=A");
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("M=D");
                }

                case "local" -> {
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@LCL");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("A=A+D");
                    writeln("D=M");
                    writeln("@13");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "argument" -> {
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@ARG");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("A=A+D");
                    writeln("D=M");
                    writeln("@13");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "static" -> {
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@static." + mFileName.split("\\.")[0] + "." + index);
                    writeln("D=M");
                    writeln("@13");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "temp" -> {
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@5");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("A=A+D");
                    writeln("D=M");
                    writeln("@13");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "pointer" -> {
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@3");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("A=A+D");
                    writeln("D=M");
                    writeln("@13");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "this" -> {
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@THIS");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("A=A+D");
                    writeln("D=M");
                    writeln("@13");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "that" -> {
                    writeln("@SP");
                    writeln("M=M+1");
                    writeln("A=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@THAT");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("A=A+D");
                    writeln("D=M");
                    writeln("@13");
                    writeln("A=M");
                    writeln("M=D");
                }
            }
        }
        //Write pop command
        else {
            switch(segment) {
                case "local" -> {
                    writeln("@LCL");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("D=A+D");
                    writeln("@14");
                    writeln("M=D");
                    writeln("@SP");
                    writeln("AM=M-1");
                    writeln("D=M");
                    writeln("@14");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "argument" -> {
                    writeln("@ARG");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("D=A+D");
                    writeln("@14");
                    writeln("M=D");
                    writeln("@SP");
                    writeln("AM=M-1");
                    writeln("D=M");
                    writeln("@14");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "static" -> {
                    writeln("@SP");
                    writeln("AM=M-1");
                    writeln("D=A");
                    writeln("@13");
                    writeln("M=D");
                    writeln("@13");
                    writeln("A=M");
                    writeln("D=M");
                    writeln("@static." + mFileName.split("\\.")[0] + "." + index);
                    writeln("M=D");
                }

                case "temp" -> {
                    writeln("@5");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("D=A+D");
                    writeln("@14");
                    writeln("M=D");
                    writeln("@SP");
                    writeln("AM=M-1");
                    writeln("D=M");
                    writeln("@14");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "pointer" -> {
                    writeln("@3");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("D=A+D");
                    writeln("@14");
                    writeln("M=D");
                    writeln("@SP");
                    writeln("AM=M-1");
                    writeln("D=M");
                    writeln("@14");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "this" -> {
                    writeln("@THIS");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("D=A+D");
                    writeln("@14");
                    writeln("M=D");
                    writeln("@SP");
                    writeln("AM=M-1");
                    writeln("D=M");
                    writeln("@14");
                    writeln("A=M");
                    writeln("M=D");
                }

                case "that" -> {
                    writeln("@THAT");
                    writeln("A=M");
                    writeln("D=A");
                    writeln("@" + index);
                    writeln("D=A+D");
                    writeln("@14");
                    writeln("M=D");
                    writeln("@SP");
                    writeln("AM=M-1");
                    writeln("D=M");
                    writeln("@14");
                    writeln("A=M");
                    writeln("M=D");
                }
            }
        }
    }

    public void writeLabel(String label) {
        writeln("(" + label + ")");
    }

    public void writeGoto(String label) {
        writeln("@" + label);
        writeln("0; JMP");
    }

    public void writeIf(String label) {
        writeln("@SP");
        writeln("AM=M-1");
        writeln("D=M");
        writeln("@" + label);
        writeln("D; JNE");
    }

    public void writeFunction(String name, int localArgs) {
        writeln("(" + name + ")");
        for(int i = 0; i < localArgs; i++) {
            writeln("@SP");
            writeln("M=M+1");
            writeln("A=M-1");
            writeln("M=0");
        }
    }

    public void writeReturn() {
        //Frame = LCL
        writeln("@LCL");
        writeln("D=M");
        writeln("@13");
        writeln("M=D");

        //RET = *(FRAME-5)
        writeln("@13");
        writeln("D=M");
        writeln("@5");
        writeln("A=D-A");
        writeln("D=M");
        writeln("@14");
        writeln("M=D");

        //*ARG = pop()
        writeln("@SP");
        writeln("AM=M-1");
        writeln("D=M");
        writeln("@ARG");
        writeln("A=M");
        writeln("M=D");

        //SP = ARG+1
        writeln("@ARG");
        writeln("D=M+1");
        writeln("@SP");
        writeln("M=D");

        //THAT = *(Frame-1)
        writeln("@13");
        writeln("A=M-1");
        writeln("D=M");
        writeln("@THAT");
        writeln("M=D");

        //THIS = *(Frame-2)
        writeln("@13");
        writeln("D=M");
        writeln("@2");
        writeln("A=D-A");
        writeln("D=M");
        writeln("@THIS");
        writeln("M=D");

        //ARG = *(Frame-3)
        writeln("@13");
        writeln("D=M");
        writeln("@3");
        writeln("A=D-A");
        writeln("D=M");
        writeln("@ARG");
        writeln("M=D");

        //LCL = *(Frame-4)
        writeln("@13");
        writeln("D=M");
        writeln("@4");
        writeln("A=D-A");
        writeln("D=M");
        writeln("@LCL");
        writeln("M=D");

        //goto RET
        writeln("@14");
        writeln("A=M");
        writeln("0; JMP");
    }

    public void writeCall(String name, int nArgs) {
        //Push return address
        writeln("@RET" + mRetCount);
        writeln("D=A");
        writeln("@SP");
        writeln("M=M+1");
        writeln("A=M-1");
        writeln("M=D");

        //Push LCL
        writeln("@LCL");
        writeln("D=M");
        writeln("@SP");
        writeln("M=M+1");
        writeln("A=M-1");
        writeln("M=D");

        //Push ARG
        writeln("@ARG");
        writeln("D=M");
        writeln("@SP");
        writeln("M=M+1");
        writeln("A=M-1");
        writeln("M=D");

        //Push THIS
        writeln("@THIS");
        writeln("D=M");
        writeln("@SP");
        writeln("M=M+1");
        writeln("A=M-1");
        writeln("M=D");

        //Push THAT
        writeln("@THAT");
        writeln("D=M");
        writeln("@SP");
        writeln("M=M+1");
        writeln("A=M-1");
        writeln("M=D");

        //ARG = SP-nArgs-5
        writeln("@SP");
        writeln("D=M");
        writeln("@" + nArgs);
        writeln("D=D-A");
        writeln("@5");
        writeln("D=D-A");
        writeln("@ARG");
        writeln("M=D");

        //LCL = SP
        writeln("@SP");
        writeln("D=M");
        writeln("@LCL");
        writeln("M=D");

        //Goto name
        writeln("@" + name);
        writeln("0; JMP");

        //Return label
        writeln("(" + "RET" + mRetCount++ + ")");
    }

    public void writeln(String line) {
        //write line to output file and increment line count
        mOutputFile.println(line);
        mLineCount++;
    }

    public void close() {
        mOutputFile.close();
    }
}