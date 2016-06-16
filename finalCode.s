.data
_a .word 0
_b .word 0
_c .word 0
_e .word 0
.text
.globl main
_dI0:
sw $fp,-4($sp)
sw $ra,-8($sp)
move $fp,$sp
sub $sp,$sp,12
li $t0,0
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_dI1:
sw $fp,-4($sp)
sw $ra,-8($sp)
move $fp,$sp
sub $sp,$sp,12
lw $t1, _i
li $t2,2
add $t3,$t1,$t2
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_dB1:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,16
move $s0,$a0
lw $t1, _i
li $t2,2
add $t2,$t1,$t2
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_test0:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,16
move $s0,$a0
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
main:
jal _test0
li $t2,2
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
move $a0,$t4
jal _dI1
move $t2,$v0
sw $t2, _c
li $t4,5
move $a1,$t4
jal _dB1
move $t2,$v0
sw $t2, _e
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
