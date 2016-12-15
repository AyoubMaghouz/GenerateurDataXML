package Controller;
import java.util.Map;

import mesures.MesuresPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;


public class ExtractLoad {
	
	//Methodes Save et Load -> "http://ecariou.perso.univ-pau.fr/cours/idm/tp1.html"
	public void sauverModele(String uri, EObject root) {
		Resource resource = null;
		try {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mesures", new XMIResourceFactoryImpl());
			ResourceSetImpl resourceSetImpl = new ResourceSetImpl();
			resource = resourceSetImpl.createResource(URI.createURI(uri));			
			resource.getContents().add(root);
	      	resource.save(null); 
		} catch (Exception e) { 
			System.err.println("ERREUR sauvegarde du modèle : \n"+e);
			e.printStackTrace(); 
		} 
	}

	public Resource chargerModele(String uri) { //public Resource chargerModele(String uri, EPackage pack)
		Resource resource = null;
		MesuresPackage.eINSTANCE.eClass();
		
        Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> map = registry.getExtensionToFactoryMap();
        map.put("mesures", new XMIResourceFactoryImpl());

        ResourceSet resSet = new ResourceSetImpl();

        // Get the resource
        Resource res = resSet.getResource(URI.createURI(uri), true);
		
		return res;
	}
}
