package metier.session;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import metier.entities.Compte;
@Stateless(name="BK")
public class BanqueEJBImpl implements IBanqueLocal,IBanqueRemote{

	@PersistenceContext(unitName="UP_Banque")
	private EntityManager em;
	@Override
	public void addCompte(Compte c) {
		// TODO Auto-generated method stub
		em.persist(c);
	}

	@Override
	public List<Compte> getAllComptes() {
		// TODO Auto-generated method stub
		Query query=em.createQuery("select c from Compte c");
		return query.getResultList();
	}

	@Override
	public Compte getCompte(Long code) {
		// TODO Auto-generated method stub
		Compte cp = em.find(Compte.class, code);
		if(cp==null) throw new RuntimeException("Compte introuvable");
		return cp;
	}

	@Override
	public void verser(double mt, Long code) {
		// TODO Auto-generated method stub
		Compte cp=getCompte(code);
		cp.setSolde(cp.getSolde()+mt);
		//em.persist(cp);
	}

	@Override
	public void retirer(double mt, Long code) {
		// TODO Auto-generated method stub
		Compte cp=getCompte(code);
		cp.setSolde(cp.getSolde()-mt);
	}

	@Override
	public void virement(double mt, Long cpte1, Long cpte2) {
		// TODO Auto-generated method stub
		retirer(mt, cpte1);
		verser(mt, cpte2);
	}

	@Override
	public void updateCompte(Compte c) {
		// TODO Auto-generated method stub
		em.merge(c);
	}

	@Override
	public void deleteCompte(Long code) {
		// TODO Auto-generated method stub
		Compte cp = getCompte(code);
		em.remove(cp);
	}

}
