@50
D=A
@arr
M=D  //new arr[]

@5
D=A
@n
M=D  //n=5

@i
M=0  //i=0
(LOOP)
@n
D=M
@i
D=M-D  //D=i-n
@END
D; JGE  //if i>n goto END

@100
@arr
D=M
@i
D=D+M
@R0
M=D
@100
D=A
@R0
A=M
M=D //arr[i] = 100

@i
M=M+1  //i++
@LOOP
0; JMP
(END)