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
    
    public String autenticar(){ 
        this.adm.setLogin(adm.getLogin().toUpperCase());
        AdministradorDao dao = new AdministradorDao();
        Administrador temp;
        temp = dao.autenticar(adm);
        if (temp == null){  
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou senha inválidos", null)); 
            return null;  
        }  
        
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ectx = context.getExternalContext();
        HttpSession session = (HttpSession) ectx.getSession(true);
        session.setAttribute("usuarioLogado", temp);        
        return "listaPedidos";   
    }
    
      public Administrador getAdm() {
        return adm;
    }

    public void setAdm(Administrador adm) {
        this.adm = adm;
    }
}