.data
_c: .word 0
_b: .word 0
_a: .word 0
_resFact: .word 0
_msg0: .asciiz "RECURSIVE CALL: "
_msg1: .asciiz "\n"
_msg2: .asciiz "Resultado: "
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
li $v0, 4
la $a0, _msg0
syscall
li $v0, 1
move $a0,$s0
syscall
li $v0, 4
la $a0, _msg1
syscall
li $t1,1
sub $t2,$s0,$t1
move $a0,$t2
jal _factI1
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
la $a0, _msg2
syscall
li $v0, 1
lw $t1, _resFact
move $a0,$t1
syscall
li $v0,10
syscall