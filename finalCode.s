.data
_a .word 0
_b .word 0
_c .word 0
.text
.globl main
_dI0:
li $t0,0
_dI1:
lw $t1, _i
li $t2,2
add $t3,$t1,$t2
_testV0:
main:
jal test0li $t2,2
li $t2,3
add $t1,$t2,$t2
sw $t1, _b
li $t2,3
li $t2,2
mul $t1,$t2,$t2
lw $t2, _b
sub $t4,$t2,$t1
li $t2,3
div $t1,$t4,$t2
li $t4,5
sub $t2,$t1,$t4
sw $t2, _a
li $t4,5
move $a0,$t4jal d1lw $t2, _RET
sw $t2, _c
lw $t2, _a
lw $t4, _c
bgt $t2,$t4,label4
b label5
label5:
lw $t2, _b
lw $t4, _c
bgt $t2,$t4,label4
b label3
label4:
lw $t2, _a
li $t0,0
beq $t2,$t0,label1
b label3
label3:
lw $t2, _a
lw $t4, _c
blt $t2,$t4,label1
b label2
label1:
li $t0,8
sw $t0, _c
b label0
label2:
label0:
