import dao.AdministradorDao;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Administrador;


@ManagedBean (name="indexControle")
@ViewScoped
public class indexControle implements Serializable{
    private Administrador adm;
    
    public indexControle(){
        adm = new Administrador();
    }
    
    public String autenticar(){ //Validar login que foi digitado na tela de login com as informações do banco
        this.adm.setLogin(adm.getLogin().toUpperCase());
        AdministradorDao dao = new AdministradorDao();
        Administrador temp;
        temp = dao.autenticar(adm);
        if (temp == null){  // se houver erro, método autenticar no dao retorna null
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos", null)); //Mostra mensagem de erro
            return null;  //fica na página
        }  
        //seta usuario na Sessao
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ectx = context.getExternalContext();
        HttpSession session = (HttpSession) ectx.getSession(true);
        session.setAttribute("usuarioLogado", temp);        
        return "listaPedidos";   //Se o login for concluido com sucesso, chama a página da lista de pedidos
    }
    
      public Administrador getAdm() {
        return adm;
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }
}