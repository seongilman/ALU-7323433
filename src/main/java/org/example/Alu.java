package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Alu {
    private int operand1 = -1;
    private int operand2 = -1;
    private final Map<String, BiFunction<Integer, Integer, Integer>> OPMAP = new HashMap<>();
    private BiFunction<Integer, Integer, Integer> SELECTED_OP = null;

    public Alu() {
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
        if (isInvalidOperand(r)){
            return;
        }

        int result = this.SELECTED_OP.apply(operand1, operand2);
        int resultCode = AluResultCode.SUCCESS;

        setAluResult(r, result);
        setAluStatus(r, resultCode);
    }

    private boolean isInvalidOperand(Result r) {
        if (this.SELECTED_OP == null) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluResultCode.NOT_SELECTED_OPERAND_CODE);
            return true;
        }
        if (isInvalidValue(this.operand1)) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluResultCode.INVALID_OPERAND_1);
            return true;
        }
        if (isInvalidValue(this.operand2)) {
            setAluResult(r, AluResultCode.NO_RESULT);
            setAluStatus(r, AluResultCode.INVALID_OPERAND_2);
            return true;
        }
        return false;
    }

    private boolean isInvalidValue(int operand) {
        return operand == -1;
    }

    private void setAluResult(Result r, int result) {
        r.setResult(result);
    }
    private void setAluStatus(Result r, int statusCode) {
        r.setStatus(statusCode);
    }
}