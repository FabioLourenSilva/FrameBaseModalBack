package Test;

import Base.RequestBase;
import Request.RequestBody;
import Steps.CriarSimulação;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

    public class ConsultaSimulacaoCpf {

        RequestBase base = new RequestBase();
        CriarSimulação cpfSimulacao = new CriarSimulação();

    @Test
    @Order(1)
    @DisplayName("Consulta simulação por CPF")
    public void consultaSimulacaoPorCpf(){
        Response teste = base.executeGetMethod("/api/v1/simulacoes/" + cpfSimulacao.criarSimulacaoCpf());
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertNotNull(teste.path("nome") );
        Assert.assertNotNull(teste.path("cpf") );
        Assert.assertNotNull(teste.path("email") );
        Assert.assertNotNull(teste.path("valor") );
        Assert.assertNotNull(teste.path("parcelas") );
        Assert.assertNotNull(teste.path("seguro") );
    }

        @Test
        @Order(2)
        @DisplayName("Consulta simulação inexistente por CPF")
        public void consultaSimulacoPorCpf(){
            Response teste = base.executeGetMethod("/api/v1/simulacoes/11122233344");
            Assert.assertEquals(404, teste.statusCode());
            Assert.assertEquals("O CPF 11122233344 possui restrição",teste.path("mensagem"));
        }
}
