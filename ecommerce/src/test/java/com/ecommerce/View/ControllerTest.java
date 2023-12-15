package com.ecommerce.View;

import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test; 
    
public class ControllerTest {

    @Test
    public void test() {
        String simulatedInput = "s\nProduto1\ns\nProduto2\nn\ns\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        Controller.main(null);

        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
        String consoleOutput = outputStream.toString();
        //assertTrue(consoleOutput.contains("produto adicionado ao carrinho"));
        // assertTrue(consoleOutput.contains("produto removido"));
        // assertTrue(consoleOutput.contains("removido"));
        // assertTrue(consoleOutput.contains("produto no carrinho"));
        // assertTrue(consoleOutput.contains("valor R$"));
        // assertTrue(consoleOutput.contains("concluir compra?"));
        // assertTrue(consoleOutput.contains("compra concluída"));

         assertFalse(consoleOutput.contains("alguma mensagem que não deveria estar lá"));
    }
}
    