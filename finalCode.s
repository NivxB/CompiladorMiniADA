.data
_val: .word 0
_msg0: .asciiz "Seno yas "
.text
.globl main
_powI2:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
sw $s1,-16($sp)
move $fp,$sp
sub $sp,$sp,24
move $s0,$a0
move $s1,$a1
sw $s0, -20($fp)
li $t1,1
sw $t1, -24($fp)
label0:
blt $t1,$s1,label1
b label2
label1:
lw $t2,-20($fp)
mul $t3,$t2,$s0
sw $t3, -20($fp)
li $t1,1
add $t1,$t1,$t1
b label0
label2:
move $v0,$t2
_returnCode_powI2:
move $sp,$fp
lw $s1,-16($fp)
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_yasI2:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
sw $s1,-16($sp)
move $fp,$sp
sub $sp,$sp,32
move $s0,$a0
move $s1,$a1
li $t0,0
sw $t0, -32($fp)
label3:
blt $t0,$s1,label4
b label5
label4:
li $t0,2
lw $t2,-32($fp)
mul $t3,$t0,$t2
li $t2,1
add $t0,$t3,$t2
move $a0,$t0
move $a1,$s0
jal _powI2
move $t3,$v0
sw $t3, -24($fp)
li $t0,2
mul $t3,$t0,$t2
li $t2,1
add $t0,$t3,$t2
move $a2,$t0
jal _factI1
move $t2,$v0
sw $t2, -28($fp)
lw $t3,-24($fp)
lw $t5,-28($fp)
div $t6,$t3,$t5
lw $t3,-20($fp)
add $t7,$t3,$t6
sw $t7, -20($fp)
li $t2,1
add $t0,$t0,$t2
b label3
label5:
_returnCode_yasI2:
move $sp,$fp
lw $s1,-16($fp)
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_factI1:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,20
move $s0,$a0
li $t0,1
sw $t0, -16($fp)
li $t0,2
sw $t0, -20($fp)
label6:
blt $t0,$s0,label7
b label8
label7:
lw $t1,-16($fp)
lw $t3,-20($fp)
mul $t4,$t1,$t3
sw $t4, -16($fp)
li $t0,1
add $t0,$t0,$t0
b label6
label8:
move $v0,$t1
_returnCode_factI1:
move $sp,$fp
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
main:
li $v0, 4
la $a0, _msg0
syscall
li $t0,2
move $a0,$t0
li $t0,2
move $a1,$t0
jal _yasI2
move $t4,$v0
sw $t4, _val
li $v0, 1
lw $t4, _val
move $a0,$t4
syscall
li $v0,10
syscall