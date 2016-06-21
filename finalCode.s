.data
_min: .word 0
_max: .word 0
_cont: .word 0
_n: .word 0
_c: .word 0
_b: .word 0
_a: .word 0
_msg0: .asciiz "Cantidad de alumnos: "
_msg1: .asciiz "Ingrese nota 1: "
_msg2: .asciiz "Ingrese nota 2: "
_msg3: .asciiz "Promedio del alumno No."
_msg4: .asciiz " es: "
_msg5: .asciiz " "
_msg6: .asciiz "La nota mas alta es: "
_msg7: .asciiz "La nota mas baja es: "
.text
.globl main
main:
li $t0,100
sw $t0, _min
li $v0, 4
la $a0, _msg0
syscall
li $v0, 5
syscall
sw $v0, _n
label0:
lw $t0, _cont
lw $t1, _n
blt $t0,$t1,label2
b label1
label2:
li $v0, 4
la $a0, _msg1
syscall
li $v0, 5
syscall
sw $v0, _a
li $v0, 4
la $a0, _msg2
syscall
li $v0, 5
syscall
sw $v0, _b
lw $t1, _a
lw $t0, _b
add $t2,$t1,$t0
li $t0,2
div $t1,$t2,$t0
sw $t1, _c
li $v0, 4
la $a0, _msg3
syscall
li $v0, 1
lw $t0, _cont
move $a0,$t0
syscall
li $v0, 4
la $a0, _msg4
syscall
li $v0, 1
lw $t1, _c
move $a0,$t1
syscall
li $v0, 4
la $a0, _msg5
syscall
lw $t0, _cont
li $t2,1
add $t3,$t0,$t2
sw $t3, _cont
lw $t1, _c
lw $t3, _max
bgt $t1,$t3,label4
b label5
label4:
lw $t1, _c
sw $t1, _max
b label3
label5:
label3:
lw $t1, _c
lw $t3, _min
blt $t1,$t3,label7
b label8
label7:
lw $t1, _c
sw $t1, _min
b label6
label8:
label6:
b label0
label1:
li $v0, 4
la $a0, _msg6
syscall
li $v0, 1
lw $t3, _max
move $a0,$t3
syscall
li $v0, 4
la $a0, _msg7
syscall
li $v0, 1
lw $t3, _min
move $a0,$t3
syscall
li $v0,10
syscall