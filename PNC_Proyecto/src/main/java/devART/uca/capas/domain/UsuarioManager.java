package devART.uca.capas.domain;

public class UsuarioManager {
    private AppUser user;
    private UserExpediente userExp;
    private String dpto;
    private String muny;

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getMuny() {
        return muny;
    }

    public void setMuny(String muny) {
        this.muny = muny;
    }

    public UsuarioManager(AppUser user, UserExpediente userExp, String dpto, String muny) {
        this.user = user;
        this.userExp = userExp;
        this.dpto = dpto;
        this.muny = muny;
    }

    public UsuarioManager() {
    }

    public UsuarioManager(AppUser user, UserExpediente userExp) {
        this.user = user;
        this.userExp = userExp;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public UserExpediente getUserExp() {
        return userExp;
    }

    public void setUserExp(UserExpediente userExp) {
        this.userExp = userExp;
    }
}
