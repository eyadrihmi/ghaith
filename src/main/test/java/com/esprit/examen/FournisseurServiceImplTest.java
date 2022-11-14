package com.esprit.examen;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.services.FournisseurServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;



@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class FournisseurServiceImplTest {
	
	
		@Autowired
		FournisseurServiceImpl FournisseurServiceImpl;
		
		@Autowired
		FournisseurRepository FournisseurRepository;


	
	@Test
	public void testAddFournisseur(){
		Fournisseur fa=new Fournisseur("CodeFournisseur","LibelleFournisseur");
		Fournisseur f = FournisseurServiceImpl.addFournisseur(fa);
		assertNotNull(f.getIdFournisseur());
		assertTrue(f.getLibelle().length() > 0);
		FournisseurServiceImpl.deleteFournisseur(f.getIdFournisseur());
	}
	@Test
	public void testDeleteFournisseur() {
		Fournisseur fa=new Fournisseur("CodeFournisseur","LibelleFournisseur");
		Fournisseur f = FournisseurServiceImpl.addFournisseur(fa);
		FournisseurServiceImpl.deleteFournisseur(f.getIdFournisseur());
		assertNull(FournisseurServiceImpl.retrieveFournisseur(f.getIdFournisseur()));
	};
	

	@Test
	public void testRetrieveFournisseur()
	{
		Fournisseur fa=new Fournisseur("CodeFournisseur","LibelleFournisseur");
		Fournisseur f = FournisseurServiceImpl.addFournisseur(fa);
		Fournisseur fournisseur = FournisseurServiceImpl.retrieveFournisseur(f.getIdFournisseur());
		assertEquals(f.getIdFournisseur().longValue(),fournisseur.getIdFournisseur().longValue());
		
	}
	
	@Test
	public void testSaveDetailFournisseur() 
	{
		Fournisseur fa=new Fournisseur(); 
		DetailFournisseur dt =new DetailFournisseur("adresse","matricule");
		fa.setDetailFournisseur(dt);
		Fournisseur fourn = FournisseurRepository.save(fa);
		DetailFournisseur fs = FournisseurServiceImpl.saveDetailFournisseur(fourn);
		assertNotNull(fs);
		assertNotNull(fa);	
	}
	
	@Test
	public void testUpdateFournisseur()
	{
		Fournisseur fr=new Fournisseur("CodeFournisseur","LibelleFournisseur");
		Fournisseur f1 = FournisseurServiceImpl.addFournisseur(fr);
		Fournisseur f = FournisseurServiceImpl.retrieveFournisseur(f1.getIdFournisseur());
		f.setLibelle("test");
		f.setCode("tst");
		Fournisseur updatedFournisseur=FournisseurServiceImpl.updateFournisseur(f);
		assertEquals(f.getLibelle(),updatedFournisseur.getLibelle());
	}

}
