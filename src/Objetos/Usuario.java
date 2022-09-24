package Objetos;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String cpf;
    private Map<Produto, Integer> carrinho; //Objetos.Produto, Quantidade
    private boolean admin;

    public Usuario(String cpf, boolean admin){
        this.cpf = cpf;
        this.admin = admin;
        carrinho = new HashMap<>();
    }

    public boolean isAdmin(){
        return admin;
    }

    public String getCpf() {
        return cpf;
    }

    public static String formatarCPF(String cpf){
        if(cpf.length() != 11) return cpf; // retorna o cpf normal caso não seja formatável
        String[] chars = cpf.split("");
        return chars[0]+chars[1]+chars[2]+"."+chars[3]+chars[4]+chars[5]+"."+chars[6]+chars[7]+chars[8]+"-"+chars[9]+chars[10];
    }

    /* e.g 111.111.111-00 => 11111111100 (remove pontos e hifens)
    * */
    public static String desformatarCPF(String cpf){
        return cpf.replace(".", "").replace("-", "");
    }

    public void adicionarAoCarrinho(Produto produto, int quantidade){
        carrinho.put(produto, quantidade);
    }

    public Map<Produto, Integer> getCarrinho(){
        return carrinho;
    }

    public double totalCarrinho(){
        return getCarrinho().entrySet().stream()
                .mapToDouble((i) -> i.getValue()*i.getKey().getPreco()).reduce(0, Double::sum);
    }

    public void limparCarrinho(){
        this.carrinho.clear();
    }
}