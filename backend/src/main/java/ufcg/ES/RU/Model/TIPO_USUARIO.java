package ufcg.ES.RU.Model;

public enum TIPO_USUARIO {
    ALUNO("aluno"), RU("RU");

    private  String role;

    TIPO_USUARIO(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
