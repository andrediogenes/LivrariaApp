package alura.com.livrariaapp.OBJETOS;

public class Venda {
    Integer venda_id;
    Integer venda_id_usuario;
    Integer venda_id_livro;
    String venda_data;
    String venda_forma_pagamento;

    public Integer getVenda_id() {
        return venda_id;
    }

    public Integer getVenda_id_usuario() {
        return venda_id_usuario;
    }

    public void setVenda_id_usuario(Integer venda_id_usuario) {
        this.venda_id_usuario = venda_id_usuario;
    }

    public Integer getVenda_id_livro() {
        return venda_id_livro;
    }

    public void setVenda_id_livro(Integer venda_id_livro) {
        this.venda_id_livro = venda_id_livro;
    }

    public String getVenda_data() {
        return venda_data;
    }

    public void setVenda_data(String venda_data) {
        this.venda_data = venda_data;
    }

    public String getVenda_forma_pagamento() {
        return venda_forma_pagamento;
    }

    public void setVenda_forma_pagamento(String venda_forma_pagamento) {
        this.venda_forma_pagamento = venda_forma_pagamento;
    }
}
