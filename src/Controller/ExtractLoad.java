package Controller;
import java.io.File;
import java.util.Map;

import mesures.MesuresPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;


public class ExtractLoad {
	
	//Methodes Save et Load -> "http://ecariou.perso.univ-pau.fr/cours/idm/tp1.html"
	public void sauverModele(String uri, EObject root) {
		Resource resource = null;
		try {
//			URI uriUri = URI.createFileURI(new File(uri).getAbsolutePath());
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("mesures", new XMIResourceFactoryImpl());
			ResourceSetImpl resourceSetImpl = new ResourceSetImpl();

			resource = resourceSetImpl.createResource(URI.createURI(uri));
//			resource = resourceSetImpl.getResource(uriUri, true);
			
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
		
		
//		//Methodes Save et Load -> "Métamodéliser avec Eclipse Modeling Framework" "Benoit Combemale" "source:http://people.irisa.fr/Benoit.Combemale/teaching/mde/0910/insa09-02-emf.pdf"
//		public void save(Measures mesures, File f) {
//			ResourceSet rs = new ResourceSetImpl();
//			Resource.Factory.Registry registry = rs.getResourceFactoryRegistry();
//			java.util.Map<String, Object> m = registry.getExtensionToFactoryMap();
//			m. put (" xmi", new XMLTypeFactoryImpl());
//			m. put (" ecore ", new EcoreFactoryImpl ());
//			rs . getPackageRegistry (). put ( MesuresPackage.eNS_URI, MesuresPackage.eINSTANCE);
//			Resource packageResource = rs.createResource(URI.createFileURI("Temperatures_à_Brest.Mesures" ));
//			packageResource.getContents().add ( MesuresPackage.eINSTANCE );
//			
//			try 
//			{ 
//				packageResource.load(null);
//			}
//			catch (IOException e1) 
//			{ 
//				e1.printStackTrace();
//			}
//			
//			URI uri = URI.createFileURI(f.getAbsolutePath());
//			Resource resource = rs.createResource(uri);
//			resource.getContents().add(mesures);
//			try {
//				HashMap <String, Boolean> options = new HashMap <String, Boolean>();
//				options.put( XMIResource . OPTION_SCHEMA_LOCATION ,Boolean.TRUE );
//			resource . save ( options );
//			} catch ( IOException e) { e. printStackTrace (); }
//			}
	//	
	//	
//		public Graph load ( File f) {
//			ResourceSet rs = new ResourceSetImpl ();
//			Resource . Factory . Registry registry =
//					XMIResource . OPTION_SCHEMA_LOCATIONrs . getResourceFactoryRegistry ();
//			Map < String , Object > m = registry . getExtensionToFactoryMap ();
//			m. put (" xmi", new XMIResourceFactoryImpl ());
//			rs . getPackageRegistry (). put ( GraphPackage . eNS_URI ,
//			GraphPackage . eINSTANCE );
//			URI uri = URI . createFileURI (f. getAbsolutePath ());
//			Resource resource = rs . getResource ( uri , true );
//			if ( resource . isLoaded () &&
//			resource . getContents (). size () > 0) {
//			return ( Graph ) resource . getContents (). get (0);
//			}
//			return null ;
//			}

}
