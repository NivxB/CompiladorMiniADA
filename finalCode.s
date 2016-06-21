.data
_c .word 0
_b .word 0
_a .word 0
_resFact .word 0
_msg0 .asciiz "test"
.text
.globl main
_factI1:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,12
move $s0,$a0
li $t1,0
beq $s0,$t1,label1
b label2
label1:
li $t1,1
move $v0,$t1
b _returnCode_factI1
b label0
label2:
li $t1,1
sub $t2,$s0,$t1
move $a0,$t2
jal _fact1
move $t1,$v0
mul $t3,$s0,$t1
move $v0,$t3
b _returnCode_factI1
label0:
_returnCode_factI1:
move $sp,$fp
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_dI0:
sw $fp,-4($sp)
sw $ra,-8($sp)
move $fp,$sp
sub $sp,$sp,8
li $t0,0
move $v0,$t0
_returnCode_dI0:
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
li $t0,5
sw $t0, -16($fp)
li $t1,1
add $t2,$s0,$t1
move $s0, $t2
lw $t1,-16($fp)
move $s0, $t1
li $t1,24
add $t3,$s0,$t1
sw $t3, -16($fp)
lw $t3, _a
add $t1,$t1,$t3
move $v0,$t1
_returnCode_dI1:
move $sp,$fp
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_test0:
sw $fp,-4($sp)
sw $ra,-8($sp)
move $fp,$sp
sub $sp,$sp,8
_returnCode_test0:
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
main:
li $t0,5
sw $t0, _a
lw $t0, _a
move $a0,$t0
jal _factI1
move $t1,$v0
sw $t1, _resFact
li $v0, 4
la $a0, a
syscall
li $v0, 1
syscalls
sw $v0, a
li $v0, 4
la $a0, msg0
syscall
lw $t0, _a
lw $t1, _c
bgt $t0,$t1,label7
b label8
label8:
lw $t1, _b
lw $t1, _c
bgt $t1,$t1,label7
b label6
label7:
lw $t0, _a
li $t1,0
beq $t0,$t1,label4
b label6
label6:
lw $t0, _a
lw $t1, _c
blt $t0,$t1,label4
b label5
label4:
li $t1,8
sw $t1, _c
b label3
label5:
label3:
li $t1,0
sw $t1, _a
label12:
li $t1,10
blt $t1,$t1,label13
b label14
label13:
li $t1,3
sw $t1, _b
li $t1,1
add $t1,$t1,$t1
b label12
label14:
lw $t0, _a
li $t0,5
 $t1,$t0,$t0
li $t0,1
beq $t1,$t0,label16
b label15
label16:
li $t1,3
sw $t1, _b
label15:
