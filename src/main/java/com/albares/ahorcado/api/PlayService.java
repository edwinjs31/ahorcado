package com.albares.ahorcado.api;

import com.albares.ahorcado.utils.JWTUtils;
import com.albares.ahorcado.utils.Parameters;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/play")
public class PlayService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}/{word}")
    public String playMatch(@PathParam("token") String token, @PathParam("word") String word) {
        
        Integer id = JWTUtils.checkJWTandGetUserId(token);
        //las palabras descubiertas
        String result = Parameters.match.play(word);
        Gamer player = Parameters.match.getGamers().get(id);
        
        //Si el turno es el correcto
        if (id.equals(Parameters.match.getTurn())) {
            //Si es mas de una letra
            if (word.length() > 1) {
                if (Parameters.match.isComplete()) {
                    player.setScore(player.getScore() + 100);
                } else {
                    player.setScore(player.getScore() - 50);
                }
            }
            //si es solo una letra
            char[] caracteres = Parameters.match.getUnderscoreFromWord();
            int counter=0;
            for (char l : caracteres) {
                if (l != '_') {
                    player.increment();//incrementa de 20 en 20 por cada letra
                    counter++;
                }
                //si las letras estan descubiertas todas,se genera otra palabra secreta.
                if(counter==caracteres.length) Parameters.match.generateWord();
            }
            //Acierte o no, se pasa de turno
            Parameters.match.nextTurn();
            
        } else {
            //Si no era su turno, se resta 100 puntos
            player.setScore(player.getScore() - 100);
        }

        return result;

    }

    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTurn() {
        return String.valueOf(Parameters.match.getTurn());
    }
}
