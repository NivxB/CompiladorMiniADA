.data
_a .word 0
_b .word 0
_c .word 0
.text
.globl main
lw $t0,a
lw $t1,c
bgt $t0,$t1,label4
b label5
label5:
lw $t1,b
lw $t1,c
bgt $t1,$t1,label4
b label3
label4:
lw $t0,a
li $t1,0
beq $t0,$t1,label1
b label3
label3:
lw $t0,a
lw $t1,c
blt $t0,$t1,label1
b label2
label1:
b label0
label2:
label0:
