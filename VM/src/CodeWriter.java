/* Nathanael Cho
 * CS 220 1913 Thursdays 5:30-9:20
 * 10/30/2022
 **/

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CodeWriter {
    private PrintWriter mOutputFile;
    private int mLineCount;

    public CodeWriter(String fileName) {
        //Try to open output file
        try {
        mOutputFile = new PrintWriter(fileName);
        }
        catch(FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        //Initialize line count
        mLineCount = 0;
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
                mOutputFile.println("//eq");
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
                    writeln("@static." + index);
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
                    writeln("@static." + index);
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

    public void writeln(String line) {
        //write line to output file and increment line count
        mOutputFile.println(line);
        mLineCount++;
    }

    public void close() {
        mOutputFile.close();
    }
}