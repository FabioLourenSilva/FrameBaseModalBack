package Request;

public class RequestBody {

    public String jsonSimulacao(String nome, String cpf, String email, int valor, int parcelas, boolean seguro){
        String jsonBody = "{\n" +
                "   \"nome\":\""+nome+"\",\n" +
                "   \"cpf\":\""+cpf+"\",\n" +
                "   \"email\":\""+email+"\",\n" +
                "   \"valor\":"+valor+",\n" +
                "   \"parcelas\":"+parcelas+",\n" +
                "   \"seguro\":"+seguro+"\n" +
                "}";
        return jsonBody;
    }

    public String jsonSimulacaoSeguro(String nome, String cpf, String email, int valor, int parcelas, int seguro){
        String jsonBody = "{\n" +
                "   \"nome\":\""+nome+"\",\n" +
                "   \"cpf\":\""+cpf+"\",\n" +
                "   \"email\":\""+email+"\",\n" +
                "   \"valor\":"+valor+",\n" +
                "   \"parcelas\":"+parcelas+",\n" +
                "   \"seguro\":"+seguro+"\n" +
                "}";
        return jsonBody;
    }

    public String jsonSimulacaoValor(String nome, String cpf, String email, float valor, int parcelas, boolean seguro){
        String jsonBody = "{\n" +
                "   \"nome\":\""+nome+"\",\n" +
                "   \"cpf\":\""+cpf+"\",\n" +
                "   \"email\":\""+email+"\",\n" +
                "   \"valor\":"+valor+",\n" +
                "   \"parcelas\":"+parcelas+",\n" +
                "   \"seguro\":"+seguro+"\n" +
                "}";
        return jsonBody;
    }

}
