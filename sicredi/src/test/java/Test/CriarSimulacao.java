package Test;

import Base.GeraCpfCnpj;
import Base.RequestBase;
import Request.RequestBody;
import Steps.CriarSimulação;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static Base.IdsEstaticos.cpf;
    public class CriarSimulacao {

         RequestBase base = new RequestBase();
         RequestBody body = new RequestBody();
         GeraCpfCnpj newCpf = new GeraCpfCnpj();
         CriarSimulação cpfSimulacao = new CriarSimulação();
         private String cpf;

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        @Test
    @Order(1)
    @DisplayName("Criar simulação com sucesso")
    public void criarSimulacao(){
        this.cpf =  newCpf.cpf(false);
        this.setCpf(cpf);
        String jsonBody = body.jsonSimulacao("Fulano de tal",this.cpf, "emeal@emeal.com",
                1200, 3, true);
        Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
        Assert.assertEquals(201, teste.statusCode());
        Assert.assertNotNull(teste.path("id") );
        Assert.assertEquals("Fulano de tal",teste.path("nome") );
        Assert.assertEquals(this.cpf,teste.path("cpf") );
        Assert.assertEquals("emeal@emeal.com",teste.path("email") );
        Assert.assertEquals(Optional.of(Optional.of(Optional.of(1200))), Optional.of(Optional.of(Optional.of(teste.path("valor")))));
        Assert.assertEquals(Optional.of(Optional.of(Optional.of(3))), Optional.of(Optional.of(Optional.of(teste.path("parcelas")))));
        Assert.assertEquals(true,teste.path("seguro") );
    }



        @Test
        @Order(2)
        @DisplayName("Criar simulação atributo nome incorreto")
        public void criarSimulacaoAtributosNomeIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacao("9", this.cpf, "emeal@emeal.com",
                    1000, 5, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(3)
        @DisplayName("Criar simulação atributo CPF incorreto")
        public void criarSimulacaoAtributosCpfIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacao("Fulano", cpf+"-=", "emeal@emeal.com",
                    1000, 5, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(4)
        @DisplayName("Criar simulação atributo email incorreto")
        public void criarSimulacaoAtributosEmailIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacao("Fulano", this.cpf, "emealemealcom",
                    1000, 5, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(5)
        @DisplayName("Criar simulação atributo valor incorreto")
        public void criarSimulacaoAtributosValorMenorIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacao("Fulano", this.cpf, "emeal@emeal.com",
                    100, 5, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(6)
        @DisplayName("Criar simulação atributo valor incorreto")
        public void criarSimulacaoAtributosValorMaiorIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacao("Fulano", this.cpf, "emeal@emeal.com",
                    55000, 5, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(7)
        @DisplayName("Criar simulação atributo parcela incorreto")
        public void criarSimulacaoAtributosParcelaMenorIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacao("Fulano", this.cpf, "emeal@emeal.com",
                    40000, 1, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(8)
        @DisplayName("Criar simulação atributo parcela incorreto")
        public void criarSimulacaoAtributosParcelaMaiorIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacao("Fulano", this.cpf, "emeal@emeal.com",
                    40000, 50, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(9)
        @DisplayName("Criar simulação atributo seguro incorreto")
        public void criarSimulacaoAtributosSeguroIncorreto(){
            this.cpf =  newCpf.cpf(false);
            String jsonBody = body.jsonSimulacaoSeguro("Fulano", this.cpf, "emeal@emeal.com",
                    40000, 40, 125);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(400, teste.statusCode());
            Assert.assertNotNull(teste.path("erros"));
        }

        @Test
        @Order(10)
        @DisplayName("Criar simulação com duplicada")
        public void criarSimulacaoDuplicada(){
            String jsonBody = body.jsonSimulacao("Fulano de tal", cpfSimulacao.criarSimulacaoCpf(), "emeal@emeal.com",
                    1200, 3, true);
            Response teste = base.executePostMethod("/api/v1/simulacoes",jsonBody);
            Assert.assertEquals(409, teste.statusCode());
            Assert.assertEquals("CPF já existente", teste.path("mensagem"));
        }


}
