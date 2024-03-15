package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Alu {
    private int operand1 = -1;
    private int operand2 = -1;
    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPMAP = new HashMap<>();
    private BiFunction<Integer, Integer, Integer> SELECTED_OP = null;
    static {
        OPMAP.put("ADD", Integer::sum);
        OPMAP.put("MUL", (a, b) -> a * b);
        OPMAP.put("SUB", (a, b) -> a - b);
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public void setOPCODE(String OPCODE) {
        this.SELECTED_OP = OPMAP.get(OPCODE.toUpperCase());
    }

    public void enableSignal(Result r) {
        if (this.SELECTED_OP == null) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluResultCode.INVALID_OPERAND_CODE);
            return;
        }

        int result = AluResultCode.NO_RESULT;
        int resultCode = 0;
        if (isValidOperand(operand1, operand2)) {
            result = this.SELECTED_OP.apply(operand1, operand2);
            resultCode = AluResultCode.SUCCESS;
        } else if (!isValidOperand(operand1)) {
            resultCode = AluResultCode.INVALID_OPERAND_1;
        } else if (!isValidOperand(operand2)) {
            resultCode = AluResultCode.INVALID_OPERAND_2;
        }

        setAluResult(r, result);
        setAluStatus(r, resultCode);
    }

    private boolean isValidOperand(int operand1, int operand2) {
        return isValidOperand(operand1) && isValidOperand(operand2);
    }

    private boolean isValidOperand(int operand) {
        return operand != -1;
    }

    private void setAluResult(Result r, int result) {
        r.setResult(result);
    }
    private void setAluStatus(Result r, int statusCode) {
        r.setStatus(statusCode);
    }
}