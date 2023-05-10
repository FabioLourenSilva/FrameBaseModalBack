package Steps;

import Base.GeraCpfCnpj;
import Base.RequestBase;
import Request.RequestBody;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static Base.IdsEstaticos.cpf;

public class CriarSimulação {

     RequestBase base = new RequestBase();
     RequestBody body = new RequestBody();
     GeraCpfCnpj newCpf = new GeraCpfCnpj();
     int id;



@Test
@Order(1)
@DisplayName("Criar simulação com sucesso")
public int criarSimulação(){
    cpf =  newCpf.cpf(false);
    String jsonBody = body.jsonSimulacao("Fulano de tal",cpf, "emeal@emeal.com",
            1200, 3, true);
    Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
    Assert.assertEquals(201, teste.statusCode());
    id = teste.getBody().path("id");
    return id;
}

    @Test
    @Order(1)
    @DisplayName("Criar simulação com sucesso")
    public String criarSimulacaoCpf(){
        cpf =  newCpf.cpf(false);
        String jsonBody = body.jsonSimulacao("Fulano de tal",cpf, "emeal@emeal.com",
                1200, 3, true);
        Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
        Assert.assertEquals(201, teste.statusCode());
        id = teste.getBody().path("id");
        return cpf;
    }
}
