package org.example;

public class Alu {

    private int operand1 = -1;
    private int operand2 = -1;
    private String OPCODE = "";

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public void setOPCODE(String OPCODE) {
        this.OPCODE = OPCODE;
    }

    public void enableSignal(Result r) {
        if (OPCODE.equals("ADD") == true && !OPCODE.equals("MUL") == true && !OPCODE.equals("SUB") == true) {
            if (operand1 != -1 && operand2 != -1) {
                int result = operand1 + operand2;
                r.setResult(result);
                r.setStatus(0);
            }
            else if (operand1 == -1) {
                r.setResult(65535);
                r.setStatus(1);
            }
            else if (operand2 == -1) {
                r.setResult(65535);
                r.setStatus(2);
            }
        }
        else if (!OPCODE.equals("ADD") == true && OPCODE.equals("MUL") == true && !OPCODE.equals("SUB") == true) {
            if (operand1 != -1 && operand2 != -1) {
                int result = operand1 * operand2;
                r.setResult(result);
                r.setStatus(0);
            }
            else if (operand1 == -1) {
                r.setResult(65535);
                r.setStatus(1);
            }
            else if (operand2 == -1) {
                r.setResult(65535);
                r.setStatus(2);
            }
        }
        else if (!OPCODE.equals("ADD") == true && !OPCODE.equals("MUL") == true && OPCODE.equals("SUB") == true) {
            if (operand1 != -1 && operand2 != -1) {
                int result = operand1 - operand2;
                r.setResult(result);
                r.setStatus(0);
            }
            else if (operand1 == -1) {
                r.setResult(65535);
                r.setStatus(1);
            }
            else if (operand2 == -1) {
                r.setResult(65535);
                r.setStatus(2);
            }
        }
        else {
            r.setResult(65535);
            r.setStatus(3);
        }
    }
}