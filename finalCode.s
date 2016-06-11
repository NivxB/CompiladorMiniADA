.data
_a .word 0
_b .word 0
_c .word 0
.text
.globl main
li $t0,2
li $t1,3
add $t2,$t0,$t1
sw $t2, _b
li $t1,3
li $t0,2
mul $t2,$t1,$t0
lw $t1, _b
sub $t0,$t1,$t2
li $t1,3
div $t2,$t0,$t1
li $t0,5
sub $t1,$t2,$t0
sw $t1, _a
lw $t1, _RET
sw $t1, _c
lw $t1, _a
lw $t0, _c
bgt $t1,$t0,label4
b label5
label5:
lw $t1, _b
lw $t0, _c
bgt $t1,$t0,label4
b label3
label4:
lw $t1, _a
li $t0,0
beq $t1,$t0,label1
b label3
label3:
lw $t1, _a
lw $t0, _c
blt $t1,$t0,label1
b label2
label1:
li $t0,8
sw $t0, _c
b label0
label2:
label0:
