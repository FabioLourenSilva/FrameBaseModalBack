package Test;

import Base.RequestBase;
import Request.RequestBody;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

    public class ConsultaRestricao {

        RequestBase base = new RequestBase();


    @Test
    @Order(1)
    @DisplayName("Consulta restrição - CPF com restrição")
    public void consultaCpfComRestricao(){Response teste = base.executeGetMethod("/api/v1/restricoes/97093236014");
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertEquals("O CPF 97093236014 possui restrição",teste.path("mensagem") );
    }

    @Test
    @Order(2)
    @DisplayName("Consulta restrição - CPF sem restrição")
    public void consultaCpfSemRestricao(){
        Response teste = base.executeGetMethod("/api/v1/restricoes/67771486045");
        Assert.assertEquals(204, teste.statusCode());
    }

}
