.data
_x: .word 0
_msg9: .asciiz "Ingrese un numero positivo: "
_msg6: .asciiz " "
_msg8: .asciiz "\n"
_msg7: .asciiz "*"
.text
.globl main
_validarPositivoI1:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,12
move $s0,$a0
label0:
li $t1,0
blt $s0,$t1,label2
b label1
label2:
li $v0, 4
la $a0, _msg9
syscall
li $v0, 1
syscall
sw $v0, _x
b label0
label1:
move $v0,$s0
_returnCode_validarPositivoI1:
move $sp,$fp
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
_printPiramideV1:
sw $fp,-4($sp)
sw $ra,-8($sp)
sw $s0,-12($sp)
move $fp,$sp
sub $sp,$sp,20
move $s0,$a0
move $a0,$s0
jal _validarPositivoI1
move $t1,$v0
move $s0, $t1
li $t1,1
sub $t2,$s0,$t1
li $t1,2
mul $t3,$t1,$t2
move $s0, $t3
li $t3,0
sw $t3, -20($fp)
label3:
li $t1,2
div $t3,$s0,$t1
blt $t3,$t3,label4
b label5
label4:
li $t3,0
sw $t3, -16($fp)
label6:
lw $t1,-20($fp)
sub $t2,$s0,$t1
blt $t3,$t2,label7
b label8
label7:
li $v0, 4
la $a0, _msg6
syscall
li $t1,1
add $t3,$t3,$t1
b label6
label8:
li $t3,0
sw $t3, -16($fp)
label9:
li $t1,2
mul $t2,$t1,$t1
blt $t3,$t2,label10
b label11
label10:
li $v0, 4
la $a0, _msg6
syscall
li $t1,1
add $t3,$t3,$t1
b label9
label11:
li $v0, 4
la $a0, _msg8
syscall
li $t1,1
add $t3,$t3,$t1
b label3
label5:
li $t3,0
sw $t3, -20($fp)
label12:
li $t1,2
div $t3,$s0,$t1
blt $t3,$t3,label13
b label14
label13:
li $t1,2
div $t3,$s0,$t1
sw $t3, -16($fp)
label15:
sub $t1,$s0,$t1
blt $t3,$t1,label16
b label17
label16:
li $v0, 4
la $a0, _msg6
syscall
li $t1,1
add $t3,$t3,$t1
b label15
label17:
li $t3,0
sw $t3, -16($fp)
label18:
li $t1,2
mul $t2,$t1,$t1
blt $t3,$t2,label19
b label20
label19:
li $v0, 4
la $a0, _msg7
syscall
li $t1,1
add $t3,$t3,$t1
b label18
label20:
li $t1,1
sw $t1, -16($fp)
label21:
li $t1,2
mul $t2,$t1,$t1
sub $t1,$s0,$t2
blt $t1,$t1,label22
b label23
label22:
li $v0, 4
la $a0, _msg6
syscall
li $t1,1
add $t1,$t1,$t1
b label21
label23:
li $t3,0
sw $t3, -16($fp)
label24:
li $t1,2
mul $t2,$t1,$t1
blt $t3,$t2,label25
b label26
label25:
li $v0, 4
la $a0, _msg7
syscall
li $t1,1
add $t3,$t3,$t1
b label24
label26:
li $v0, 4
la $a0, _msg8
syscall
li $t1,1
add $t3,$t3,$t1
b label12
label14:
_returnCode_printPiramideV1:
move $sp,$fp
lw $s0,-12($fp)
lw $ra,-8($sp)
lw $fp,-4($sp)
jr $ra
main:
li $v0, 4
la $a0, _msg9
syscall
li $v0, 5
syscall
sw $v0, _x
lw $t1, _x
move $a1,$t1
jal _printPiramideV1
li $v0,10
syscall