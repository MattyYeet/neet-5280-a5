package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemVerifierTest {

    @Test
    void checkSNpass() {
        String box = "1234567890";
        if (box.length() != 10 || box.equals("") ){
            box = "fail";
        } else {
            box = "pass";
        }
        assertEquals(box,"pass");
    }

    @Test
    void checkSNfail() {
        String box = "123456";
        if (box.length() != 10 || box.equals("") ){
            box = "pass";
        } else{
            box = "fail";
        }
        assertEquals(box,"pass");
    }

    @Test
    void checkPricepass() {
        String box = "9.0";
        Double amount = 9.0;
        if (box.equals(null)){
            box = "fail";
        } else if(!box.equals(amount.toString())){
            box = "fail";
        } else{
            box = "pass";
        }
        assertEquals(box,"pass");
    }

    @Test
    void checkPricefail() {
        String box = "ah";
        Double amount = 9.00;
        if (box.equals(null) || !box.equals(amount.toString())){
            box = "pass";
        } else {
            box = "fail";
        }
        assertEquals(box,"pass");
    }

    @Test
    void checkNamepass() {
        String box = "ahhh";
        String exist = "reee";
        if(box.length() < 2 || box.length() > 256 || box.equals(exist)){
            box = "fail";
        } else{
            box = "pass";
        }
        assertEquals(box, "pass");
    }

    @Test
    void checkNamefail() {
        String box = "reee";
        String exist = "reee";
        if(box.length() < 2 || box.length() > 256){
            box = "fail";
        } else if(box.equals(exist)){
            box = "fail";
        }else{
            box = "pass";
        }
        assertEquals(box, "fail");
    }
}