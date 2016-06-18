.data
_c .word 0
_b .word 0
_a .word 0
_e .word 0
.text
.globl main
_dI0:
sw $fp,-4($sp)
sw $ra,-8($sp)
move $fp,$sp
sub $sp,$sp,8
li $t0,0
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_dI1:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,16
move $s0,$a0
li $t1,2
add $t2,$s0,$t1
move $sp,$fp
lw $s0,-12($sp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_dB1:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,12
move $s0,$a0
li $t1,2
add $t2,$s0,$t1
move $sp,$fp
lw $s0,-12($sp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_test0:
sw $fp,-4($sp)
sw $ra,-8($sp)
move $fp,$sp
sub $sp,$sp,8
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
main:
jal _test0
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
li $t0,5
move $a0,$t0
jal _dI1
move $t1,$v0
sw $t1, _c
li $t0,5
move $a1,$t0
jal _dB1
move $t1,$v0
sw $t1, _e
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
li $t0,0
sw $t0, _a
label9:
li $t0,10
blt $t0,$t0,label10
b label11
label10:
li $t1,3
sw $t1, _b
li $t0,1
add $t0,$t0,$t0
b label9
label11:
lw $t0, _e
li $t1,1
beq $t0,$t1,label13
b label12
label13:
li $t1,3
sw $t1, _b
label12:
