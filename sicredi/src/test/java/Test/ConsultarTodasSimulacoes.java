package Test;

import Base.RequestBase;
import Request.RequestBody;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

    public class ConsultarTodasSimulacoes {
        RequestBase base = new RequestBase();


    @Test
    @Order(1)
    @DisplayName("Consulta todas as simulações")
    public void consultaTodasSimulacoes(){
        Response teste = base.executeGetMethod("/api/v1/simulacoes");
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertNotNull(teste);
    }

}
