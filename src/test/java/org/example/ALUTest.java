package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AluTest {

    static Alu alu;
    static Result result;

    @BeforeAll
    static void beforeAll() {
        alu = new Alu();
        result = new Result();
    }

    @Test
    void addTest() {
        alu.setOperand1(1);
        alu.setOperand2(2);
        alu.setOPCODE("ADD");

        alu.enableSignal(result);

        assertEquals(3, result.getResult());
        assertEquals(0, result.getStatus());
    }

    @Test
    void mulTest() {
        alu.setOperand1(2);
        alu.setOperand2(5);
        alu.setOPCODE("MUL");

        alu.enableSignal(result);

        assertEquals(10, result.getResult());
        assertEquals(0, result.getStatus());
    }

}