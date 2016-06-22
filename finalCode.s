.data
_c: .word 0
_b: .word 0
_a: .word 0
_resFact: .word 0
_msg0: .asciiz "RECURSIVE CALL: "
_msg14: .asciiz "\n"
_msg2: .asciiz "Con FOR"
_msg15: .asciiz "Resultado: "
_msg6: .asciiz "Con While"
_msg11: .asciiz "Escriba num iteraciones"
_msg16: .asciiz "ELSE"
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
la $a0, _msg14
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
lw $t3,-16($fp)
lw $t1, _a
add $t4,$t3,$t1
move $v0,$t4
_returnCode_dI1:
move $sp,$fp
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_testV0:
sw $fp,-4($sp)
sw $ra,-8($sp)
move $fp,$sp
sub $sp,$sp,8
_returnCode_testV0:
move $sp,$fp
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
main:
li $v0, 4
la $a0, _msg2
syscall
li $t0,5
sw $t0, _a
li $t0,0
sw $t0, _b
label3:
lw $t0, _b
li $t1,5
ble $t0,$t1,label4
b label5
label4:
lw $t0, _b
move $a0,$t0
jal _factI1
move $t1,$v0
sw $t1, _resFact
li $v0, 4
la $a0, _msg15
syscall
li $v0, 1
lw $t1, _resFact
move $a0,$t1
syscall
li $v0, 4
la $a0, _msg14
syscall
li $v0, 4
la $a0, _msg14
syscall
lw $t0, _b
li $t2,1
add $t3,$t0,$t2
sw $t3, _b
b label3
label5:
li $v0, 4
la $a0, _msg6
syscall
li $v0, 4
la $a0, _msg14
syscall
li $t3,0
sw $t3, _b
label6:
lw $t0, _b
li $t3,5
ble $t0,$t3,label8
b label7
label8:
lw $t0, _b
move $a0,$t0
jal _factI1
move $t3,$v0
sw $t3, _resFact
li $v0, 4
la $a0, _msg15
syscall
li $v0, 1
lw $t1, _resFact
move $a0,$t1
syscall
li $v0, 4
la $a0, _msg14
syscall
li $v0, 4
la $a0, _msg14
syscall
lw $t0, _b
li $t2,1
add $t3,$t0,$t2
sw $t3, _b
b label6
label7:
li $v0, 4
la $a0, _msg11
syscall
li $v0, 5
syscall
sw $v0, _a
lw $t3, _a
li $t2,5
bgt $t3,$t2,label10
b label11
label10:
li $t2,0
sw $t2, _b
label12:
lw $t0, _b
lw $t3, _a
ble $t0,$t3,label13
b label14
label13:
lw $t0, _b
move $a0,$t0
jal _factI1
move $t3,$v0
sw $t3, _resFact
li $v0, 4
la $a0, _msg15
syscall
li $v0, 1
lw $t1, _resFact
move $a0,$t1
syscall
li $v0, 4
la $a0, _msg14
syscall
li $v0, 4
la $a0, _msg14
syscall
lw $t0, _b
li $t3,1
add $t2,$t0,$t3
sw $t2, _b
b label12
label14:
b label9
label11:
lw $t2, _a
li $t3,5
beq $t2,$t3,label16
b label17
label16:
li $t3,10
move $a0,$t3
jal _factI1
move $t2,$v0
sw $t2, _resFact
li $v0, 4
la $a0, _msg15
syscall
li $v0, 1
lw $t1, _resFact
move $a0,$t1
syscall
b label15
label17:
li $v0, 4
la $a0, _msg16
syscall
label15:
label9:
li $v0,10
syscall