package alura.com.livrariaapp.OBJETOS;

public class Livro {
    Integer livro_id;
    Integer usuario_cadastro_id;
    String livro_nome;
    String livro_genero;
    String livro_autor;
    Float livro_preco;

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

    public Float getLivro_preco() {
        return livro_preco;
    }

    public void setLivro_preco(Float livro_preco) {
        this.livro_preco = livro_preco;
    }
}
