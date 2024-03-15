package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AluTest {

    @Test
    void 정상적인계산테스트() {
        String OPCODE[] = {"ADD","SUB","MUL"};
        Integer results[] = {10, 4, 21};
        for(int i = 0 ; i < 3; i ++) {
            Alu alu = new Alu();
            Result result = new Result();

            alu.setOPCODE(OPCODE[i]);
            alu.setOperand1(7);
            alu.setOperand2(3);
            alu.enableSignal(result);

            assertEquals(results[i], result.getResult());
            assertEquals(0, result.getStatus());
        }
    }

    @Test
    void 비정상적인_operand1() {
        String OPCODE[] = {"ADD","SUB","MUL"};
        for(int i = 0 ; i < 3; i ++) {
            Alu alu = new Alu();
            Result result = new Result();

            alu.setOPCODE(OPCODE[i]);
            alu.setOperand2(3);
            alu.enableSignal(result);

            assertEquals(65535, result.getResult());
            assertEquals(1, result.getStatus());
        }
    }

    @Test
    void 비정상적인_operand2(){
        String OPCODE[] = {"ADD","SUB","MUL"};
        for(int i = 0 ; i < 3; i ++) {
            Alu alu = new Alu();
            Result result = new Result();

            alu.setOPCODE(OPCODE[i]);
            alu.setOperand1(7);
            alu.enableSignal(result);

            assertEquals(65535, result.getResult());
            assertEquals(2, result.getStatus());
        }
    }

    @Test
    void 비정상적인_OPCODE(){
        Alu alu = new Alu();
        Result result = new Result();

        alu.setOPCODE("DIV");
        alu.setOperand1(7);
        alu.setOperand2(3);
        alu.enableSignal(result);

        assertEquals(65535, result.getResult());
        assertEquals(3, result.getStatus());
    }
}