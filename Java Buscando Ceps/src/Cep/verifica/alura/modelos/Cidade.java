package Cep.verifica.alura.modelos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Cidade {
    @SerializedName("cep")
    private String cep;
    @SerializedName("logradouro")
    private String logradouro;
    @SerializedName("complemento")
    private String complemento;
    @SerializedName("localidade")
    private String localidade;
    @SerializedName("uf")
    private String uf;

    public Cidade() {
    }

    public void pesquisaCepHttp(String busca) throws IOException, InterruptedException {
        // Para fazer a pesquisa e usar o Gson
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        // Tudo do Http para procurar, acho que entendi um pouco :)
        String endereco = "https://viacep.com.br/ws/" + busca + "/json/";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        // Para colocar os dados do Json na classe:
        Cidade cidadeAtual = gson.fromJson(json, Cidade.class);
        System.out.println("Cidade de: " + cidadeAtual.getUf());
        System.out.println(cidadeAtual);
        // Para escrever no arquivo Json
        FileWriter escrita = new FileWriter("resultado.json", true);
        /* Converte o objeto Cidade em JSON e escreve no arquivo, porque só quero os dados do objeto
        no JSON */
        gson.toJson(cidadeAtual, escrita);
        // Adiciona uma quebra de linha para separar os registros, para não ficar reescrevendo toda hora.
        escrita.write(System.lineSeparator());
        // Para fechar e salvar o arquivo depois
        escrita.close();
    }

    @Override
    public String toString() {
        return " > Cep: " + cep + "\n > Logradouro: " + logradouro +
                "\n > Complemento: " + complemento + "\n > Localidade: " + localidade
                + "\n > Estado: " + uf;
    }

    public String getUf() {
        return uf;
    }
}
