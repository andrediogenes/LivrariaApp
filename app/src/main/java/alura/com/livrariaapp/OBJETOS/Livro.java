package alura.com.livrariaapp.OBJETOS;

public class Livro {
    Integer livro_id;
    Integer usuario_cadastro_id;
    String livro_cod_barras;
    String livro_nome;
    String livro_genero;
    String livro_autor;
    String livro_preco;

    public String getLivro_cod_barras() {
        return livro_cod_barras;
    }

    public void setLivro_cod_barras(String livro_cod_barras) {
        this.livro_cod_barras = livro_cod_barras;
    }

    public Integer getLivro_id() {
        return livro_id;
    }

    public Integer getUsuario_cadastro_id() {
        return usuario_cadastro_id;
    }

    public void setUsuario_cadastro_id(Integer usuario_cadastro_id) {
        this.usuario_cadastro_id = usuario_cadastro_id;
    }

    public String getLivro_nome() {
        return livro_nome;
    }

    public void setLivro_nome(String livro_nome) {
        this.livro_nome = livro_nome;
    }

    public String getLivro_genero() {
        return livro_genero;
    }

    public void setLivro_genero(String livro_genero) {
        this.livro_genero = livro_genero;
    }

    public String getLivro_autor() {
        return livro_autor;
    }

    public void setLivro_autor(String livro_autor) {
        this.livro_autor = livro_autor;
    }

    public String getLivro_preco() {
        return livro_preco;
    }

    public void setLivro_preco(String livro_preco) {
        this.livro_preco = livro_preco;
    }
}
