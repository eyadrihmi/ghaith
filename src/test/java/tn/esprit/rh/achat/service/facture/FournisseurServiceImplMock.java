package com.esprit.examen;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.services.FournisseurServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
public class FournisseurServiceImplMock {
	
	@Mock
	FournisseurRepository FournisseurRepository ; 
	
	@Mock
	DetailFournisseurRepository DetailFournisseurRepository ; 
	
	@InjectMocks
	FournisseurServiceImpl fournisseurService;
	
	Fournisseur f = new Fournisseur("CodeFournisseur","LibelleFournisseur");
	DetailFournisseur df = new DetailFournisseur("matr","addr");
	
	List<Fournisseur> list = new ArrayList<Fournisseur> () {
		{
			add(new Fournisseur("CodeFournisseur","LibelleFournisseur"));
			add(new Fournisseur("CodeFournisseur","LibelleFournisseur"));
			
		}
	};
	
	@Test  
	public void testRetrieveAllFournisseurs() {
		Mockito.when(FournisseurRepository.findAll()).thenReturn(list);
		assertEquals(list.size(), fournisseurService.retrieveAllFournisseurs().size());
	}

	
	@Test 
	public void testAddFournisseur() {
		Fournisseur f = new Fournisseur("CodeFournisseur","LibelleFournisseur");
		Mockito.when(FournisseurRepository.save(any(Fournisseur.class))).thenReturn(f);
		Fournisseur f1 = fournisseurService.addFournisseur(f);
		assertNotNull(f1);
		
	}
	
	@Test 
	public void testRetrieveFournisseur() {
		
		Mockito.when(FournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(f) );	
		Fournisseur f = fournisseurService.retrieveFournisseur(2L);
		assertNotNull(f);
		
	}
	
	@Test
	public void testSaveDetailFournisseur() 
	{
		Fournisseur f=new Fournisseur("code","libelle"); 
		DetailFournisseur dt =new DetailFournisseur("adresse","matricule");
		Mockito.when(FournisseurRepository.save(any(Fournisseur.class))).thenReturn(f);	
		Mockito.when(DetailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(df);	
		f.setDetailFournisseur(df);
		Fournisseur fourn = FournisseurRepository.save(f);
		DetailFournisseur dfs = fournisseurService.saveDetailFournisseur(fourn);
		assertNotNull(dfs);
		assertNotNull(f);	
	}
	
	@Test 
	public void testUpdateFournisseur() {
		Fournisseur f = new Fournisseur("CodeFournisseur","LibelleFournisseur");
		DetailFournisseur df = new DetailFournisseur("matricule","addresse");
		System.out.println("new Fournisseur"+f);
		Mockito.when(FournisseurRepository.save(any(Fournisseur.class))).thenReturn(f);	
		Mockito.when(DetailFournisseurRepository.save(any(DetailFournisseur.class))).thenReturn(df);	
		f.setLibelle("LibelleUpdated");
		f.setCode("CodeUpdated");
		f.setDetailFournisseur(df);
		System.out.println("DetailFournisseurRepository.save"+f);
		Fournisseur fUpdated = fournisseurService.updateFournisseur(f);
		assertNotNull(fUpdated);
		assertEquals(f.getLibelle(),fUpdated.getLibelle());

	}
	
	@Test 
	public void testDeleteFournisseur() {
		Fournisseur f =new Fournisseur("CodeFournisseur","LibelleFournisseur");
		f.setIdFournisseur(1L);	
		doNothing().when(FournisseurRepository).deleteById(Mockito.anyLong());
		fournisseurService.deleteFournisseur(1L);
		verify(FournisseurRepository,times(1)).deleteById(f.getIdFournisseur());
	}
	

}

	
	


