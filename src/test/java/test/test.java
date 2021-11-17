package test;

import com.albares.ahorcado.utils.Parameters;

public class test {

    public static void main(String[] args) {
        Parameters.match.generateWord();
        String palabraS = Parameters.match.getSecretWord();
        
        
        String letra1 = "c";
        String letra2 = "coche";

        System.out.println("palabra jugada: " + Parameters.match.play(letra1));
        System.out.println("palabraSecreta: " + palabraS);
        System.out.println("premio: "+Parameters.match.isComplete());
    }
}
