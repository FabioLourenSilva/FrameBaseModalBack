package Test;

import Base.RequestBase;
import Steps.CriarSimulação;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class RemoverSimulacao {
        RequestBase base = new RequestBase();
        CriarSimulação idSimulacao = new CriarSimulação();

    @Test
    @Order(1)
    @DisplayName("Remover uma simulação existente")
    public void removerSimulaçãoExistente(){
        Response teste = base.executeDeleteMethod("/api/v1/simulacoes/" + idSimulacao.criarSimulação());
        Assert.assertEquals(204, teste.statusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Remover uma simulação inexistente")
    public void removerSimulaçãoInexistente(){
        Response teste = base.executeDeleteMethod("/api/v1/simulacoes/5666643");
        Assert.assertEquals(404, teste.statusCode());
        Assert.assertEquals("Simulação não encontrada", teste.path("mensagem"));
    }

}