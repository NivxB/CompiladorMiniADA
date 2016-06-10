.data

.text
.globl main

_fib:
  sw $fp, -4($sp)
  sw $ra, -8($sp)
  sw $s0, -12($sp)
  move $fp,$sp
  sub $sp,$sp,12
  move $s0,$a0
  li $t0,1
  beq $s0,$t0,_label1
  b _label2
  _label1:
    li $v0,0
    b _finalLabel
 _label2:
 li $t0,2
 beq $s0,$t0,_label3
 b _label4
 _label3:
  li $v0,1
  b _finalLabel
_label4:
  sub $t0,$s0,1
  move $a0, $t0
  jal _fib
  move $t0,$v0
  sw $t0,-4($sp)
  sub $sp,$sp,4
  sub $t0,$s0,2
  move $a0,$t0
  jal _fib
  lw $t0,($sp)
  add $sp,$sp,4
  move $t1,$v0
  add $t3,$t0,$t1
  move $v0,$t3
_finalLabel:
  move $sp,$fp
  lw $s0,-12($fp)
  lw $ra,-8($fp)
  lw $fp,-4($fp)
  jr $ra

_leer:
 sw $fp,-4($sp)
 sw $ra,-8($sp)
 sub $sp,$sp,16
 li $t0,1
 sw $t0,-12($fp)
_veri:
 li $t1,6
 ble $t0,$t1,_body
 b _exit
_add:
   lw $t0,-12($fp)
   add $t0,$t0,1
   sw $t0,-12($fp)
   b _veri
_body:
 lw $a0,-12($fp)
 jal _fib
 sw $v0,-16($fp)
 move $a0,$v0
 li $v0,1
 syscall
 b _add
_exit:
  move $sp,$fp
  lw $ra,-8($fp)
  lw $fp,-4($fp)
  jr $ra
main:
  move $fp,$sp
  jal _leer
  li $v0,10
  syscall
