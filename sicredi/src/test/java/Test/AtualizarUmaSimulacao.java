package Test;

import Base.RequestBase;
import Request.RequestBody;
import Steps.CriarSimulação;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class AtualizarUmaSimulacao {
         RequestBase base = new RequestBase();
         RequestBody body = new RequestBody();
    CriarSimulação cpfSimulacao = new CriarSimulação();

    @Test
    @Order(1)
    @DisplayName("Atualizar uma simulação inexistente")
    public void atualizarUmaSimulacaoInexistente(){
        String jsonBody = body.jsonSimulacao("Fulano de tal", " 87&¨%89", "emeal@gmail.com",
                1500, 10, false);
        Response teste = base.executePutMethod("/api/v1/simulacoes/ 87&¨%89",jsonBody);
        Assert.assertEquals(404, teste.statusCode());
        Assert.assertEquals("CPF não encontrado", teste.path("mensagem"));
    }
    @Test
    @Order(2)
    @DisplayName("Atualizar uma simulação atributo nome")
    public void atualizarSimulacaoAtributoNome(){
        String cpf = cpfSimulacao.criarSimulacaoCpf();
        String jsonBody = body.jsonSimulacao("Fulano",cpf , "emeal@gmail.com",
                1200, 10, false);
        Response teste = base.executePutMethod("/api/v1/simulacoes/"+cpf,jsonBody);
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertEquals("Fulano",teste.path("nome") );
    }

    @Test
    @Order(3)
    @DisplayName("Atualizar uma simulação atributo email")
    public void atualizarSimulacaoAtributoEmail(){
        String cpf = cpfSimulacao.criarSimulacaoCpf();
        String jsonBody = body.jsonSimulacao("Fulano", cpf, "emeal@gmail.com",
                1200, 10, false);
        Response teste = base.executePutMethod("/api/v1/simulacoes/"+cpf,jsonBody);
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertEquals("emeal@gmail.com",teste.path("email") );
    }

    @Test
    @Order(4)
    @DisplayName("Atualizar uma simulação atributo valor")
    public void atualizarSimulacaoAtributoValor(){
        String cpf = cpfSimulacao.criarSimulacaoCpf();
        String jsonBody = body.jsonSimulacaoValor("Fulano", cpf, "emeal@gmail.com",
                1500.00f, 10, false);
        Response teste = base.executePutMethod("/api/v1/simulacoes/"+cpf,jsonBody);
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertEquals(Optional.of(Optional.of(1500.00f)), Optional.of(Optional.ofNullable(teste.path("valor"))));
    }

    @Test
    @Order(5)
    @DisplayName("Atualizar uma simulação atributo parcelas")
    public void atualizarSimulacaoAtributoParcelas(){
        String cpf = cpfSimulacao.criarSimulacaoCpf();
        String jsonBody = body.jsonSimulacao("Fulano",cpf, "emeal@gmail.com",
                1200, 20, false);
        Response teste = base.executePutMethod("/api/v1/simulacoes/"+cpf,jsonBody);
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertEquals(Optional.of(Optional.of(Optional.of(20))), Optional.of(Optional.of(Optional.ofNullable((teste.path("parcelas"))))));
    }

    @Test
    @Order(6)
    @DisplayName("Atualizar uma simulação atributo parcelas maior")
    public void atualizarSimulacaoAtributoParcelasMaior(){
        String cpf = cpfSimulacao.criarSimulacaoCpf();
        String jsonBody = body.jsonSimulacao("Fulano",cpf, "emeal@gmail.com",
                1200, 50, false);
        Response teste = base.executePutMethod("/api/v1/simulacoes/"+cpf,jsonBody);
        Assert.assertEquals(400, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertNotNull(teste.path("erros"));
    }

    @Test
    @Order(7)
    @DisplayName("Atualizar uma simulação atributo seguro")
    public void atualizarSimulacaoAtributoSeguro(){
        String cpf = cpfSimulacao.criarSimulacaoCpf();
        String jsonBody = body.jsonSimulacao("Fulano", cpf, "emeal@gmail.com",
                1200,50 , true);
        Response teste = base.executePutMethod("/api/v1/simulacoes/"+cpf,jsonBody);
        Assert.assertEquals(200, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertEquals(true,teste.path("seguro"));
    }

    @Test
    @Order(8)
    @DisplayName("Atualizar uma simulação atributo seguro")
    public void atualizarSimulacaoAtributoSeguroInt(){
        String cpf = cpfSimulacao.criarSimulacaoCpf();
        String jsonBody = body.jsonSimulacaoSeguro("Fulano", cpf, "emeal@gmail.com",
                1200,50 , 154);
        Response teste = base.executePutMethod("/api/v1/simulacoes/"+cpf,jsonBody);
        Assert.assertEquals(400, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertNotNull(teste.path("erros"));
    }


}
