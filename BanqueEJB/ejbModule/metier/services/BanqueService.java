package metier.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import metier.entities.Compte;
import metier.session.IBanqueLocal;
@Stateless
@WebService
public class BanqueService {

	@EJB(beanName="BK")
	private IBanqueLocal metier;
	
	@WebMethod
	public void addCompte(@WebParam(name="soldeInitial")double soldeInitial)
	{
		Compte cp=new Compte(soldeInitial,new Date(),true);
		metier.addCompte(cp);
	}
	
	@WebMethod
	public List<Compte> listComptes()
	{
		return metier.getAllComptes();
	}
	
	@WebMethod
	public Compte getCompte(@WebParam(name="code")Long code)
	{
		return metier.getCompte(code);
	}
	
	@WebMethod
	public void verser(@WebParam(name="mt")double mt, @WebParam(name="code")Long code)
	{
		metier.verser(mt, code);
	}
	
	@WebMethod
	public void retirer(@WebParam(name="mt")double mt, @WebParam(name="code")Long code)
	{
		metier.retirer(mt, code);
	}
	
	@WebMethod
	public void virement(@WebParam(name="mt")double mt, @WebParam(name="compte1")Long c1, @WebParam(name="compte2")Long c2)
	{
		metier.virement(mt, c1, c2);
	}
}
